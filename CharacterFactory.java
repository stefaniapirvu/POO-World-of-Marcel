package GAME.player;

public class CharacterFactory {

    public Character makeCharacter (String newCharacterType,String nume, int experienta, int nivelcurent){
        Character newCharacter =null;

        if (newCharacterType.equalsIgnoreCase("WARRIOR")){
            return new Warrior(nume, experienta,nivelcurent);// mai dau parametrii nume lvl exp
        } else
        if (newCharacterType.equalsIgnoreCase("ROGUE")){
            return new Rogue(nume, experienta,nivelcurent);
        } else
            return new Mage(nume, experienta,nivelcurent);

    }
}
