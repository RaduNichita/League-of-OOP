package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

/**
 * Damage Angel, which allow player to give more damage.
 */

public final class DamageAngel extends Angel {


    private static final float KNIGHTMODIFICATOR = 0.15f;
    private static final float PYROMANCERMODIFICATOR = 0.2f;
    private static final float ROGUEMODIFICATOR = 0.3f;
    private static final float WIZARDMODIFICATOR = 0.4f;


    DamageAngel(final int x, final int y) {
        super(x, y);
    }

    @Override
    public void applyAbility(final Knight k) {
        k.setStrategyModificator(KNIGHTMODIFICATOR);
    }

    @Override
    public void applyAbility(final Pyromancer p) {
        p.setStrategyModificator(PYROMANCERMODIFICATOR);
    }

    @Override
    public void applyAbility(final Rogue r) {
        r.setStrategyModificator(ROGUEMODIFICATOR);
    }

    @Override
    public void applyAbility(final Wizard w) {
        w.setStrategyModificator(WIZARDMODIFICATOR);
    }
}
