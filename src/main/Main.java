package main;


import gameinput.GameEngine;
import gameinput.GameInputLoader;
import gameoutput.GameOutput;

import java.io.FileNotFoundException;

public final class main {

    private main() {

    }
    public static void main(final String[] args) throws IllegalAccessException,
            FileNotFoundException {
        String input = "/home/radu/facultate/An2/poo/LeagueOfOOP/src/test1.in"; //TODO
        String output = "/home/radu/facultate/An2/poo/LeagueOfOOP/src/test1.out"; //TODO
        // GameInputLoader gameInputLoader = new GameInputLoader(input, output);
       GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);

        GameEngine gameEngine = new GameEngine(gameInputLoader.load());
        gameEngine.processData();

       //GameOutput gameOutput = new GameOutput(output);
       GameOutput gameOutput = new GameOutput(args[1]);
        gameOutput.writeResults(gameEngine.finalScore());

    }
}
