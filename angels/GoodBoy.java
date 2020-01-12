package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

/**
 * GoodBoy is the opposite of Dracula, an angel which both increases HP and damage capability.
 */

public final class GoodBoy extends Angel {

    private static final float KNIGHTMODIFICATOR = 0.4f;
    private static final float PYROMANCERMODIFICATOR = 0.5f;
    private static final float ROGUEMODIFICATOR = 0.4f;
    private static final float WIZARDMODIFICATOR = 0.3f;

    private static final int KNIGHTHP = 20;
    private static final int PYROMANCERHP = 30;
    private static final int ROGUEHP = 40;
    private static final int WIZARDHP = 50;


    public GoodBoy(final int x, final int y) {
        super(x, y);
    }


    @Override
    public void applyAbility(final Knight k) {
        k.setCurrentHp(k.getCurrentHp() + KNIGHTHP);
        k.setStrategyModificator(KNIGHTMODIFICATOR);
    }

    @Override
    public void applyAbility(final Pyromancer p) {
        p.setCurrentHp(p.getCurrentHp() + PYROMANCERHP);
        p.setStrategyModificator(PYROMANCERMODIFICATOR);
    }

    @Override
    public void applyAbility(final Rogue r) {
        r.setCurrentHp(r.getCurrentHp() + ROGUEHP);
        r.setStrategyModificator(ROGUEMODIFICATOR);
    }

    @Override
    public void applyAbility(final Wizard w) {
        w.setCurrentHp(w.getCurrentHp() + WIZARDHP);
        w.setStrategyModificator(WIZARDMODIFICATOR);
    }
}
