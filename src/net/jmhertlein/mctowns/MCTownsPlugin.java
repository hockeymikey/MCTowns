package net.jmhertlein.mctowns;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.databases.ProtectionDatabaseException;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.jmhertlein.mctowns.command.ActiveSet;
import net.jmhertlein.mctowns.command.executors.MCTExecutor;
import net.jmhertlein.mctowns.command.executors.PlotExecutor;
import net.jmhertlein.mctowns.command.executors.TerritoryExecutor;
import net.jmhertlein.mctowns.command.executors.TownExecutor;
import net.jmhertlein.mctowns.database.TownManager;
import net.jmhertlein.mctowns.listeners.MCTPlayerListener;
import net.jmhertlein.mctowns.listeners.QuickSelectToolListener;
import net.jmhertlein.mctowns.permission.Perms;
import net.jmhertlein.mctowns.remote.server.RemoteConnectionServer;
import net.jmhertlein.mctowns.townjoin.TownJoinManager;
import net.jmhertlein.mctowns.util.metrics.Metrics;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main class of the MCTowns plugin.
 *
 * @author joshua
 */
public class MCTownsPlugin extends JavaPlugin {
    private static MCTownsPlugin singleton;

    public static final String TOWNS_SAVE_DIR_NAME = "saves",
            RSA_KEYS_DIR_NAME = "rsa_keys",
            AUTH_KEYS_DIR_NAME = "auth_keys",
            TEXT_CONFIG_FILE_NAME = "config.txt",
            META_TOWN_YAML_FILE_NAME = ".meta.yml";
    private static final String MCT_TEXT_CONFIG_PATH = "plugins" + File.separator + "MCTowns" + File.separator + "config.txt";
    private static final boolean DEBUGGING = false;
    private static TownManager townManager;
    private TownJoinManager joinManager;
    private HashMap<String, ActiveSet> activeSets;
    private HashMap<Player, ActiveSet> potentialPlotBuyers;
    private boolean abortSave;
    private Set<File> dataDirs, configFiles;

    /**
     * Persist any data that needs to be persisted.
     */
    @Override
    public void onDisable() {
        try {
            saveWorldGuardWorlds();
        } catch (ProtectionDatabaseException ex) {
            MCTowns.logSevere("Error saving WG regions: " + ex.getLocalizedMessage());
        }

        if (!abortSave) {
            persistTownManager();
        } else {
            MCTowns.logInfo("The save was aborted manually, so nothing was saved.");
        }

        MCTowns.logInfo("[MCTowns]: MCTowns has been successfully disabled.");
        try {
            trimFiles();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MCTownsPlugin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | InvalidConfigurationException ex) {
            Logger.getLogger(MCTownsPlugin.class.getName()).log(Level.SEVERE, null, ex);
        }

        //release as much memory as I can, to make reloads suck less.
        townManager = null;
        joinManager = null;
        activeSets = null;
        potentialPlotBuyers = null;
    }

    /**
     * Sets up files needed for persistence, registers listeners and
     * permissions, etc
     */
    @Override
    public void onEnable() {
        singleton = this;
        checkFiles();

        joinManager = new TownJoinManager();

        activeSets = new HashMap<>();

        if (MCTowns.economyIsEnabled()) {
            potentialPlotBuyers = new HashMap<>();
        }

        Perms.registerPermNodes(getServer().getPluginManager());
        hookInDependencies();
        setupTownManager();
        regEventListeners();
        setCommandExecutors();

        abortSave = false;

        startMetricsCollection();

        if(MCTowns.remoteAdminServerIsEnabled())
            startRemoteServer();

        MCTowns.logInfo("MCTowns is now fully loaded.");

    }

    private void checkFiles() {
        saveDefaultConfig();
        dataDirs = new HashSet<>();
        dataDirs.add(new File(this.getDataFolder(), RSA_KEYS_DIR_NAME));
        dataDirs.add(new File(this.getDataFolder(), TOWNS_SAVE_DIR_NAME));
        dataDirs.add(new File(this.getDataFolder(), AUTH_KEYS_DIR_NAME));

        for (File f : dataDirs)
            f.mkdirs();

        configFiles = new HashSet<>();
        configFiles.add(new File(this.getDataFolder(),"config.yml"));
    }

