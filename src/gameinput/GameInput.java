package gameinput;

import java.util.ArrayList;

public final class GameInput {

    private int rows;
    private int columns;
    private int rounds;
    private char[][] terrainType;
    private ArrayList<Integer>  initialPosition;
    private ArrayList<String> moves;
    private ArrayList<String> players;

    private GameInput() {

    }
    public static final class Builder {

        private int rows;
        private int columns;
        private int rounds;
        private char[][] terrainType;
        private ArrayList<Integer>  initialPosition;
        private ArrayList<String> moves;
        private ArrayList<String> players;

        public Builder() {
        }
        public Builder atDimensions(final int rowsdimension, final int columnsdimension) {
            this.rows = rowsdimension;
            this.columns = columnsdimension;
            return this;
        }

        public Builder atRounds(final int roundsnumber) {
            this.rounds = roundsnumber;
            return this;
        }

        public Builder atMap(final char[][] map) {
            this.terrainType = map;
            return this;
        }

        public Builder atCoordonates(final ArrayList<Integer> initialposition) {
            this.initialPosition = initialposition;
            return this;
        }
        public Builder atMoves(final ArrayList<String> listofmoves) {
            this.moves = listofmoves;
            return this;
        }

        public Builder atPlayers(final ArrayList<String> currentPlayers) {
            this.players = currentPlayers;
            return this;
        }

        public GameInput build() {
            GameInput gameInput = new GameInput();
            gameInput.rows = this.rows;
            gameInput.columns = this.columns;
            gameInput.terrainType = this.terrainType;
            gameInput.initialPosition = this.initialPosition;
            gameInput.moves = this.moves;
            gameInput.players = this.players;
            gameInput.rounds = this.rounds;
            return gameInput;
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getRounds() {
        return rounds;
    }

    public char[][] getTerrainType() {
        return terrainType;
    }

    public ArrayList<Integer> getInitialPosition() {
        return initialPosition;
    }

    public ArrayList<String> getMoves() {
        return moves;
    }

    public ArrayList<String> getPlayers() {
        return players;
    }
}
