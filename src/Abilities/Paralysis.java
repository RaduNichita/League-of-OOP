package Abilities;

import Players.*;

public class Paralysis implements IAbility {


    private static final char FavoriteTerrain = 'W';

    private static final int BASE_DAMAGE = 40;
    private int damage = BASE_DAMAGE;
    private static final int DAMAGE_PER_LEVEL = 10;
    private static final int CRITICAL_HIT = 3;

    private static final float ROGUE_MODIFICATOR = 0.9f;
    private static final float KNIGHT_MODIFICATOR =  0.8f;
    private static final float PYROMANCER_MODIFICATOR = 1.2f;
    private static final float WIZARD_MODIFICATOR = 1.25f;

    private static final int OVERTIMEROUNDS = 3;
    private static final int WOODSOVERTIMEROUNDS = 6;



    public int DamageWithoutModifier(AbstractPlayer p1) {
        float modifier = 1f;
        if (p1.getFavouriteTerrain() == FavoriteTerrain) {
            modifier *= p1.getBonusPercent();
        }
        return Math.round(damage * modifier);
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
    public int PostFightDamage() {
        return 0;
    }

    @Override
    public float opponent(Rogue r) {
        return  ROGUE_MODIFICATOR;
    }
}
