package players;

public final class PlayersFactory {

    private PlayersFactory() {

    }

    public static AbstractPlayer getPlayer(final String type, final int x, final int y) throws
            IllegalStateException {
        switch (type) {
            case "W":
                return new Wizard(x, y);
            case "R":
                return new Rogue(x, y);
            case "P":
                return new Pyromancer(x, y);
            case "K":
                return new Knight(x, y);
            default:
                throw new IllegalStateException("No such player");
        }
    }


}
