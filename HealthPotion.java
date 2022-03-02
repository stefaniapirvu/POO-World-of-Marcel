package GAME.player.po;

import GAME.player.Potion;
import GAME.player.Character;

public class HealthPotion implements Potion {

    public HealthPotion() {
    }

    public void utilizarePotiune(Character character){
        character.regenerareViata(preluareVal());
    }
    public int preluarePret(){
        return 12;
    }
    public int preluareVal(){
        return 7;
    }
    public int preluareGreutate(){
        return 8;
    }

    @Override
    public String toString() {
        return "HealthPotion";
    }
}
