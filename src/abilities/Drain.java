package abilities;

import heroes.Hero;
import heroes.Wizard;
import heroes.Rogue;
import heroes.Knight;
import heroes.Pyromancer;

public final class Drain implements IAbility {


    private static final float BASE_PERCENT = 0.2f;
    private static final float PERCENT_PER_LEVEL = 0.05f;
    private static final float HP_PERCENT = 0.3f;

    private static final float ROGUE_MODIFICATOR = -0.2f;
    private static final float KNIGHT_MODIFICATOR = +0.2f;
    private static final float PYROMANCER_MODIFICATOR = -0.1f;
    private static final float WIZARD_MODIFICATOR = 0.05f;

    private float percent = BASE_PERCENT;


    @Override
    public float getLandModifier(final Hero p) {
        float modificator = 1f;
        if (p.getCurrentTerrain() == p.getFavouriteTerrain()) {
            modificator *= p.getBonusPercent();
        }
        return modificator;
    }

    @Override
    public float damagewithoutmodifier(final Hero attack, final Hero defend) {
        return Math.min(HP_PERCENT * defend.getMaxHP(),
                defend.getCurrentHp());

    }

    @Override
    public void levelUp() {
        percent += PERCENT_PER_LEVEL;
    }

    @Override
    public int totaldamage(final Hero attack, final Hero defend) {
        return Math.round(Math.round(damagewithoutmodifier(attack,
                defend) * getLandModifier(attack)) * (percent + percent * attack
                .modifierCalculator(this, defend)));
    }

    @Override
    public float inContactWith(final Knight k) {
        return KNIGHT_MODIFICATOR;
    }

    @Override
    public float inContactWith(final Wizard w) {
        return WIZARD_MODIFICATOR;
    }

    @Override
    public float inContactWith(final Pyromancer p) {
        return PYROMANCER_MODIFICATOR;
    }


    @Override
    public float inContactWith(final Rogue r) {
        return ROGUE_MODIFICATOR;
    }
}
