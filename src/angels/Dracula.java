package angels;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;


/**
 *  Dracula is a dangerous type of angel, which takes HP from player and decreases given damage.
 */
public final class Dracula extends Angel {

    private static final float KNIGHTMODIFICATOR = -0.2f;
    private static final float PYROMANCERMODIFICATOR = -0.3f;
    private static final float ROGUEMODIFICATOR = -0.1f;
    private static final float WIZARDMODIFICATOR = -0.4f;

    private static final int KNIGHTHP = -60;
    private static final int PYROMANCERHP = -40;
    private static final int ROGUEHP = -35;
    private static final int WIZARDHP = -20;


    public Dracula(final int x, final int y) {
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

    @Override
    public String helpMessage(final Hero p) {
        return this.getClass().getSimpleName() + " hit " + p.getClass().getSimpleName() + " " + p
                .getPlayerId();
    }

}
