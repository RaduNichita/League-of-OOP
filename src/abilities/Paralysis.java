package abilities;


import players.AbstractPlayer;
import players.Wizard;
import players.Rogue;
import players.Knight;
import players.Pyromancer;

public final class Paralysis implements IAbility {


    private static final int BASE_DAMAGE = 40;
    private static final int DAMAGE_PER_LEVEL = 10;
    private int damage = BASE_DAMAGE;

    private static final float ROGUE_MODIFICATOR = 0.9f;
    private static final float KNIGHT_MODIFICATOR = 0.8f;
    private static final float PYROMANCER_MODIFICATOR = 1.2f;
    private static final float WIZARD_MODIFICATOR = 1.25f;

    private static final int OVERTIMEROUNDS = 3;
    private static final int WOODSOVERTIMEROUNDS = 6;


    @Override
    public float getLandModifier(final AbstractPlayer p) {
        float modifier = 1f;
        if (p.getCurrentTerrain().getType() == p.getFavouriteTerrain()) {
            modifier *= p.getBonusPercent();
        }
        return modifier;
    }

    public float damagewithoutmodifier(final AbstractPlayer attack, final AbstractPlayer defend) {
        defend.setDebuff(
                new Debuff((attack.getCurrentTerrain().getType() == attack.getFavouriteTerrain())
                        ? WOODSOVERTIMEROUNDS : OVERTIMEROUNDS, (Math.round(
                                damage * getLandModifier(attack)
                                        * defend.requestModifier(this))),
                        true));
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
