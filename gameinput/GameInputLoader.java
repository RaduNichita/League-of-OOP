package gameinput;

import fileio.FileSystem;

import java.util.ArrayList;
import java.util.Collections;


public final class GameInputLoader {
    private final String inputPath;
    private final String outputPath;

    public GameInputLoader(final String inputPath, final String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public GameInput load() {
        int rows;
        int columns;
        int noPlayers;
        int noRounds;
        ArrayList<String> playerstypes = new ArrayList<>();
        ArrayList<Integer> playerPositions = new ArrayList<>();
        ArrayList<String> moves = new ArrayList<>();
        ArrayList<Integer> roundHelpers = new ArrayList<>();
        ArrayList<ArrayList<String>> angeltypes = new ArrayList<>();
        ArrayList<ArrayList<Integer>> angelPositions = new ArrayList<>();
        char[][] terrainTypes;
        try {
            FileSystem fs = new FileSystem(inputPath, outputPath);
            rows = fs.nextInt();
            columns = fs.nextInt();
            terrainTypes = new char[rows][columns];
            for (int i = 0; i < rows; i++) {
                String line = fs.nextWord();
                for (int j = 0; j < columns; j++) {
                    terrainTypes[i][j] = line.charAt(j);
                }
            }
            noPlayers = fs.nextInt();
            for (int i = 0; i < noPlayers; i++) {
                playerstypes.add(fs.nextWord());
                Collections.addAll(playerPositions, fs.nextInt(), fs.nextInt());
            }
            noRounds = fs.nextInt();
            for (int i = 0; i < noRounds; i++) {
                moves.add(fs.nextWord());
            }

            for (int i = 0; i < noRounds; i++) {
                int currentRoundHelpers = fs.nextInt();
                roundHelpers.add(currentRoundHelpers);

                ArrayList<Integer> currentCoordinates = new ArrayList<>();
                ArrayList<String> currentTypes = new ArrayList<>();
                for (int j = 0; j < currentRoundHelpers; j++) {
                    String line = fs.nextWord();
                    String[] splitted = line.split(",");
                    Collections.addAll(currentTypes, splitted[0]);
                    Collections.addAll(currentCoordinates, Integer.parseInt(splitted[1]),
                            Integer.parseInt(splitted[2]));
                }
                angeltypes.add(currentTypes);
                angelPositions.add(currentCoordinates);


            }
            fs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new GameInput.Builder().atRounds(noRounds).atCoordonates(playerPositions)
                .atDimensions(rows, columns).atMap(terrainTypes).atMoves(moves)
                .atPlayers(playerstypes).atAngelPosition(angelPositions).atRoundAngels(roundHelpers)
                .atAngelsTypes(angeltypes).build();

    }

}
