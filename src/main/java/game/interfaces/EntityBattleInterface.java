package game.interfaces;

public interface EntityBattleInterface {
    
    /**
     * This will get the damage from the unit's attack.  This will also mean the attack's costs and effects go through.
     * @return The amount of damage.
     */
    public int getDamageOutput();

    /**
     * This will register damage to the unit.
     * @param damage The amount of damage registered.
     */
    public void inflictDamage(int damage);

}
