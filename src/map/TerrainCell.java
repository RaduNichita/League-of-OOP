package map;

import heroes.Hero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public final class TerrainCell {

    private int xAxis;
    private int yAxis;
    private ArrayList<Hero> playersList;
    private TerrainType type;


    TerrainCell(final int x, final int y, final TerrainType type) {
        this.xAxis = x;
        this.yAxis = y;
        this.type = type;
        playersList = new ArrayList<>();
    }

    void joinCell(final Hero player) {
        playersList.add(player);
    }

    void leaveCell(final Hero player) {
        playersList.remove(player);
    }

    public ArrayList<Hero> getPlayersList() {
        Collections.sort(playersList, Comparator.comparingInt(Hero::getPlayerId));
        return playersList;
    }

    public TerrainType getType() {
        return type;
    }
}
