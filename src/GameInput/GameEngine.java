package GameInput;

import Players.AbstractPlayer;
import Players.PlayersFactory;
import Terrain.Terrain;
import Terrain.TerrainMap;

import java.util.ArrayList;
import java.util.Iterator;

public final class GameEngine {

    GameInput gameInput;
    ArrayList<AbstractPlayer> players = new ArrayList<>();
    TerrainMap map;
    //ArrayList<ArrayList<Terrain>> map = new ArrayList<>();

    public GameEngine(final GameInput gameInput) {
        this.gameInput = gameInput;
    }

    public void processData() throws IllegalAccessException {

        map = createMap();

        Iterator<Integer> iterator = gameInput.getInitialPosition().iterator();
        for (String s : gameInput.getPlayers()) {
            int x_axis = iterator.next();
            int y_axis = iterator.next();
            AbstractPlayer newPlayer = PlayersFactory.getPlayer(s,x_axis,y_axis);
            newPlayer.joinTerrain(map.getTerrain(x_axis,y_axis));
            players.add(newPlayer);
        }
       // System.out.println(players);

        for (int i = 0; i< gameInput.getRounds(); i++) {
            playRound(i);
        }
    }

    private TerrainMap createMap() {
        ArrayList<ArrayList<Terrain>> map = new ArrayList<>();
        for (int i = 0; i < gameInput.getColumns(); i++) {
            ArrayList<Terrain> line_terrain = new ArrayList<>();
            for (int j = 0; j<gameInput.getRows();j++) {
                line_terrain.add(new Terrain(gameInput.getTerrainType()[i][j],i,j));
            }
            map.add(line_terrain);
        }
        return new TerrainMap(map);
    }

    public void playRound(int roundNumber) throws IllegalAccessException {

        char [] moves = gameInput.getMoves().get(roundNumber).toCharArray();
       // System.out.println(moves);
        for (int i = 0; i < moves.length; i++) {
            int x_axis = players.get(i).getxCoordonate();
            int y_axis = players.get(i).getyCoordonate();
            players.get(i).leftTerrain(map.getTerrain(x_axis,y_axis));
          switch (moves[i]) {
              case 'R' :
                  players.get(i).joinTerrain(map.getTerrain(x_axis, y_axis + 1));
                  break;
              case 'L' :
                  players.get(i).joinTerrain(map.getTerrain(x_axis,y_axis - 1));
                  break;
              case 'U' :
                  players.get(i).joinTerrain(map.getTerrain(x_axis - 1, y_axis));
                  break;
              case 'D' :
                  players.get(i).joinTerrain(map.getTerrain(x_axis + 1, y_axis));
                  //TODO
                  break;
              case '_' :
                  players.get(i).joinTerrain(map.getTerrain(x_axis,y_axis));
                  break;
              default:
                  throw new IllegalAccessException("Symbol not found!");
          }
        }
            GameBattle.Battle(players);
    }


}
