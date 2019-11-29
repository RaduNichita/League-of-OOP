package GameInput;

import Abilities.AbstractAbility;
import Players.*;

import java.util.ArrayList;

public class GameBattle {


    public static void Battle(ArrayList<AbstractPlayer> players) {
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

    public static void fight(AbstractPlayer hero1, AbstractPlayer hero2) {
                //defendPlayer.isAttacked(attackPlayer);
            //hero2.isAttacked(hero1);
        System.out.println(hero1);
        System.out.println(hero2);
        hero2.isAttacked(hero1);
    }
}
