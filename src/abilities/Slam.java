package abilities;

import players.AbstractPlayer;
import players.Wizard;
import players.Rogue;
import players.Knight;
import players.Pyromancer;

public final class Slam implements IAbility {


    private static final int BASE_DAMAGE = 100;
    private int damage = BASE_DAMAGE;
    private static final int DAMAGE_PER_LEVEL = 40;

    private static final float ROGUE_MODIFICATOR = 0.8f;
    private static final float KNIGHT_MODIFICATOR =  1.2f;
    private static final float PYROMANCER_MODIFICATOR = 0.9f;
    private static final float WIZARD_MODIFICATOR = 1.05f;


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
        defend.setDebuff(new Debuff(1, 0, true));
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
