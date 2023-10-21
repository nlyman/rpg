import static org.junit.Assert.assertEquals;

import org.junit.Test;

import game.classes.Entity;
import game.classes.Item;
import game.enums.AttackAction;
import game.enums.Resource;
import game.enums.Stat;
import game.interfaces.EntityInterface;
import game.interfaces.ItemInterface;

public class CheckThese {

    /**Tests if the item class works. */
    @Test
    public void item(){
        ItemInterface ring = new Item();

        //Checks setting stat.
        ring.setStat(Stat.MAX_HP, 10);
        assertEquals(10, ring.getStat(Stat.MAX_HP));

        //Checks overwriting stat and that negative works.
        ring.setStat(Stat.MAX_HP, -4);
        assertEquals(-4, ring.getStat(Stat.MAX_HP));
        
        //Checks if changing another stat works.
        ring.setStat(Stat.ARMOR, 6);
        assertEquals(6, ring.getStat(Stat.ARMOR));

        //Checks previous stat was not affected.
        assertEquals(-4, ring.getStat(Stat.MAX_HP));
    }

    /**Tests if base getters and setters on entity works. */
    @Test
    public void entityStatsNoEquip(){
        EntityInterface entity = new Entity();

        //Checks setting stat works and that min 1 is in place.
        entity.setBaseStat(Stat.MAX_HP, -4);
        assertEquals(1, entity.getStat(Stat.MAX_HP));
        
        //Checks that overwriting stat works.
        entity.setBaseStat(Stat.MAX_HP, 10);
        assertEquals(10, entity.getStat(Stat.MAX_HP));

        //Checks that changing another stat works.
        entity.setBaseStat(Stat.ARMOR, 3);
        assertEquals(3, entity.getStat(Stat.ARMOR));

        //Checks that the previous stat is still the same.
        assertEquals(10, entity.getStat(Stat.MAX_HP));
    }

    /**Tests stats working with equipping and removing items. */
    @Test
    public void entityStatsWithEquip(){
        EntityInterface entity = new Entity();
        ItemInterface ring = new Item();

        //Checks that entity base and item adds together.
        entity.setBaseStat(Stat.ATTACK, 4);
        ring.setStat(Stat.ATTACK, 3);
        //Also checks that previously returned null.
        assertEquals(null, entity.equip(ring));

        //Checks that changing a stat off entity works fine.
        entity.setBaseStat(Stat.ARMOR, 10);
        assertEquals(10, entity.getStat(Stat.ARMOR));

        //And that former stat is fine.
        assertEquals(7, entity.getStat(Stat.ATTACK));

        //Checks that updating ring works fine.
        ring.setStat(Stat.ATTACK, 10);
        assertEquals(14, entity.getStat(Stat.ATTACK));

        //Sets that unit base is separate from ring.
        entity.setBaseStat(Stat.ATTACK, -20);
        assertEquals(11, entity.getStat(Stat.ATTACK));

        //Checks if removing ring worked.
        //Also that it returned the item.
        ring=entity.equip(null);
        assertEquals(1, entity.getStat(Stat.ATTACK));

        //Checks that grabbing a new item worked.
        ItemInterface ring2 = new Item();
        ring2.setStat(Stat.ATTACK, 3);
        ring=entity.equip(ring2);
        assertEquals(4, entity.getStat(Stat.ATTACK));

        //Checks that swapping out items works.
        //Also checks that negative items works.
        entity.setBaseStat(Stat.ATTACK, 30);
        ring.setStat(Stat.ATTACK, -10);
        entity.equip(ring);
        assertEquals(20, entity.getStat(Stat.ATTACK));

        //Checks that a negative item will not reduce a stat below 1.
        entity.setBaseStat(Stat.ATTACK, 2);
        assertEquals(1, entity.getStat(Stat.ATTACK));
    }

    /**Tests if resources equal max value. */
    @Test
    public void entityResources(){
        EntityInterface entity = new Entity();
        entity.setBaseStat(Stat.MAX_HP, 103);
        assertEquals(103, entity.getRemainingResource(Resource.HEALTH));

        entity.setBaseStat(Stat.MAX_HP, 114);
        assertEquals(114, entity.getRemainingResource(Resource.STAMINA));

        entity.setBaseStat(Stat.MAX_HP, 121);
        assertEquals(121, entity.getRemainingResource(Resource.HEALTH));
    }

