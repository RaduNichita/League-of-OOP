package abilities;

import players.AbstractPlayer;
import players.Wizard;
import players.Rogue;
import players.Knight;
import players.Pyromancer;

public final class Drain implements IAbility {


    private static final float BASE_PERCENT = 0.2f;
    private final float percent  = BASE_PERCENT;
    private static final float PERCENT_PER_LEVEL = 0.05f;
    private static final float HP_PERCENT = 0.3f;

    private static final float ROGUE_MODIFICATOR = -0.2f;
    private static final float KNIGHT_MODIFICATOR =  +0.2f;
    private static final float PYROMANCER_MODIFICATOR = -0.1f;
    private static final float WIZARD_MODIFICATOR = 0.05f;


    @Override
    public float getLandModifier(final AbstractPlayer p) {
        float modificator = 1f;
        if (p.getCurrentTerrain().getType() == p.getFavouriteTerrain()) {
            modificator *= p.getBonusPercent();
        }
        return modificator;
    }

    @Override
    public float damagewithoutmodifier(final AbstractPlayer attack, final AbstractPlayer defend) {
        return (Math.min(HP_PERCENT * defend.getMaxHP(),
                defend.getCurrentHp()));

    }

    @Override
    public float opponent(final Knight k) {
        return percent + KNIGHT_MODIFICATOR * percent;
    }

    @Override
    public float opponent(final Wizard w) {
        return percent + WIZARD_MODIFICATOR * percent;
    }

    @Override
    public float opponent(final Pyromancer p) {
        return percent + PYROMANCER_MODIFICATOR * percent;
    }


    @Override
    public float opponent(final Rogue r) {
       // System.out.println(percent + ROGUE_MODIFICATOR * percent);
        return percent + ROGUE_MODIFICATOR * percent;
    }
}
