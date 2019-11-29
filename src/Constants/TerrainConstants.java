package Constants;

public final class TerrainConstants {
    private static TerrainConstants instance = null;



    public static TerrainConstants getInstance() {
        if (instance == null) {
            return new TerrainConstants();
        } else {
            return instance;
        }
    }
}
