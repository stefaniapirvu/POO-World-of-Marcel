package GAME.player;

import java.util.ArrayList;

public abstract class Entity implements Element{

    ArrayList<Spell> abilitati= new ArrayList<Spell>();
    int viatacurenta;
    int viatamaxima =150;
    int manacurenta;
    int manamaxima =150;
    boolean fireprotection, iceprotection, earthprotection;


    public void regenerareViata(int viata){
        viatacurenta =viatacurenta + viata;
        if (viatacurenta > viatamaxima)
            viatacurenta = viatamaxima;

    }

    public void regenerareMana(int mana){
        manacurenta = manacurenta + mana;
        if ( manacurenta > manamaxima)
            manacurenta = manamaxima;

    }
    public void folosireAbilitate(Spell spell, Entity entity){
        if( spell.getCostmana() < manacurenta) {
            entity.accept(spell);
            manacurenta = manacurenta - spell.getCostmana();
            abilitati.remove(spell);
        }
    }

    abstract void receiveDamage(int damage);
    abstract int getDamage();

    public String afisareAbilitati(){
        String s =new String();
        int index;
        for(int i=0; i< abilitati.size(); i++ ) {
            index = i+1;
            s = s + index + "-" + abilitati.get(i).getClass().getSimpleName() + " ";
        }

        return  s;
    }




}
