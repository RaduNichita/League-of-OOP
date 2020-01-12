package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

/**
 * LevelUpAngel is an angel that gives player enough experience to level up. Moreover, the hero
 * now give a little more damage.
 */

public final class LevelUpAngel extends Angel {
    private static final float KNIGHTMODIFICATOR = 0.1f;
    private static final float PYROMANCERMODIFICATOR = 0.2f;
    private static final float ROGUEMODIFICATOR = 0.15f;
    private static final float WIZARDMODIFICATOR = 0.25f;
    LevelUpAngel(final int x, final int y) {
        super(x, y);
    }

    @Override
    public void applyAbility(final Knight k) {
        k.getExperiencetoReachLevel();
        k.setStrategyModificator(KNIGHTMODIFICATOR);
    }

    @Override
    public void applyAbility(final Pyromancer p) {
        p.getExperiencetoReachLevel();
        p.setStrategyModificator(PYROMANCERMODIFICATOR);
    }

    @Override
    public void applyAbility(final Rogue r) {
        r.getExperiencetoReachLevel();
        r.setStrategyModificator(ROGUEMODIFICATOR);
    }

    @Override
    public void applyAbility(final Wizard w) {
        w.getExperiencetoReachLevel();
        w.setStrategyModificator(WIZARDMODIFICATOR);
    }


}
