package angels;

public final class AngelsFactory {

    private AngelsFactory() {

    }

    /**
     * Design of factory pattern to create angels.
     * @param type type of angels provided by input file.
     * @param x x-Axis Coordinate of angel.
     * @param y y-Axis Coordinate of angel.
     * @return an angel of type requested  at (x,y) position or throws error in case there is no
     * such angel.
     */
    public static Angel createAngel(final String type, final int x, final int y) {
        switch (type) {
            case "LifeGiver":
                return new LifeGiver(x, y);
            case "LevelUpAngel":
                return new LevelUpAngel(x, y);
            case "TheDoomer":
                return new TheDoomer(x, y);
            case "DamageAngel":
                return new DamageAngel(x, y);
            case "Dracula":
                return new Dracula(x, y);
            case "XPAngel":
                return new XPAngel(x, y);
            case "Spawner":
                return new Spawner(x, y);
            case "SmallAngel":
                return new SmallAngel(x, y);
            case "GoodBoy":
                return new GoodBoy(x, y);
            case "DarkAngel":
                return new DarkAngel(x, y);

            default:
                throw new IllegalStateException("No such angel");
        }
    }
}
