package gameinput;

import players.AbstractPlayer;
import players.PlayerStatus;

import java.util.ArrayList;

public final class GameBattle {

    private GameBattle() {

    }

    public static void battle(final ArrayList<AbstractPlayer> players) {
        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                if ((players.get(i).getxCoordonate() == players.get(j).getxCoordonate()) && (players
                        .get(i).getyCoordonate() == players.get(j).getyCoordonate()) && (players
                        .get(i).getStatus() == PlayerStatus.ALIVE) && (players.get(j)
                        .getStatus() == PlayerStatus.ALIVE)) {

                    fight(players.get(i), players.get(j));
                }
            }
        }
    }

    public static void fight(final AbstractPlayer hero1, final AbstractPlayer hero2) {


        int damage1 = hero2.isAttacked(hero1);
        int damage2 = hero1.isAttacked(hero2);
        hero1.receiveDamage(damage2);
        hero2.receiveDamage(damage1);


        if (hero1.getStatus() == PlayerStatus.DEAD) {
            hero2.getExperience(hero1);
        }

        if (hero2.getStatus() == PlayerStatus.DEAD) {
            hero1.getExperience(hero2);
        }




        hero1.update();
        hero2.update();
    }
}
