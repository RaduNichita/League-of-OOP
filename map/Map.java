package map;

import heroes.Hero;

public final class Map {

    private static Map instance = null;

    private static TerrainCell[][] terraintype;

    private static int rows;

    private static int columns;

    public static int getRows() {
        return rows;
    }

    public static int getColumns() {
        return columns;
    }

    private Map() {

    }


    public static void setRows(final int rows) {
        Map.rows = rows;
    }

    public static void setColumns(final int columns) {
        Map.columns = columns;
    }

    public static void setDimensions(final int rowsDimension, final int columnsDimension) {
        terraintype = new TerrainCell[rowsDimension][columnsDimension];
        setRows(rowsDimension);
        setColumns(columnsDimension);

    }

    public static void setTerrain(final int i, final int j, final char terrain) {
        terraintype[i][j] = new TerrainCell(i, j, TerrainType.valueOf(String.valueOf(terrain)));
    }

    public static void joinCell(final Hero p) {
        terraintype[p.getxCoordonate()][p.getyCoordonate()].joinCell(p);
    }

    public static void leaveCell(final Hero p) {
        terraintype[p.getxCoordonate()][p.getyCoordonate()].leaveCell(p);
    }
    public static TerrainType getType(final int i, final int j) {
        return terraintype[i][j].getType();
    }

    public static TerrainCell getCell(final int i, final int j) {
        return terraintype[i][j];
    }

    public static Map getInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }


}
