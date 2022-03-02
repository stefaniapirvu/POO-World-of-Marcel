package GAME.player;

import GAME.player.po.HealthPotion;
import GAME.player.po.ManaPotion;

import java.util.ArrayList;
import java.util.Random;

class Shop implements CellElement {

    ArrayList<Potion> potionlist =new ArrayList<Potion>();

    public Shop() {
        // Pentru partea harcodata adaug in shop 2 potiuni pentru a fi sigura ca  exista
        potionlist.add(new HealthPotion());
        potionlist.add(new ManaPotion());

        Random rand =new Random();
        int n =rand.nextInt(2,5);
        for(int i =0; i < n; i++){
            if(rand.nextBoolean())
                potionlist.add(new HealthPotion());
            else
                potionlist.add(new ManaPotion());
        }

    }

    @Override
    public String toString() {
        return "Lista potiuni disponibile :" + potionlist;
    }

    Potion selectPotion (int index){
        return potionlist.get(index);
    }
    public char toCharacter(){
        return  'S';
    }


}
