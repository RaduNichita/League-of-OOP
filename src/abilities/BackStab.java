package abilities;

import players.AbstractPlayer;
import players.Wizard;
import players.Rogue;
import players.Knight;
import players.Pyromancer;
import map.TerrainType;

public final class BackStab implements IAbility {


    private static final TerrainType FAVORITE_TERRAIN = TerrainType.W;

    private static final float BASE_DAMAGE = 200f;
    private static final float DAMAGE_PER_LEVEL = 20f;
    private static final int CRITICAL_HIT = 3;

    private static final float ROGUE_MODIFICATOR = 1.2f;
    private static final float KNIGHT_MODIFICATOR = 0.9f;
    private static final float PYROMANCER_MODIFICATOR = 1.25f;
    private static final float WIZARD_MODIFICATOR = 1.25f;
    private static final float CRITICAL_MODIFICATOR = 1.5f;
    private float damage = BASE_DAMAGE;
    private int criticalRound = 0;


    @Override
    public float damagewithoutmodifier(final AbstractPlayer attack, final AbstractPlayer defend) {
        float multipilicator = 1f;
        if (attack.getCurrentTerrain() == FAVORITE_TERRAIN) {
            if (attack.getCritical() % CRITICAL_HIT == 0) {
                multipilicator *= CRITICAL_MODIFICATOR;
                criticalRound = criticalRound % CRITICAL_HIT;
            } else {
                criticalRound = 0;
            }
        }
        criticalRound++;

        return 1f * Math
                .round(((BASE_DAMAGE + attack.getLevel() * DAMAGE_PER_LEVEL) * multipilicator));
    }

    @Override
    public void levelUp() {
        damage += DAMAGE_PER_LEVEL;
    }

    @Override
    public int totaldamage(final AbstractPlayer attack, final AbstractPlayer defend) {
        return Math.round(Math
                .round(damagewithoutmodifier(attack, defend) * getLandModifier(attack)) * attack
                .modifierCalculator(this, defend));
    }

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