    private void setupTownManager() {
        try {
            townManager = TownManager.readYAML(this.getDataFolder().getPath());
        } catch (IOException | InvalidConfigurationException ex) {
            MCTowns.logWarning("MCTowns: Couldn't load the town database. Ignore if this is the first time the plugin has been run.");
            MCTowns.logInfo("If this was NOT expected, make sure you run the command /mct togglesave to make sure that you don't destroy your saves!");
            townManager = new TownManager();
        }
    }

    private void hookInDependencies() {
        Plugin wgp = this.getServer().getPluginManager().getPlugin("WorldGuard");
        if(wgp == null) {
            MCTowns.logSevere("[MCTowns] Error occurred in hooking in to WorldGuard. Is both WorldGuard and WorldEdit installed?");
            MCTowns.logSevere("[MCTowns] !!!!!NOTICE!!!!! MCTOWNS WILL NOW BE DISABLED.  !!!!!NOTICE!!!!!");
            this.getPluginLoader().disablePlugin(this);
        }

        if (MCTowns.economyIsEnabled()) {
            try {
                boolean success = setupEconomy();
                if (!success) {
                    MCTowns.logSevere("MCTowns: Unable to hook-in to Vault (1)!");
                }
            } catch (Exception e) {
                MCTowns.logSevere("MCTowns: Unable to hook-in to Vault.");
            }
        }
    }

    private void regEventListeners() {
        MCTPlayerListener playerListener = new MCTPlayerListener(this);
        QuickSelectToolListener qsToolListener = new QuickSelectToolListener(MCTowns.getWorldGuardPlugin(), this);

        //configure the tool listener as per the config
        QuickSelectToolListener.SELECT_TOOL = MCTowns.getQuickSelectTool();

        getServer().getPluginManager().registerEvents(playerListener, this);
        getServer().getPluginManager().registerEvents(qsToolListener, this);
    }

    private void persistTownManager() {
        try {
            townManager.writeYAML(this.getDataFolder().getPath());
        } catch (IOException ex) {
            MCTowns.logSevere("Error saving town database: " + ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }

    private boolean setupEconomy() {
        Economy economy = null;
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

    private void setCommandExecutors() {
        getCommand("mct").setExecutor(new MCTExecutor(this));
        getCommand("town").setExecutor(new TownExecutor(this));
        getCommand("territory").setExecutor(new TerritoryExecutor(this));
        getCommand("plot").setExecutor(new PlotExecutor(this));
    }

    private void trimFiles() throws FileNotFoundException, IOException, InvalidConfigurationException {
        File root = this.getDataFolder();
        File meta = new File(root, META_TOWN_YAML_FILE_NAME);
        FileConfiguration fileConfig = new YamlConfiguration();
        fileConfig.load(meta);

        List<String> towns = fileConfig.getStringList("towns"),
                regions = fileConfig.getStringList("regions");

        for (File f : root.listFiles()) {
            if (f.getName().equals(META_TOWN_YAML_FILE_NAME) || dataDirs.contains(f) || configFiles.contains(f))
                continue;

            String trunc = f.getName().substring(0, f.getName().lastIndexOf('.'));

            if (!(towns.contains(trunc) || regions.contains(trunc))) {
                f.delete();
            }
        }

    }

    public boolean willAbortSave() {
        return abortSave;
    }

    public void setAbortSave(boolean abortSave) {
        this.abortSave = abortSave;
    }
    
    public TownManager getTownManager() {
        return townManager;
    }

    public TownJoinManager getJoinManager() {
        return joinManager;
    }

    public HashMap<String, ActiveSet> getActiveSets() {
        return activeSets;
    }

    public HashMap<Player, ActiveSet> getPotentialPlotBuyers() {
        return potentialPlotBuyers;
    }

    private void startMetricsCollection() {
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            MCTowns.logSevere("Unable to submit plugin information. Please let everdras@gmail.com know. Thanks!");
        }
    }

    private void saveWorldGuardWorlds() throws ProtectionDatabaseException {
        for (World w : this.getServer().getWorlds()) {
            MCTowns.getWorldGuardPlugin().getRegionManager(w).save();
        }
    }

    public static boolean isDebugging() {
        return DEBUGGING;
    }

    private void startRemoteServer() {
        RemoteConnectionServer s;
        try {
            s = new RemoteConnectionServer(this, new File(this.getDataFolder(), "auth_keys"));
        } catch (IOException ex) {
            Logger.getLogger(MCTownsPlugin.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        s.start();
    }
    
    public static MCTownsPlugin getPlugin() {
        return singleton;
    }
}