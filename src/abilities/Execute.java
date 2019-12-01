package abilities;

import players.AbstractPlayer;
import players.Wizard;
import players.Rogue;
import players.Knight;
import players.Pyromancer;

public final class Execute implements IAbility {


    private static final int BASE_DAMAGE = 200;
    private static final int DAMAGE_PER_LEVEL = 30;

    private static final float ROGUE_MODIFICATOR = 1.15f;
    private static final float KNIGHT_MODIFICATOR =  1.0f;
    private static final float PYROMANCER_MODIFICATOR = 1.1f;
    private static final float WIZARD_MODIFICATOR = 0.8f;

    private static final float MIN_HP_LIMIT = 0.2f;
    private static final float MAX_HP_LIMIT = 0.4f;
    private static final float HP_PERCENT = 0.01f;

    @Override
    public float damagewithoutmodifier(final AbstractPlayer attack, final AbstractPlayer defend) {
        if ((float) defend.getCurrentHp() / defend.getMaxHP() < Math.min(MIN_HP_LIMIT,
                 MAX_HP_LIMIT)) {
            defend.receiveDamage(defend.getCurrentHp());
            return defend.getCurrentHp();
        } else {
            return BASE_DAMAGE;
        }
    }

    @Override
    public float opponent(final Knight k) {
        return KNIGHT_MODIFICATOR;
    }

    @Override
    public float getLandModifier(final AbstractPlayer p) {
        float modifier = 1f;
        if (p.getCurrentTerrain().getType() == p.getFavouriteTerrain()) {
            modifier *= p.getBonusPercent();
        }
        return modifier;
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
