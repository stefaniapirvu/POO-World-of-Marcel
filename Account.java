package GAME.info;

import GAME.player.Character;

import java.util.ArrayList;
import java.util.*;

public class Account {

    Information info;
    public ArrayList<Character> personaje;
    int nrjocuri;


    public Account(Information info, ArrayList<Character> personaje, int nrjocuri) {
        this.info = info;
        this.personaje = personaje;
        this.nrjocuri = nrjocuri;
    }

    public Information getInfo() {
        return info;
    }

    public void setInfo(Information info) {
        this.info = info;
    }

    public ArrayList<Character> getPersonaje() {
        return personaje;
    }

    public void setPersonaje(ArrayList<Character> personaje) {
        this.personaje = personaje;
    }

    public int getNrjocuri() {
        return nrjocuri;
    }

    public void setNrjocuri(int nrjocuri) {
        this.nrjocuri = nrjocuri;
    }

    @Override
    public String toString() {
        return "Account{" +
                "info=" + info +
                ", personaje=" + personaje +
                ", nrjocuri=" + nrjocuri +
                '}';
    }

}

