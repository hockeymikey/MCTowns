package net.jmhertlein.mctowns.structure;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.configuration.file.FileConfiguration;

/**
 *
 * @author joshua
 */
public class Territory extends MCTownsRegion{

    private static final long serialVersionUID = "TERRITORY".hashCode(); // DO NOT CHANGE
    private static final int VERSION = 0;

    private String parTownName;
    private Set<String> plotNames;

    /**
     * Constructs a new territory
     *
     * @param name the desired name of the territory
     * @param worldName the name of the world in which the territory exists
     */
    public Territory(String name, String worldName, String parentTownName) {
        super(name, worldName);
        plotNames = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());
        parTownName = parentTownName;
    }

    public Territory(){}

    /**
     * Adds a plot to the territory. Registering the WG region of the territory
     * needs to be done elsewhere.
     *
     * @param dist the plot to be added
     * @return false if the plot was not added because it is already added, true
     * otherwise
     */
    public boolean addPlot(Plot plot) {
        if (plotNames.contains(plot.getName())) {
            return false;
        }

        plotNames.add(plot.getName());
        return true;
    }

    /**
     * Modifying membership of returned set does not modify which plots
     * are in this territ
     * 
     * Sets returned by this method will not update themselves if subsequent Territory method
     * calls add plots to it
     * 
     * Returned Set is a LinkedHashSet and as such performs well for
     * iteration and set membership checks
     * @return the plots owned by this territory
     */
    public Set<String> getPlotsCollection() {
        return new LinkedHashSet<>(plotNames);
    }

    /**
     * Removes the plot from the territory
     *
     * @param plotName the name of the plot to be removed
     * @return if a plot was removed or not
     */
    public boolean removePlot(String plotName) {
        return plotNames.remove(plotName);
    }

    public String getParentTown() {
        return parTownName;
    }

    @Override
    public void writeYAML(FileConfiguration f) {
        super.writeYAML(f);
        f.set("town", parTownName);
        f.set("plots", getPlotNameList());
        f.set("type", TownLevel.TERRITORY.name());

    }

    public static Territory readYAML(FileConfiguration f) {
        Territory ret = new Territory();

        ret.name = f.getString("name");
        ret.worldName = f.getString("worldName");
        ret.parTownName = f.getString("town");

        ret.plotNames = new HashSet<>();
        ret.plotNames.addAll(f.getStringList("plots"));

        return ret;
    }

    private List<String> getPlotNameList() {
        LinkedList<String> ret = new LinkedList<>();

        for(String s : plotNames) {
            ret.add(s);
        }

        return ret;
    }


}