package GAME.player;


public class Fire extends Spell {
    int damage=15;
    int costmana=8;

    int getDamage(){
        return damage;
    }
    int getCostmana(){
        return costmana;
    }

    public void visit(Entity entity){
        if( entity.fireprotection){
            entity.receiveDamage(0);
        }
        else{
            entity.receiveDamage(damage);
        }
    }
}
