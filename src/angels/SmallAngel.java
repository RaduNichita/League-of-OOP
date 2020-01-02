package angels;

import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

public final class SmallAngel extends Angel {


    private static final float KNIGHTMODIFICATOR = 0.1f;
    private static final float PYROMANCERMODIFICATOR = 0.5f;
    private static final float ROGUEMODIFICATOR = 0.05f;
    private static final float WIZARDMODIFICATOR = 0.1f;

    private static final int KNIGHTHP = 10;
    private static final int PYROMANCERHP = 15;
    private static final int ROGUEHP = 20;
    private static final int WIZARDHP = 25;

    public SmallAngel(final int x, final int y) {
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
