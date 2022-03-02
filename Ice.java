package GAME.player;


public class Ice extends Spell {
    int damage=20;
    int costmana=5;

    int getDamage(){
        return damage;
    }
    int getCostmana(){
        return costmana;
    }

    public void visit(Entity entity){
        if( entity.iceprotection){
            entity.receiveDamage(0);
        }
        else{
            entity.receiveDamage(damage);
        }
    }


}
