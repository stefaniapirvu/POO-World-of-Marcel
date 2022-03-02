package GAME.player;

import java.util.Random;

class Rogue extends Character  {

 public Rogue(String nume, int experienta, int nivelcurent) {
  super(nume, experienta, nivelcurent);
  fireprotection = false;
  iceprotection = false;
  earthprotection = true;
  inventory.greutateramasa = 150;
  inventory.nrmonede = 300;

  strength = nivelcurent*2+10;
  charisma = nivelcurent*2+10;
  dexterity = nivelcurent*3 +20;
 }

 public void accept(Visitor visitor){
  visitor.visit(this);
 }

 public void receiveDamage(int damage){
  if (strength + charisma > nivelcurent*3){
   Random rand = new Random();
   if(rand.nextBoolean())
    viatacurenta = viatacurenta - damage / 2;
  }
  else
   viatacurenta = viatacurenta - damage;

  if (viatacurenta < 0)
   viatacurenta = 0;
 }

 public void levelUp( ){
  nivelcurent ++;
  strength = nivelcurent*2+10;
  charisma = nivelcurent*2+10;
  dexterity = nivelcurent*3 +20;

 }
 public int getDamage(){
  int damage = dexterity -5;

  if (dexterity > nivelcurent+100 )
   damage=damage*2;

  return damage;
 }
 @Override
 public String toString() {
  return "ROGUE";
 }

 String getType(){
  return "ROGUE";
 }

}
