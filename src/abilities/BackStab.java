package abilities;

import players.AbstractPlayer;
import players.Wizard;
import players.Rogue;
import players.Knight;
import players.Pyromancer;

public final class BackStab implements IAbility {


    private static final char FAVORITE_TERRAIN = 'W';

    private static final float BASE_DAMAGE = 200;
    private static final int DAMAGE_PER_LEVEL = 20;
    private static final int CRITICAL_HIT = 3;

    private static final float ROGUE_MODIFICATOR = 1.2f;
    private static final float KNIGHT_MODIFICATOR =  0.9f;
    private static final float PYROMANCER_MODIFICATOR = 1.25f;
    private static final float WIZARD_MODIFICATOR = 1.25f;



    private static final float CRITICAL_MODIFICATOR = 1.5f;
    private int criticalRound = 0;


    @Override
    public float damagewithoutmodifier(final AbstractPlayer attack, final AbstractPlayer defend) {
        float multipilicator = 1f;
        if (attack.getCurrentTerrain().getType() == FAVORITE_TERRAIN) {
            if (attack.getCritical() % CRITICAL_HIT == 0) {
                multipilicator *= CRITICAL_MODIFICATOR;
                criticalRound = criticalRound % CRITICAL_HIT;
            } else {
                criticalRound = 0;
            }
        }
        if (attack.getCurrentTerrain().getType() == attack.getFavouriteTerrain()) {
            multipilicator *= attack.getBonusPercent();
        }
         criticalRound++;
        return Math.round(BASE_DAMAGE * multipilicator);
    }

    @Override
    public float getLandModifier(final AbstractPlayer p) {
       return 1f;
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
