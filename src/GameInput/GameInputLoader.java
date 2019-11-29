package GameInput;

import fileio.FileSystem;

import java.util.ArrayList;
import java.util.Collections;


public final class GameInputLoader
{
    private final String inputPath;
    private final String outputPath;

    public GameInputLoader(final String inputPath, final String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public GameInput load() {
        int rows = 0;
        int columns = 0;
        int noPlayers = 0;
        int noRounds = 0;
        ArrayList<String> playerstypes = new ArrayList<>();
        ArrayList<Integer> playerPositions = new ArrayList<>();
        ArrayList<String> moves = new ArrayList<>();
        char [][]TerrainTypes;

        try {
            FileSystem fs = new FileSystem(inputPath,outputPath);
            rows = fs.nextInt();
            columns = fs.nextInt();
            TerrainTypes = new char[rows][columns];
            for (int i = 0; i < rows; i++) {
                String line = fs.nextWord();
                for (int j = 0; j<columns;j++) {
                    TerrainTypes[i][j] = line.charAt(j);
                }
            }
            noPlayers = fs.nextInt();
            for (int i = 0; i<noPlayers;i++){
              playerstypes.add(fs.nextWord());
              Collections.addAll(playerPositions,fs.nextInt(),fs.nextInt());
            }
            noRounds = fs.nextInt();
           // System.out.println(playerPositions);
            for (int i = 0; i<noRounds; i++) {
                moves.add(fs.nextWord());
            }
            fs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new GameInput.Builder().atRounds(noRounds).atCoordonates(playerPositions).atDimensions(rows,columns).atMap(TerrainTypes).atMoves(moves).atPlayers(playerstypes).build();

    }

}
