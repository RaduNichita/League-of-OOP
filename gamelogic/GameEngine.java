package gamelogic;

import angels.Angel;
import angels.AngelsFactory;
import designpatterns.GreatMagician;
import gameinput.GameInput;
import gameoutput.GameOutput;
import map.Map;
import heroes.Hero;
import heroes.HeroStatus;
import heroes.HeroFactory;

import java.util.ArrayList;
import java.util.Iterator;

public final class GameEngine {

    private final GameInput gameInput;
    private final GameOutput gameOutput;
    private final ArrayList<Hero> players = new ArrayList<>();

    public GameEngine(final GameInput gameInput, final GameOutput gameoutput) {
        this.gameInput = gameInput;
        this.gameOutput = gameoutput;
    }

    /**
     * Process input data into data structures and begin the game.
     *
     * @throws IllegalAccessException
     */
    public void processData() throws IllegalAccessException {

        createMap();

        Iterator<Integer> iterator = gameInput.getInitialPosition().iterator();
        int currentId = 0;
        for (String s : gameInput.getPlayers()) {
            int xAxis = iterator.next();
            int yAxis = iterator.next();
            Hero newPlayer = HeroFactory.getPlayer(s, xAxis, yAxis);
            newPlayer.setPlayerId(currentId);
            currentId++;
            GreatMagician.getInstance().addObservable(newPlayer);
            newPlayer.joinTerrain(xAxis, yAxis);
            players.add(newPlayer);
        }

        for (int i = 0; i < gameInput.getRounds(); i++) {
            playRound(i);
        }
    }

    /**
     * Map creation.
     *
     * @return map;
     */
    private Map createMap() {
        Map map = Map.getInstance();
        Map.setDimensions(gameInput.getRows(), gameInput.getColumns());
        for (int i = 0; i < gameInput.getRows(); i++) {
            for (int j = 0; j < gameInput.getColumns(); j++) {
                Map.setTerrain(i, j, gameInput.getTerrainType()[i][j]);
            }
        }
        return map;
    }


    /**
     * For every round, if there are any debuff, the function applies them. After that, player
     * will move to the next position. After all moves have been made, it is checked which
     * players will battle.
     *
     * @param roundNumber -> current Round
     * @throws IllegalAccessException
     */
    public void playRound(final int roundNumber) throws IllegalAccessException {

        writeStart(roundNumber);

        for (Hero p : players) {
            p.preFightDamage();
        }


        System.out.println("hp before battle" + players);
        gameMovement(roundNumber);

        GameBattle.battle(players);


        spawnAngels(roundNumber);
        writeEnd();

    }

    private void writeEnd() {
        gameOutput.writeRoundEnding();
    }

    private void writeStart(final int roundNumber) {
        gameOutput.writeRoundBeginning(roundNumber);
    }

    private void writeSpawnMessage(final Angel angel) {
        angel.spawnNotification();
    }

    private void writeHelpMessage(final Angel angel, final Hero player) {
        angel.helpNotification(player);
    }

    private void writeKillMessage(final Hero p) {
        p.killNotification();
    }

    private void gameMovement(final int roundNumber) throws IllegalAccessException {
        char[] moves = gameInput.getMoves().get(roundNumber).toCharArray();
        for (int i = 0; i < moves.length; i++) {
            int xAxis = players.get(i).getxCoordonate();
            int yAxis = players.get(i).getyCoordonate();
            if (!players.get(i).isStuned()) {
                players.get(i).strategyChoice();
                switch (moves[i]) {
                    case 'R':
                        players.get(i).leftTerrain();
                        players.get(i).joinTerrain(xAxis, yAxis + 1);
                        break;
                    case 'L':
                        players.get(i).leftTerrain();
                        players.get(i).joinTerrain(xAxis, yAxis - 1);
                        break;
                    case 'U':
                        players.get(i).leftTerrain();
                        players.get(i).joinTerrain(xAxis - 1, yAxis);
                        break;
                    case 'D':
                        players.get(i).leftTerrain();
                        players.get(i).joinTerrain(xAxis + 1, yAxis);
                        break;
                    case '_':
                        break;
                    default:
                        throw new IllegalAccessException("Symbol not found!");
                }
            }
        }
    }

    /**
     * For each round angels are spawned according to input round number. Then, they apply their
     * effects on heroes located in the same Map Cell.
     * @param roundNumber
     */
    private void spawnAngels(final int roundNumber) {
        if (gameInput.getAngelTypes().get(roundNumber).size() != 0) {
            ArrayList<Angel> helpers = new ArrayList<>();
            Iterator<Integer> coordinateIterator =
                    gameInput.getAngelCoordinates().get(roundNumber).iterator();
            for (String s : gameInput.getAngelTypes().get(roundNumber)) {
                int xAxis = coordinateIterator.next();
                int yAxis = coordinateIterator.next();
                helpers.add(AngelsFactory.createAngel(s, xAxis, yAxis));
                GreatMagician.getInstance().addSecondObservable(helpers.get(helpers.size() - 1));
            }


            for (Angel angel : helpers) {
                writeSpawnMessage(angel);
                int xAxis = angel.getxAxis();
                int yAxis = angel.getyAxis();
                for (Hero p : Map.getCell(xAxis, yAxis).getPlayersList()) {
                    HeroStatus oldStatus = p.getStatus();
                    if (p.getStatus() == HeroStatus.ALIVE && !angel.getClass().getSimpleName()
                            .equals("Spawner")) {
                        writeHelpMessage(angel, p);
                        p.acceptAngel(angel);
                        if (oldStatus == HeroStatus.ALIVE && p
                                .getStatus() == HeroStatus.DEAD) {
                            writeKillMessage(p);
                        }
                    } else if (p.getStatus() == HeroStatus.DEAD && angel.getClass()
                            .getSimpleName().equals("Spawner")) {
                        writeHelpMessage(angel, p);
                        p.acceptAngel(angel);
                    }
                }
            }
            GreatMagician.getInstance().resetAngels();
        }


    }


    public ArrayList<Hero> finalScore() {
        return players;
    }


}
