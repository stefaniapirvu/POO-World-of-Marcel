package GAME;

import GAME.player.Game;

import java.io.IOException;

public class Test {

    public static void main (String[] args) throws IOException {

        Game game = Game.getInstance();
        game.run();

    }
}
