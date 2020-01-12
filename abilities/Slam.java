package abilities;

import heroes.Hero;
import heroes.Wizard;
import heroes.Rogue;
import heroes.Knight;
import heroes.Pyromancer;

public final class Slam implements IAbility {


    private static final float BASE_DAMAGE = 100f;
    private static final float DAMAGE_PER_LEVEL = 40f;
    private static final float ROGUE_MODIFICATOR = 0.8f;
    private static final float KNIGHT_MODIFICATOR = 1.2f;
    private static final float PYROMANCER_MODIFICATOR = 0.9f;
    private static final float WIZARD_MODIFICATOR = 1.05f;
    private float damage = BASE_DAMAGE;

    @Override
    public float getLandModifier(final Hero p) {
        float modifier = 1f;
        if (p.getCurrentTerrain() == p.getFavouriteTerrain()) {
            modifier *= p.getBonusPercent();
        }
        return modifier;
    }

    @Override
    public float damagewithoutmodifier(final Hero attack, final Hero defend) {
        defend.setDebuff(new Debuff(1, 0, true));
        return damage;
    }

    @Override
    public void levelUp() {
        damage += DAMAGE_PER_LEVEL;
    }

    @Override
    public int totaldamage(final Hero attack, final Hero defend) {
        return Math.round(Math
                .round(damagewithoutmodifier(attack, defend) * getLandModifier(attack)) * attack
                .modifierCalculator(this,
                        defend));
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
