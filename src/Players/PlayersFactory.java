package Players;

public final class PlayersFactory {

    private PlayersFactory() {

    }

    public static AbstractPlayer getPlayer(String type,int x, int y) throws IllegalStateException {
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
