package players;

import abilities.Deflect;
import abilities.Drain;
import abilities.IAbility;


public final class Wizard extends AbstractPlayer {


    private final int maxhp = 400;
    private static final int BONUSHP = 30;
    private final float bonusPercent = 1.1f;
    private static final char FAVOURITE_TERRAIN = 'D';

    @Override
    public char getFavouriteTerrain() {
        return FAVOURITE_TERRAIN;
    }

    @Override
    public float getBonusPercent() {
        return bonusPercent;
    }

    @Override
    public float requestModifier(final IAbility ability) {
        return ability.opponent(this);
    }

    @Override
    public int getMaxHP() {
        return this.maxhp + BONUSHP * level;
    }

    Wizard(final int x, final int y) {
        super(x, y);
        super.setCurrentHp(maxhp);
        addAbility(new Drain());
        addAbility(new Deflect());
    }

    @Override
    public int isAttacked(final AbstractPlayer abstractPlayer) {
        return abstractPlayer.getTotalDamage(this);
    }




}
