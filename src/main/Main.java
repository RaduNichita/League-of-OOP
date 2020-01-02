// Copyright Nichita Radu, 321CA

package main;


import gameinput.GameEngine;
import gameinput.GameInputLoader;
import gameoutput.GameOutput;

import java.io.FileNotFoundException;

public final class Main {

    private Main() {

    }
    public static void main(final String[] args) throws IllegalAccessException,
            FileNotFoundException {

       String input = "/home/radu/facultate/An2/poo/LeagueOfOOP/src/test1.in";
       String output = "/home/radu/facultate/An2/poo/LeagueOfOOP/src/test1.out";
       GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
       // GameInputLoader gameInputLoader = new GameInputLoader(input, output);
       // GameOutput gameOutput = new GameOutput(output);
       GameOutput gameOutput = new GameOutput(args[1]);
       GameEngine gameEngine = new GameEngine(gameInputLoader.load(),gameOutput);
       gameEngine.processData();
        gameOutput.writeResults(gameEngine.finalScore());

    }
}
