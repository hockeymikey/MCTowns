/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jmhertlein.mctowns.banking;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

/**
 *
 * @author joshua
 */
public class BlockBank {

    private static final long serialVersionUID = "TOWNBANK".hashCode(); // DO NOT CHANGE
    private static final int VERSION = 1;
    private TreeMap<Integer, Integer> bank;
    private BigDecimal townFunds;

    /**
     * Constructs a new empty block bank.
     */
    public BlockBank() {
        bank = new TreeMap<>();
        townFunds = BigDecimal.ZERO;
    }

    /**
     * Deposits blocks into the block bank.
     *
     * @param dataValue the type of block to deposit
     * @param quantity the number of blocks to deposit
     * @return true if the blocks were deposited, false if they were not due to
     * any reason.
     */
    public boolean depositBlocks(int dataValue, int quantity) {

        if (quantity <= 0) {
            return false;
        }

        if (bank.containsKey(dataValue)) {
            bank.put(dataValue, quantity + bank.get(dataValue));
        } else {
            bank.put(dataValue, quantity);
        }

        return true;
    }

    /**
     * Attempts to subtract quantity blocks of type corresponding to dataValue
     * from the bank. The withdrawl either completes successfully (EXACTLY
     * 'quantity' blocks were withdrawn) and true is returned, or NOTHING
     * happens and false is returned.
     *
     * @param dataValue the type of block to withdraw
     * @param quantity the number of blocks to withdraw
     * @return true if the full number of blocks were withdrawn, false otherwise
     */
    public boolean withdrawBlocks(int dataValue, int quantity) {
        if (quantity <= 0) {
            return false;
        }

        if (bank.containsKey(dataValue)) {
            if (bank.get(dataValue) - quantity < 0) {
                return false;
            }

            bank.put(dataValue, bank.get(dataValue) - quantity);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks how many blocks of a certain data value are in the bank
     *
     * @param dataValue the data value of the block to be queried
     * @return the number of blocks in the bank for the given data value
     */
    public int queryBlocks(int dataValue) {
        if (bank.containsKey(dataValue)) {
            return bank.get(dataValue).intValue();
        }
        return -1;
    }

    public boolean depositCurrency(BigDecimal amt) {
        if (amt.compareTo(BigDecimal.ZERO) < 0) {
            return false;
        }

        townFunds = townFunds.add(amt);
        return true;

    }

    public BigDecimal withdrawCurrency(BigDecimal amt) {
        BigDecimal result;

        result = townFunds.subtract(amt);

        if (result.compareTo(BigDecimal.ZERO) < 0) {
            amt = amt.add(result);
        }

        townFunds = townFunds.subtract(amt);

        return amt;
    }

    public BigDecimal getCurrencyBalance() {
        return townFunds;
    }

    public boolean hasCurrencyAmount(BigDecimal amt) {
        return townFunds.compareTo(amt) >= 0;
    }

    public void writeYAML(FileConfiguration f) {
        f.set("bank.townFunds", townFunds.toString());

        List<String> l = new LinkedList<>();

        for(Entry<Integer, Integer> e : bank.entrySet()) {
            l.add(Material.getMaterial(e.getKey()).name() + "|" + e.getValue().toString());
        }

        f.set("bank.contents", l);
    }

    public static BlockBank readYAML(FileConfiguration f) {
        BlockBank bank = new BlockBank();

        bank.bank = new TreeMap<>();
        String[] temp;

        for(String s : f.getStringList("bank.contents")) {
            temp = s.split("|");
            bank.bank.put(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
        }

        bank.townFunds = new BigDecimal(f.getString("bank.townFunds"));

        return bank;
    }
}