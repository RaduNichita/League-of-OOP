package gameinput;

import players.AbstractPlayer;
import players.PlayersFactory;
import terrain.Terrain;
import terrain.TerrainMap;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

public final class GameEngine {

    private final GameInput gameInput;
    private final ArrayList<AbstractPlayer> players = new ArrayList<>();
    private TerrainMap map;

    public GameEngine(final GameInput gameInput) {
        this.gameInput = gameInput;
    }

    public void processData() throws IllegalAccessException, FileNotFoundException {

        map = createMap();

        Iterator<Integer> iterator = gameInput.getInitialPosition().iterator();
        for (String s : gameInput.getPlayers()) {
            int xAxis = iterator.next();
            int yAxis = iterator.next();
            AbstractPlayer newPlayer = PlayersFactory.getPlayer(s, xAxis, yAxis);
            newPlayer.joinTerrain(map.getTerrain(xAxis, yAxis));
            players.add(newPlayer);
        }

        for (int i = 0; i < gameInput.getRounds(); i++) {
            playRound(i);
        }
    }

    private TerrainMap createMap() {
        ArrayList<ArrayList<Terrain>> newmap = new ArrayList<>();
        for (int i = 0; i < gameInput.getColumns(); i++) {
            ArrayList<Terrain> lineTerrain = new ArrayList<>();
            for (int j = 0; j < gameInput.getRows(); j++) {
                lineTerrain.add(new Terrain(gameInput.getTerrainType()[i][j], i, j));
            }
            newmap.add(lineTerrain);
        }
        return new TerrainMap(newmap);
    }

    public void playRound(final int roundNumber) throws IllegalAccessException {

        for (AbstractPlayer p : players) {
            p.preFightDamage();
        }

        char[] moves = gameInput.getMoves().get(roundNumber).toCharArray();
        for (int i = 0; i < moves.length; i++) {
            int xAxis = players.get(i).getxCoordonate();
            int yAxis = players.get(i).getyCoordonate();
            if (!players.get(i).isStuned()) {
                players.get(i).leftTerrain(map.getTerrain(xAxis, yAxis));
                switch (moves[i]) {
                    case 'R' :
                        players.get(i).joinTerrain(map.getTerrain(xAxis, yAxis + 1));
                        break;
                    case 'L' :
                        players.get(i).joinTerrain(map.getTerrain(xAxis, yAxis - 1));
                        break;
                    case 'U' :
                        players.get(i).joinTerrain(map.getTerrain(xAxis - 1, yAxis));
                        break;
                    case 'D' :
                        players.get(i).joinTerrain(map.getTerrain(xAxis + 1, yAxis));
                        break;
                    case '_' :
                        players.get(i).joinTerrain(map.getTerrain(xAxis, yAxis));
                        break;
                    default:
                        throw new IllegalAccessException("Symbol not found!");
                }
            } else {
                System.out.println("Jucatorul" + i + "nu se poate misca");
            }

        }
            GameBattle.battle(players);
    }

    public ArrayList<AbstractPlayer> finalScore() {
        return players;
    }
}
