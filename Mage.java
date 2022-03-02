package GAME.player;

import java.util.Random;

class Mage extends Character {

    public Mage(String nume, int experienta, int nivelcurent) {
        super(nume, experienta, nivelcurent);
        fireprotection = false;
        iceprotection = true;
        earthprotection = false;
        inventory.greutateramasa = 100;
        inventory.nrmonede = 300;

        strength = nivelcurent*2+10;
        charisma = nivelcurent*3+20;
        dexterity = nivelcurent*2 +20;


    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }

    public void levelUp( ){
        nivelcurent ++;
        strength = nivelcurent*2+10;
        charisma = nivelcurent*3+20;
        dexterity = nivelcurent*2 +20;

    }

    public void receiveDamage(int damage){
        if (strength + dexterity > nivelcurent*3){
            Random rand = new Random();
            if(rand.nextBoolean())
                viatacurenta = viatacurenta - damage / 2;
        }
        else
            viatacurenta = viatacurenta - damage;

        if (viatacurenta < 0)
            viatacurenta = 0;
    }

    public int getDamage(){
        int damage = charisma -5;

        if (charisma > nivelcurent+100 )
            damage=damage*2;

        return damage;
    }

    @Override
    public String toString() {
        return "MAGE";
    }



    String getType(){
        return "MAGE";
    }
}
