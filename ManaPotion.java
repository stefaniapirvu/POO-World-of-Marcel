package GAME.player.po;

import GAME.player.Character;
import GAME.player.Potion;

public class ManaPotion implements Potion {
    public ManaPotion() {
    }

    public void utilizarePotiune(Character character){
        character.regenerareMana(preluareVal());
    }
    public int preluarePret(){
        return 10;
    }
    public int preluareVal(){
        return 5;
    }
    public int preluareGreutate(){
        return 7;
    }

    @Override
    public String toString() {
        return "ManaPotion";
    }
}
