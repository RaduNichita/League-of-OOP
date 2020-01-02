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
    private ArrayList<Integer> roundAngels;
    private ArrayList<ArrayList<String>> angelTypes;
    private ArrayList<ArrayList<Integer>> angelCoordinates;

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
        private ArrayList<Integer> roundAngels;
        private ArrayList<ArrayList<String>> angelTypes;
        private ArrayList<ArrayList<Integer>> angelCoordinates;

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

        public Builder atRoundAngels(final ArrayList<Integer> roundsAngels) {
            this.roundAngels = roundsAngels;
            return this;
        }

        public Builder atAngelsTypes(final ArrayList<ArrayList<String>> angeltypes) {
            this.angelTypes = angeltypes;
            return this;
        }

        public Builder atAngelPosition(final ArrayList<ArrayList<Integer>> angelcoordinates) {
            this.angelCoordinates = angelcoordinates;
            return this;
        }

        /**
         * Build Pattern for dealing with gameinput.
         * @return
         */
        public GameInput build() {
            GameInput gameInput = new GameInput();
            gameInput.rows = this.rows;
            gameInput.columns = this.columns;
            gameInput.terrainType = this.terrainType;
            gameInput.initialPosition = this.initialPosition;
            gameInput.moves = this.moves;
            gameInput.players = this.players;
            gameInput.rounds = this.rounds;
            gameInput.roundAngels = this.roundAngels;
            gameInput.angelTypes = this.angelTypes;
            gameInput.angelCoordinates = this.angelCoordinates;
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

    public  ArrayList<ArrayList<String>> getAngelTypes() {
        return angelTypes;
    }

    public ArrayList<ArrayList<Integer>> getAngelCoordinates() {
        return angelCoordinates;
    }
}
