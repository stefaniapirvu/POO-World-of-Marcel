package GAME.player;

public class Earth extends Spell {
    int damage=10;
    int costmana=3;

    int getDamage(){
        return damage;
    }
    int getCostmana(){
        return costmana;
    }

    public void visit(Entity entity){
        if( entity.earthprotection){
            entity.receiveDamage(0);
        }
        else{
            entity.receiveDamage(damage);
        }
    }
}
