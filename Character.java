package GAME.player;

import java.util.ArrayList;
import java.util.Random;

public abstract class Character extends Entity {
    private String nume;
    int Oxcurent;
    int Oycurent;
    Inventory inventory= new Inventory();
    int experienta;
    int nivelcurent;
    int strength, charisma, dexterity;

    public Character(String nume, int experienta, int nivelcurent) {
        viatacurenta= viatamaxima;
        manacurenta= manamaxima;
        this.nume = nume;
        Oxcurent = 0;
        Oycurent = 0;
        this.experienta = experienta;
        this.nivelcurent = nivelcurent;
        this.strength = 0;
        this.charisma = 0;
        this.dexterity = 0;

        Random rand = new Random();
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

    public void buyPotion (Potion potiune){
             inventory.addPotiune(potiune);
    }

    public void usePotion(Potion potiune){
        if(inventory.potiuni.contains(potiune)){
            potiune.utilizarePotiune(this);

        }

    }

    public abstract void levelUp();

    public String afisareinfo(){
        return  "Nume: "+ nume + "\n"+
                " experienta: " + experienta +"\n"+
                ", nivelcurent: " + nivelcurent ;


    }

}
