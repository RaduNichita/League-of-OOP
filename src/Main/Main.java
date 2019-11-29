package Main;


import GameInput.GameEngine;
import GameInput.GameInputLoader;
import Players.AbstractPlayer;
import Players.PlayersFactory;
import Players.Rogue;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        String input ="/home/radu/facultate/An2/poo/League of OOP/src/test1.in";
        String output ="/home/radu/facultate/An2/poo/League of OOP/src/test1.out";
        GameInputLoader gameInputLoader = new GameInputLoader(input,output);

        GameEngine gameEngine = new GameEngine(gameInputLoader.load());
        gameEngine.processData();

    }
}
