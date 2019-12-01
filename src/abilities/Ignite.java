package abilities;

import players.AbstractPlayer;
import players.Wizard;
import players.Rogue;
import players.Knight;
import players.Pyromancer;

public final class Ignite implements IAbility {


    private static final int BASE_DAMAGE = 150;
    private int damage = BASE_DAMAGE;
    private static final int OVERTIME_DAMAGE = 50;
    private static final int DAMAGE_PER_LEVEL = 30;

    private static final float ROGUE_MODIFICATOR = 0.8f;
    private static final float KNIGHT_MODIFICATOR =  1.2f;
    private static final float PYROMANCER_MODIFICATOR = 0.9f;
    private static final float WIZARD_MODIFICATOR = 1.05f;

    private static final int OVERTIME = 2;


    @Override
    public float getLandModifier(final AbstractPlayer p) {
        float modifier = 1f;
        if (p.getCurrentTerrain().getType() == p.getFavouriteTerrain()) {
            modifier *= p.getBonusPercent();
        }
        return modifier;
    }

    @Override
    public float damagewithoutmodifier(final AbstractPlayer attack, final AbstractPlayer defend) {
        defend.setDebuff(new Debuff(OVERTIME,
                Math.round(getLandModifier(attack) * OVERTIME_DAMAGE * defend.requestModifier(
                        this)), false));
        return damage;
    }

    @Override
    public float opponent(final Knight k) {
        return KNIGHT_MODIFICATOR;
    }

    @Override
    public float opponent(final Wizard w) {
        return WIZARD_MODIFICATOR;
    }

    @Override
    public float opponent(final Pyromancer p) {
        return PYROMANCER_MODIFICATOR;
    }

    @Override
    public float opponent(final Rogue r) {
        return ROGUE_MODIFICATOR;
    }
}
