package game.enums;

/**An enum for all the available attack actions. */
public enum AttackAction {
    /**Basic attack. */
    BASIC("basic", 100, 0),
    /**Advanced attack. */
    ADVANCED("advanced", 150, 5);

    private final String str;
    /**Stamina cost. */
    private final int cost;
    /**Damage multiplier. */
    private final int multiplier;

    AttackAction(String str, int multiplier, int cost){
        this.str = str;
        this.cost = cost;
        this.multiplier = multiplier;
    }

    /**
     * Get the stamina cost for an action.
     * @return The cost.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Get the damage multiplier for an attack.
     * @return The multiplier.
     */
    public int getMultiplier() {
        return multiplier;
    }

    public String toString(){
        return str;
    }
}
