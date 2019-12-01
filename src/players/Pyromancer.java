package players;

import abilities.FireBlast;
import abilities.IAbility;
import abilities.Ignite;

public final class Pyromancer extends AbstractPlayer {


    private static final int MAX_HP = 500;
    private static final int BONUSHP = 50;
    private static final float BONUS_PERCENT = 1.25f;
    private static final char FAVOURITE_TERRAIN = 'V';

    Pyromancer(final int x, final int y) {
        super(x, y);
        addAbility(new FireBlast());
        addAbility(new Ignite());
        super.setCurrentHp(MAX_HP);
    }

    @Override
    public char getFavouriteTerrain() {
        return FAVOURITE_TERRAIN;
    }


    @Override
    public float requestModifier(final IAbility ability) {
        return ability.opponent(this);
    }

    @Override
    public int getMaxHP() {
        return MAX_HP + level * BONUSHP;
    }

    @Override
    public float getBonusPercent() {
        return BONUS_PERCENT;
    }

    @Override
    public int isAttacked(final AbstractPlayer abstractPlayer) {
        return abstractPlayer.getTotalDamage(this);

    }
}
