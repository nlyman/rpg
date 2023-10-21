package game.enums;

/**
 * This is for the unit stats in the game.
 */
public enum Stat {
    /**Max health. */
    MAX_HP("HP"),
    /**Max stamina. */
    MAX_SP("SP"),
    /**The attack. */
    ATTACK("attack"),
    /**The armor. */
    ARMOR("armor");

    private final String str;

    Stat(String str) {
        this.str=str;
    }
    
    public String toString(){
        return str;
    }
}
