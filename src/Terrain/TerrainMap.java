package Terrain;

import java.util.ArrayList;

public class TerrainMap {


    int rows;
    int columns;
    ArrayList<ArrayList<Terrain>> map;

    public TerrainMap(ArrayList<ArrayList<Terrain>> map) {
        this.map = map;
        this.rows = map.size();
        this.columns = map.get(0).size();
    }

    public Terrain getTerrain(int x, int y) {
        try {
            return map.get(x).get(y);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
