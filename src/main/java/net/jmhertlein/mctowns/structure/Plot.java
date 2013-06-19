package net.jmhertlein.mctowns.structure;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import java.math.BigDecimal;
import net.jmhertlein.core.location.Location;
import net.jmhertlein.mctowns.MCTowns;
import net.jmhertlein.mctowns.MCTownsPlugin;
import net.jmhertlein.mctowns.remote.view.PlotView;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;

/**
 *
 * @author joshua
 */
public class Plot extends MCTownsRegion {

    private static final long serialVersionUID = "PLOT".hashCode(); // DO NOT CHANGE

    private volatile String parTerrName;
    private volatile String parTownName;

    private boolean forSale;
    private volatile BigDecimal price;
    private volatile Location signLoc;

    /**
     * Creates a new plot with the specified properties.
     * @param name
     * @param worldName
     * @param parentTerritoryName
     * @param parentTownName
     */
    public Plot(String name, String worldName, String parentTerritoryName, String parentTownName) {
        super(name, worldName);

        price = MCTowns.getTownManager().getTown(parentTownName).getDefaultPlotPrice();
        parTerrName = parentTerritoryName;
        parTownName = parentTownName;
        
        //note to self- don't put calculateSignLoc() here because it needs the region to already
        //be added to the region manager
    }

    /**
     * Empty constructor for de-serialization. Recommended: Don't use this.
     */
    private Plot() {}

    /**
     *
     * @return
     */
    public String getParentTerritoryName() {
        return parTerrName;
    }

    /**
     *
     * @return
     */
    public String getParentTownName() {
        return parTownName;
    }

    /**
     *
     * @return the price of the Plot
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     *
     * @return the location of the plot's sale sign
     */
    public Location getSignLoc() {
        return signLoc;
    }

    /**
     *
     * @return the shorter, reading-friendly name of the plot
     */
    public String getTerseName() {
        String absName = name;

        while (absName.contains("_")) {
            absName = absName.substring(absName.indexOf('_') + 1);
        }

        return absName;

    }

    /**
     *
     * @param signLoc the new location for the plot's sale sign
     */
    public void setSignLoc(Location signLoc) {
        this.signLoc = signLoc;
    }

    /**
     *
     * @return
     */
    public boolean isForSale() {
        return forSale;
    }

    /**
     *
     * @param forSale
     */
    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }

    /**
     *
     */
    public void buildSign() {

        if (signLoc == null) {
            MCTowns.logSevere("The sign's location was null.");
        }

        org.bukkit.Location loc = Location.convertToBukkitLocation(Bukkit.getServer(), signLoc);

        loc.getBlock().setType(Material.SIGN_POST);



        Sign sign = (Sign) loc.getBlock().getState();

        sign.setLine(0, "[mct]");
        sign.setLine(1, "For sale!");
        sign.setLine(2, name);
        sign.setLine(3, "Price: " + price);

        sign.update();
    }

    /**
     *
     */
    public void demolishSign() {
        Location.convertToBukkitLocation(Bukkit.getServer(), signLoc).getBlock().setType(Material.AIR);
    }

    /**
     * Tries to place the sign's location in the middle of the plot.
     */
    public final void calculateSignLoc() {
        WorldGuardPlugin wgp = MCTowns.getWorldGuardPlugin();
        ProtectedRegion reg = wgp.getRegionManager(wgp.getServer().getWorld(worldName)).getRegion(name);
        Vector middle = reg.getMaximumPoint().add(reg.getMinimumPoint());
        middle = middle.divide(2);


        org.bukkit.Location loc = new org.bukkit.Location(wgp.getServer().getWorld(worldName), middle.getBlockX(), middle.getBlockY(), middle.getBlockZ());

        loc.setY(loc.getWorld().getHighestBlockYAt(loc));

        signLoc = Location.convertFromBukkitLocation(loc);
    }

    /**
     *
     * @return
     */
    public boolean signLocIsSet() {
        return signLoc != null;
    }

    /**
     *
     * @param f
     */
    @Override
    public void writeYAML(FileConfiguration f) {
        super.writeYAML(f);
        f.set("forSale", forSale);
        f.set("price", (price == null) ? "nil" : price.toString());
        f.set("signLoc", (signLoc == null) ? "nil" : signLoc.toList());
        f.set("parentTownName", parTownName);
        f.set("parentTerritoryName", parTerrName);
        f.set("type", TownLevel.PLOT.name());
    }

    /**
     *
     * @param f
     * @return
     */
    public static Plot readYAML(FileConfiguration f) {
        Plot p = new Plot();

        p.name = f.getString("name");
        p.worldName = f.getString("worldName");
        p.parTerrName = f.getString("parentTerritoryName");
        p.parTownName = f.getString("parentTownName");
        p.forSale = f.getBoolean("forSale");

        if(f.getString("signLoc").equals("nil"))
            p.signLoc = null;
        else
            p.signLoc = Location.fromList(f.getStringList("signLoc"));

        if(f.getString("price").equals("nil"))
            p.price = null;
        else
            p.price = new BigDecimal(f.getString("price"));

        return p;
    }
    
    /**
     * Updates the plot so that it reflects changes made to the PlotView
     * NOTE: This will not modify player membership (owners or guests) or any non-mutable metadata (name, world, parent town, parent territ)
     * @param view 
     */
    public void updatePlot(PlotView view) {
        this.forSale = view.isForSale();
        this.price = view.getPrice();
        this.signLoc = view.getSignLoc();
    }
}