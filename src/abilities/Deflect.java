package abilities;

import players.AbstractPlayer;
import players.Wizard;
import players.Rogue;
import players.Knight;
import players.Pyromancer;

public final class Deflect implements IAbility {


    private static final float BASE_PERCENT = 0.35f;
    private static final float PERCENT_PER_LEVEL = 0.02f;
    private static final float MAX_PERCENT = 0.7f;
    private static final float ROGUE_MODIFICATOR = 1.2f;
    private static final float KNIGHT_MODIFICATOR = 1.4f;
    private static final float PYROMANCER_MODIFICATOR = 1.3f;
    private static final float WIZARD_MODIFICATOR = 0f;
    private float percent = BASE_PERCENT;

    @Override
    public float damagewithoutmodifier(final AbstractPlayer attack, final AbstractPlayer defend) {

        return (defend.getDamageWithoutModifiers(attack) * Math.min(percent, MAX_PERCENT));
    }

    @Override
    public void levelUp() {
        percent += PERCENT_PER_LEVEL;
    }

    @Override
    public int totaldamage(final AbstractPlayer attack, final AbstractPlayer defend) {

        return Math.round((damagewithoutmodifier(attack, defend) * getLandModifier(attack)) * attack
                .modifierCalculator(this, defend));
    }

    @Override
    public float getLandModifier(final AbstractPlayer p) {
        float modifier = 1f;
        if (p.getCurrentTerrain() == p.getFavouriteTerrain()) {
            modifier *= p.getBonusPercent();
        }
        return modifier;
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
