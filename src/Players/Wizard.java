package Players;

import Abilities.Drain;
import Abilities.IAbility;

import java.util.ArrayList;

public class Wizard extends AbstractPlayer {


    private int hp = 400;
    private static final int extraHP = 30;
    public final char favouriteTerrain = 'D';
    public final float bonusPercent = 1.1f;
    private ArrayList<IAbility> arrayList = new ArrayList<>();

    @Override
    public float requestModifier(IAbility ability) {
        return ability.opponent(this);
    }

    @Override
    public int getMaxHP() {
        return 0;
    }

    Wizard(int x, int y) {
        super(x, y);
        this.arrayList.add(new Drain());
    }

    @Override
    public void isAttacked(AbstractPlayer abstractPlayer) {
        abstractPlayer.getTotalDamage(this);
    }

    @Override
    public void attack(Knight knight) {

    }

    @Override
    public void attack(Rogue rogue) {
        System.out.println("Wizard attack Rogue ");
    }

    @Override
    public void attack(Pyromancer pyromancer) {

    }

    @Override
    public void attack(Wizard wizard) {
        //System.out.println("Wizard attack wizard");
    }

    @Override
    protected float getTerrainBonus() {
        return 0;
    }


}
