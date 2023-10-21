package game.interfaces;

import game.enums.AttackAction;
import game.enums.Resource;
import game.enums.Stat;

public interface EntityInterface {

    /**
     * Set a stat before item modification.
     * @param stat The stat being set.
     * @param the The amount being set to it.
     */
    public void setBaseStat(Stat baseStat, int amount);

    /**
     * Get the unit's stat.
     * @param stat The stat requested.
     * @return The value of the stat.
     */
    public int getStat(Stat stat);

    /**
     * This is for getting remaining health and such.
     * @param resource The resource requested.
     * @return The amount of the resource remaining.
     */
    public int getRemainingResource(Resource resource);

    /**
     * For equipping the entity with an item.
     * @param item The item added.
     * @return Will return the previously equipped item.  Null if nothing was there.
     */
    public ItemInterface equip(ItemInterface item);

    
    /**
     * This locks in what attacks the entity uses when fighting. I think I would change this if application expanded.
     * @param attack The attack being locked in.
     */
    public void lockAttack(AttackAction attack);

    /**
     * This resets health and stamina to max.
     */
    public void resetResources();

}
