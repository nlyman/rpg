package game.classes;

import game.enums.AttackAction;
import game.enums.Resource;
import game.enums.Stat;
import game.interfaces.EntityBattleInterface;
import game.interfaces.EntityInterface;
import game.interfaces.ItemInterface;

public class Entity implements EntityInterface, EntityBattleInterface{

    
    @Override
    public int getDamageOutput() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDamageOutput'");
    }

    @Override
    public void inflictDamage(int damage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inflictDamage'");
    }

    @Override
    public void setBaseStat(Stat baseStat, int amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setBaseStat'");
    }

    @Override
    public int getStat(Stat stat) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStat'");
    }

    @Override
    public int getRemainingResource(Resource resource) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRemainingResource'");
    }

    @Override
    public ItemInterface equip(ItemInterface item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'equip'");
    }

    @Override
    public void lockAttack(AttackAction attack) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lockAttack'");
    }

    @Override
    public void resetResources() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resetResources'");
    }
    
}
