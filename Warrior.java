package GAME.player;

import java.util.Random;

public class Warrior extends Character{


  public Warrior(String nume, int experienta, int nivelcurent) {
    super(nume, experienta, nivelcurent);
    fireprotection=true;
    iceprotection=false;
    earthprotection=false;
    inventory.greutateramasa=200;
    inventory.nrmonede=300;

    strength=nivelcurent*3 +20;
    charisma =nivelcurent*2+10;
    dexterity=nivelcurent*2+5;
  }

  public void accept(Visitor visitor){
    visitor.visit(this);
  }
  public void levelUp( ){
    nivelcurent ++;
    strength=nivelcurent*3 +20;
    charisma =nivelcurent*2+10;
    dexterity=nivelcurent*2+5;

  }

  public void receiveDamage(int damage){
    if (dexterity + charisma > nivelcurent*3){
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
      int damage = strength -5;

      if (strength > nivelcurent+100 )
        damage=damage*2;

    return damage;
  }

  @Override
  public String toString() {
    return "WARRIOR";
  }


}


