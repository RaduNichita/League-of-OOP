package heroes;

public final class HeroFactory {

    private HeroFactory() {

    }

    /**
     * Factory pattern for creating new Players.
     *
     * @param type the String literal for a player type
     * @param x - initial X-axis Coordonate of a Player.
     * @param y - iniitial Y-axis Coordonate of a Player.
     * @return the specific type of a Player.
     * @throws IllegalStateException if there is no match with current type of players.
     */
    public static Hero getPlayer(final String type, final int x, final int y) throws
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
