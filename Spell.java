package GAME.player;

public abstract class Spell implements Visitor {
     int damage;
    int costmana;

    abstract int getDamage();
    abstract int getCostmana();
}