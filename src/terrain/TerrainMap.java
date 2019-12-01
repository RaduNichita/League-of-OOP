package terrain;

import java.util.ArrayList;

public final class TerrainMap {


    private final int rows;
    private final int columns;
    private final ArrayList<ArrayList<Terrain>> map;

    public TerrainMap(final ArrayList<ArrayList<Terrain>> map) {
        this.map = map;
        this.rows = map.size();
        this.columns = map.get(0).size();
    }

    public Terrain getTerrain(final int x, final int y) {
        try {
            return map.get(x).get(y);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
