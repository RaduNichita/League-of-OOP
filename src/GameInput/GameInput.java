package GameInput;

import java.util.ArrayList;

public final class GameInput {

    private int rows;
    private int columns;
    private int rounds;
    private char [][] TerrainType;
    private ArrayList<Integer>  initialPosition;
    private ArrayList<String> moves;
    private ArrayList<String> players;

    private GameInput() {

    }
    public static class Builder {

        int rows;
        int columns;
        int rounds;
        char [][] TerrainType;
        ArrayList<Integer>  initialPosition;
        ArrayList<String> moves;
        ArrayList<String> players;

        public Builder() {
        }
        public Builder atDimensions(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
            return this;
        }

        public Builder atRounds(int rounds) {
            this.rounds = rounds;
            return this;
        }

        public Builder atMap(char [][] TerrainType) {
            this.TerrainType = TerrainType;
            return this;
        }

        public Builder atCoordonates(ArrayList<Integer> initialPosition) {
            this.initialPosition = initialPosition;
            return this;
        }
        public Builder atMoves(ArrayList<String> moves) {
            this.moves = moves;
            return this;
        }

        public Builder atPlayers(ArrayList<String> players) {
            this.players = players;
            return this;
        }

        public GameInput build() {
            GameInput gameInput = new GameInput();
            gameInput.rows = this.rows;
            gameInput.columns = this.columns;
            gameInput.TerrainType = this.TerrainType;
            gameInput.initialPosition = this.initialPosition;
            gameInput.moves = this.moves;
            gameInput.players = this.players;
            gameInput.rounds = this.rounds;
            return gameInput;
        }
    }

    public final int getRows() {
        return rows;
    }

    public final int getColumns() { return columns; }

    public final int getRounds() {
        return rounds;
    }

    public final char[][] getTerrainType() {
        return TerrainType;
    }

    public final ArrayList<Integer> getInitialPosition() {
        return initialPosition;
    }

    public final ArrayList<String> getMoves() {
        return moves;
    }

    public final ArrayList<String> getPlayers() {
        return players;
    }
}