    /**
     * Tests if they take damage in correct amounts.
     * Also tests if MAX_HP impact on current health is correct.
     */
    @Test
    public void entityTakeDamage(){
        Entity entity = new Entity();
        entity.setBaseStat(Stat.MAX_HP, 197);
        entity.setBaseStat(Stat.ARMOR, -40);

        //Checks if negative armor registered as 1.
        entity.inflictDamage(10100);
        assertEquals(97, entity.getRemainingResource(Resource.HEALTH));

        //Checks impact of lowering max health.
        entity.setBaseStat(Stat.MAX_HP, 150);
        assertEquals(97, entity.getRemainingResource(Resource.HEALTH));

        //Checks impact of lowering max health below current health.
        entity.setBaseStat(Stat.MAX_HP, 95);
        assertEquals(95, entity.getRemainingResource(Resource.HEALTH));

        //Tests armor normally.
        entity.setBaseStat(Stat.ARMOR, 30);
        entity.inflictDamage(7628);
        assertEquals(37, entity.getRemainingResource(Resource.HEALTH));

        //Checks impact of raising max health.
        //I am aware they could lower and raise their max health.  Marking that as a feature presuming healing normally is easier.
        entity.setBaseStat(Stat.MAX_HP, 200);
        assertEquals(87, entity.getRemainingResource(Resource.HEALTH));

        //Confirms lowering max health below minimum max health also sets current health to minimum.
        entity.setBaseStat(Stat.MAX_HP, -20);
        assertEquals(1, entity.getRemainingResource(Resource.HEALTH));

        //Checks if overflowing damage goes to 0.
        entity.inflictDamage(8001);
        assertEquals(0, entity.getRemainingResource(Resource.HEALTH));

        //Checks if raising max health revives you.
        entity.setBaseStat(Stat.MAX_HP, 300);
        assertEquals(0, entity.getRemainingResource(Resource.HEALTH));

        //Checks if negative will set your current health to 1.
        entity.setBaseStat(Stat.MAX_HP, -300);
        assertEquals(0, entity.getRemainingResource(Resource.HEALTH));

        //Checks if restore resources works.
        entity.resetResources();
        assertEquals(1, entity.getRemainingResource(Resource.HEALTH));
    }

    /**
     * Checks entity sending out damage.
     * Also checks sp similarly to it did health before.
     */
    @Test
    public void entityAttack(){
        Entity entity = new Entity();

        //Checks basic attack.
        entity.setBaseStat(Stat.ATTACK, 20);
        entity.setBaseStat(Stat.MAX_SP, 115);
        entity.lockAttack(AttackAction.BASIC);
        assertEquals(120, entity.getDamageOutput());
        assertEquals(115, entity.getRemainingResource(Resource.STAMINA));

        //Checks changing attack and that basic still slotted.
        entity.setBaseStat(Stat.ATTACK, 30);
        assertEquals(130, entity.getDamageOutput());
        assertEquals(115, entity.getRemainingResource(Resource.STAMINA));

        //Checks advanced attack working right.
        entity.lockAttack(AttackAction.ADVANCED);
        assertEquals(195, entity.getDamageOutput());
        assertEquals(110, entity.getRemainingResource(Resource.STAMINA));

        //Checks if stamina raises with max stamina.  it shouldn't.
        entity.setBaseStat(Stat.MAX_SP, 120);
        assertEquals(110, entity.getRemainingResource(Resource.STAMINA));

        //Checks if stamina lowers with max stamina.  it should.
        entity.setBaseStat(Stat.MAX_SP, 4);
        assertEquals(4, entity.getRemainingResource(Resource.STAMINA));

        //Checks that it will default to basic if not enough stamina.
        assertEquals(130, entity.getDamageOutput());
        assertEquals(4, entity.getRemainingResource(Resource.STAMINA));

        //Checks negative max stamina will only reduce current stamina to min max stamina value.
        entity.setBaseStat(Stat.MAX_SP, -5);
        assertEquals(1, entity.getRemainingResource(Resource.STAMINA));

        //We need this for further testing.  and if restore works.
        entity.setBaseStat(Stat.MAX_HP, 5);
        entity.resetResources();
        assertEquals(5, entity.getRemainingResource(Resource.HEALTH));

        //Checks that this will set it to 0 stamina.
        assertEquals(195, entity.getDamageOutput());
        assertEquals(5, entity.getRemainingResource(Resource.HEALTH));
        assertEquals(0, entity.getRemainingResource(Resource.STAMINA));

        //Checks if this bugs out.
        entity.setBaseStat(Stat.MAX_SP, -5);
        assertEquals(0, entity.getRemainingResource(Resource.STAMINA));
    }

    //Note: impacts of items changing resource stats have not been tested.
}
