package abilities;

import players.AbstractPlayer;
import players.Wizard;
import players.Rogue;
import players.Knight;
import players.Pyromancer;

public final class Ignite implements IAbility {


    private static final float BASE_DAMAGE = 150f;
    private static final float DAMAGE_PER_LEVEL = 20f;
    private static final float OVERTIME_DAMAGE = 50f;
    private static final float DAMAGE_PER_LEVEL_OVERTIME = 30f;
    private static final float ROGUE_MODIFICATOR = 0.8f;
    private static final float KNIGHT_MODIFICATOR = 1.2f;
    private static final float PYROMANCER_MODIFICATOR = 0.9f;
    private static final float WIZARD_MODIFICATOR = 1.05f;
    private static final int OVERTIME = 2;
    private float damage = BASE_DAMAGE;
    private float overtimeDamage = OVERTIME_DAMAGE;

    @Override
    public float getLandModifier(final AbstractPlayer p) {
        float modifier = 1f;
        if (p.getCurrentTerrain() == p.getFavouriteTerrain()) {
            modifier *= p.getBonusPercent();
        }
        return modifier;
    }

    @Override
    public float damagewithoutmodifier(final AbstractPlayer attack, final AbstractPlayer defend) {
        defend.setDebuff(new Debuff(OVERTIME,
                Math.round(Math.round(getLandModifier(attack) * (overtimeDamage)) * attack
                        .modifierCalculator(this, defend)),
                false));
        return damage;
    }

    @Override
    public void levelUp() {
        damage += DAMAGE_PER_LEVEL;
        overtimeDamage += DAMAGE_PER_LEVEL_OVERTIME;
    }

    @Override
    public int totaldamage(final AbstractPlayer attack, final AbstractPlayer defend) {
        return Math.round(Math
                .round(damagewithoutmodifier(attack, defend) * getLandModifier(attack)) * attack
                .modifierCalculator(this, defend));
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
