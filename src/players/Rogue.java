package players;

import abilities.BackStab;
import abilities.IAbility;
import abilities.Paralysis;

public final class Rogue extends AbstractPlayer {


    private static final int MAX_HP = 600;
    private static final int bonushp = 40;
    public static final char FAVOURITE_TERRAIN = 'W';
    public static final float BONUS_PERCENT = 1.15f;
    private int CRITICALROUND = 0;

    @Override
    public char getFavouriteTerrain() {
        return this.FAVOURITE_TERRAIN;
    }

    @Override
    public float getBonusPercent() {
        return BONUS_PERCENT;
    }


    @Override
    public int getMaxHP() {
        return MAX_HP + bonushp * level;
    }

    @Override
    public int getCritical() {
        return CRITICALROUND;
    }

    @Override
    public void update() {
        CRITICALROUND++;
    }

    @Override
    public float requestModifier(final IAbility ability) {
        return ability.opponent(this);
    }

    Rogue(int x, int y) {
        super(x, y);
        addAbility(new BackStab());
        addAbility(new Paralysis());
        super.setCurrentHp(MAX_HP);
    }

    @Override
    public int isAttacked(AbstractPlayer abstractPlayer) {
        return abstractPlayer.getTotalDamage(this);
    }
}
