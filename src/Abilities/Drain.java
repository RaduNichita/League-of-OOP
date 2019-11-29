package Abilities;

import Players.*;

public class Drain implements IAbility {

    private static final char FavoriteTerrain = 'D';

    private static final float BASE_PERCENT = 0.2f;
    private float percent  = BASE_PERCENT;
    private static final float PERCENT_PER_LEVEL = 0.05f;
    private static final float HP_PERCENT = 0.3f;

    private static final float ROGUE_MODIFICATOR = 0.8f;
    private static final float KNIGHT_MODIFICATOR =  +1.2f;
    private static final float PYROMANCER_MODIFICATOR = 0.9f;
    private static final float WIZARD_MODIFICATOR = 1.05f;



    @Override
    public int DamageWithoutModifier(AbstractPlayer p1) {
        return 0;
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
        return ROGUE_MODIFICATOR;
    }
}
