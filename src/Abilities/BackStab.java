package Abilities;

import Players.*;
import Terrain.TerrainType;

public class BackStab implements IAbility {


    private static final char FavoriteTerrain = 'W';

    private static final int BASE_DAMAGE = 200;
    private int damage = BASE_DAMAGE;
    private static final int DAMAGE_PER_LEVEL = 20;
    private static final int CRITICAL_HIT = 3;

    private static final float ROGUE_MODIFICATOR = 1.2f;
    private static final float KNIGHT_MODIFICATOR =  0.9f;
    private static final float PYROMANCER_MODIFICATOR = 1.25f;
    private static final float WIZARD_MODIFICATOR = 1.25f;

    @Override
    public int PostFightDamage() {
        return 0;
    }

    private static final float CRITICAL_MODIFICATOR = 1.5f;
    private int critical_round = 0;


    @Override
    public int DamageWithoutModifier(AbstractPlayer player) {

        float multiplicator = 1f;
        if (player.getCurrent_terrain().getType() == FavoriteTerrain) {
            if (critical_round % CRITICAL_HIT == 0) {
                    multiplicator *= CRITICAL_MODIFICATOR;
                    critical_round = critical_round % CRITICAL_HIT;
            }
        } else {
            critical_round = 0;
        }
        critical_round++;
        if (player.getCurrent_terrain().getType() == player.getFavouriteTerrain()) {
            multiplicator *= player.getBonusPercent();
        }
        return Math.round(damage * multiplicator);
    }


    @Override
    public float opponent(Knight k) {

        return KNIGHT_MODIFICATOR;
    }

    @Override
    public float opponent(Wizard w) {


        return WIZARD_MODIFICATOR;
    }

    @Override
    public float opponent(Pyromancer p) {

        return PYROMANCER_MODIFICATOR;
    }

    @Override
    public float opponent(Rogue r) { ;
        return ROGUE_MODIFICATOR;
    }
}
