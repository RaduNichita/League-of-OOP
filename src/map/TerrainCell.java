package map;

import players.AbstractPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public final class TerrainCell {

    private int xAxis;
    private int yAxis;
    private ArrayList<AbstractPlayer> playersList;
    private TerrainType type;


    TerrainCell(final int x, final int y, final TerrainType type) {
        this.xAxis = x;
        this.yAxis = y;
        this.type = type;
        playersList = new ArrayList<>();
    }

    void joinCell(final AbstractPlayer player) {
        playersList.add(player);
    }

    void leaveCell(final AbstractPlayer player) {
        playersList.remove(player);
    }

    public ArrayList<AbstractPlayer> getPlayersList() {
        Collections.sort(playersList, new Comparator<AbstractPlayer>() {
            @Override
            public int compare(final AbstractPlayer o1, final AbstractPlayer o2) {
                return o1.getPlayerId() - o2.getPlayerId();
            }
        });
        return playersList;
    }

    public TerrainType getType() {
        return type;
    }
}
