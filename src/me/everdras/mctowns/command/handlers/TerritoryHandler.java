/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package me.everdras.mctowns.command.handlers;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.everdras.mctowns.MCTowns;
import me.everdras.mctowns.command.ActiveSet;
import me.everdras.mctowns.command.MCTCommand;
import me.everdras.mctowns.database.TownManager;
import me.everdras.mctowns.structure.*;
import me.everdras.mctowns.townjoin.TownJoinManager;
import me.everdras.mctowns.util.Config;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Everdras
 */
public class TerritoryHandler extends CommandHandler {

    public TerritoryHandler(MCTowns parent, TownManager t, TownJoinManager j, CommandSender p, HashMap<String, ActiveSet> activeSets, WorldGuardPlugin wg, Economy econ, Config opt, MCTCommand cmd) {
        super(parent, t, j, p, activeSets, wg, econ, opt, cmd);
    }



    public void addDistrictToTerritory(String distName) {
        if (!senderWrapper.hasMayoralPermissions()) {
            senderWrapper.notifyInsufPermissions();
            return;
        }

        boolean autoActive = !cmd.hasFlag(MCTCommand.DISABLE_AUTOACTIVE);

        distName = senderWrapper.getActiveTown().getTownName() + DISTRICT_INFIX + distName;

        String worldName = senderWrapper.getActiveTown().getWorldName();
        District d = new District(distName, worldName);
        Territory parTerr = senderWrapper.getActiveTerritory();

        if (parTerr == null) {
            senderWrapper.notifyActiveTerritoryNotSet();
            return;
        }



        ProtectedCuboidRegion region = getSelectedRegion(d.getName());

        if (region == null) {
            return;
        }

        if (!this.selectionIsWithinParent(region, senderWrapper.getActiveTerritory())) {
            senderWrapper.sendMessage(ERR + "Selection is not in territory!");
            return;
        }


        ProtectedRegion parent = wgp.getRegionManager(wgp.getServer().getWorld(worldName)).getRegion(senderWrapper.getActiveTerritory().getName());
        try {
            region.setParent(parent);
        } catch (ProtectedRegion.CircularInheritanceException ex) {
            Logger.getLogger("Minecraft").log(Level.WARNING, "Circular Inheritence in addDistrictToTown.");
        }
        RegionManager regMan = wgp.getRegionManager(wgp.getServer().getWorld(worldName));

        if (regMan.hasRegion(distName)) {
            senderWrapper.sendMessage(ERR + "That name is already in use. Please pick a different one.");
            return;
        }

        regMan.addRegion(region);

        parTerr.addDistrict(d);

        senderWrapper.sendMessage("District added.");

        doRegManSave(regMan);

        if (autoActive) {
            senderWrapper.setActiveDistrict(d);
            senderWrapper.sendMessage(INFO + "Active district set to newly created district.");

        }

    }

    public void removeDistrictFromTerritory(String districtName) {
        if (!senderWrapper.hasMayoralPermissions()) {
            senderWrapper.notifyInsufPermissions();
            return;
        }

        Territory t = senderWrapper.getActiveTerritory();

        if (t == null) {
            senderWrapper.notifyActiveTerritoryNotSet();
            return;
        }

        District removeMe = t.getDistrict(districtName);

        if (removeMe == null) {
            senderWrapper.sendMessage(ERR + "That district doesn't exist. Make sure you're using the full name of the district (townname_district_districtshortname).");
        }

        t.removeDistrict(districtName);

        townManager.unregisterDistrictFromWorldGuard(wgp, removeMe);
        senderWrapper.sendMessage(SUCC + "District removed.");
    }

    public void addPlayerToTerritory(String playerName) {
        if (!senderWrapper.hasMayoralPermissions()) {
            senderWrapper.notifyInsufPermissions();
            return;
        }

        Territory territ = senderWrapper.getActiveTerritory();
        Player player = server.getPlayer(playerName);

        if (player == null) {
            senderWrapper.sendMessage(ChatColor.YELLOW + playerName + " is not online. Make sure you typed their name correctly!");
        }

        if (!senderWrapper.getActiveTown().playerIsResident(player)) {
            senderWrapper.sendMessage(ERR + "That player is not a member of the town.");
            return;
        }

        if (territ == null) {
            senderWrapper.notifyActiveTerritoryNotSet();
            return;
        }

        if (territ.addPlayerToWGRegion(wgp, playerName)) {
            senderWrapper.sendMessage("Player added to territory.");
        } else {
            senderWrapper.sendMessage(ERR + "That player is already in that territory.");
        }
    }

    public void removePlayerFromTerritory(String player) {
        if (!senderWrapper.hasMayoralPermissions()) {
            senderWrapper.notifyInsufPermissions();
            return;
        }

        boolean recursive = cmd.hasFlag(MCTCommand.RECURSIVE);

        Territory territ = senderWrapper.getActiveTerritory();

        if (territ == null) {
            senderWrapper.notifyActiveTerritoryNotSet();
            return;
        }

        if (player == null) {
            senderWrapper.sendMessage(ERR + "That player is not online.");
            return;
        }

        if (recursive) {
            if (!territ.removePlayerFromWGRegion(wgp, player)) {
                senderWrapper.sendMessage(ERR + "That player is not in this territory.");
                return;
            }


            for (District d : territ.getDistrictsCollection()) {
                d.removePlayerFromWGRegion(wgp, player);
                for (Plot p : d.getPlotsCollection()) {
                    p.removePlayerFromWGRegion(wgp, player);
                }
            }
            senderWrapper.sendMessage("Player removed from territory.");

        } else {
            if (!territ.removePlayerFromWGRegion(wgp, player)) {
                senderWrapper.sendMessage(ERR + "That player is not in this territory.");
                return;
            }
            senderWrapper.sendMessage("Player removed from territory.");
        }
    }

    public void setActiveTerritory(String territName) {
        Town t = senderWrapper.getActiveTown();

        if (t == null) {
            senderWrapper.notifyActiveTownNotSet();
            return;
        }



        Territory nuActive = t.getTerritory(territName);

        if (nuActive == null) {
            nuActive = t.getTerritory((t.getTownName() + TERRITORY_INFIX + territName).toLowerCase());
        }

        if (nuActive == null) {
            senderWrapper.sendMessage(ERR + "The territory \"" + territName + "\" does not exist.");
            return;
        }

        senderWrapper.setActiveTerritory(nuActive);
        senderWrapper.sendMessage("Active territory set to " + nuActive.getName());
    }

    public void listDistricts(int page) {

        Territory t = senderWrapper.getActiveTerritory();

        if (t == null) {
            senderWrapper.notifyActiveTerritoryNotSet();
            return;
        }
        senderWrapper.sendMessage(ChatColor.AQUA + "Existing districts (page " + page + "):");



        District[] dists = t.getDistrictsCollection().toArray(new District[t.getDistrictsCollection().size()]);

        for (int i = page - 1; i < dists.length && i < i + 5; i++) {
            senderWrapper.sendMessage(ChatColor.YELLOW + dists[i].getName());
        }
    }

    public void listDistricts() {
        listDistricts(1);
    }
}