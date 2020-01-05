package gamelogic;

import heroes.Hero;
import heroes.HeroStatus;

import java.util.ArrayList;

public final class GameBattle {

    private GameBattle() {

    }

    /**
     * Check if two heroes will battle.
     *
     * @param players array of players currently in game.
     */
    public static void battle(final ArrayList<Hero> players) {
        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                if ((players.get(i).getxCoordonate() == players.get(j).getxCoordonate()) && (players
                        .get(i).getyCoordonate() == players.get(j).getyCoordonate()) && (players
                        .get(i).getStatus() == HeroStatus.ALIVE) && (players.get(j)
                        .getStatus() == HeroStatus.ALIVE)) {

                    fight(players.get(i), players.get(j));
                }
            }
        }
    }

    /**
     * Do the fight between 2 players. The attack player will give the first attack, and defend
     * player will give 2nd attack.
     *
     * @param hero1 -> attack player
     * @param hero2 -> defend player
     */

    public static void fight(final Hero hero1, final Hero hero2) {

        int damage1 = hero2.isAttacked(hero1);
        int damage2 = hero1.isAttacked(hero2);
        hero1.receiveDamage(damage2);
        hero2.receiveDamage(damage1);

        if (hero2.getStatus() == HeroStatus.DEAD) {
            hero2.killNotification(hero1);
            if (hero1.getStatus() == HeroStatus.ALIVE) {
                hero1.getExperience(hero2);
            }
        }

        if (hero1.getStatus() == HeroStatus.DEAD) {
            hero1.killNotification(hero2);
            if (hero2.getStatus() == HeroStatus.ALIVE) {
                hero2.getExperience(hero1);
            }
        }

        // update heroes parameters (if rogue);

        hero1.update();
        hero2.update();
    }

}
