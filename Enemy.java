package GAME.player;

import java.util.Random;

public class Enemy extends Entity implements CellElement{

    public char toCharacter(){
        return 'E';
    }

    public Enemy() {
        Random rand =new Random();
        earthprotection= rand.nextBoolean();
        fireprotection= rand.nextBoolean();
        iceprotection= rand.nextBoolean();
        viatacurenta=rand.nextInt(50,70);
        manacurenta=rand.nextInt(40,60);
        int x = rand.nextInt(2,5);
        while (x!=0){
            if (rand.nextBoolean()) {
                abilitati.add(new Fire());
                x--;
            }
            if (rand.nextBoolean() && x != 0) {
                abilitati.add(new Ice());
                x--;
            }
            if (rand.nextBoolean() && x != 0) {
                abilitati.add(new Earth());
                x--;
            }
        }


    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }

    void receiveDamage(int damage){
        Random rand =new Random();
        if(rand.nextBoolean())
            viatacurenta=viatacurenta-damage/2;
        else
            viatacurenta=viatacurenta-damage;

        if(viatacurenta<0)
            viatacurenta=0;
     }

    int getDamage(){
        Random rand =new Random();
        if(rand.nextBoolean())
            return 14;
        else
            return 7;
    }
}
