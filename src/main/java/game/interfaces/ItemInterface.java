package game.interfaces;

import game.enums.Stat;

/**
 * This is the interface for interacting with items.
 */
public interface ItemInterface {

    /**
     * Sets a stat of the item.
     * @param stat The stat being set.
     * @param amount The amount being set to it.
     */
    public void setStat(Stat stat, int amount);

    /**
     * Get the stat belonging to the item.
     * @param stat The stat requested.
     * @return The value in the stat.
     */
    public int getStat(Stat stat);

}
