package game.enums;

/**
 * This is for a unit's resources.
 */
public enum Resource {
    /**The remaining health. */
    HEALTH("health"),
    /**The remaining stamina. */
    STAMINA("stamina");

    private final String str;

    Resource(String str){
        this.str=str;
    }

    public String toString(){
        return str;
    }
}
