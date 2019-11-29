package Terrain;

import Players.AbstractPlayer;

import java.util.ArrayList;

public  class Terrain {


    protected int currentPlayers;

    public char getType() {
        return type;
    }

    public ArrayList<AbstractPlayer> getPlayers() {
        return players;
    }

    protected char type;
    protected int x_axis;
    protected int y_axis;
    ArrayList<AbstractPlayer> players = new ArrayList<>();

    public Terrain(char type, int x, int y) {
        this.type = type;
        this.x_axis = x;
        this.y_axis = y;
    }

    public final boolean isOnePlayer() {
        return (players.size() == 1);
    }

    public final void leftTerrain(AbstractPlayer p) {
         players.remove(p);
    }

    public int getX_axis() {
        return x_axis;
    }

    public int getY_axis() {
        return y_axis;
    }

    public final void joinTerrain(AbstractPlayer p) {
       // System.out.println("Player joined" + "x: " + x_axis + "y:" + y_axis );
        players.add(p);

    }
}
