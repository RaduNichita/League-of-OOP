package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

/**
 * LifeGiver Angel is an angel that provide a medium amount of HP to heroes.
 */
public final class LifeGiver extends Angel {

    private static final int KNIGHTHP = 100;
    private static final int PYROMANCERHP = 80;
    private static final int ROGUEHP = 90;
    private static final int WIZARDHP = 120;


    LifeGiver(final int x, final int y) {
        super(x, y);
    }

    @Override
    public void applyAbility(final Knight k) {
        k.setCurrentHp(k.getCurrentHp() + KNIGHTHP);
    }

    @Override
    public void applyAbility(final Pyromancer p) {
        p.setCurrentHp(p.getCurrentHp() + PYROMANCERHP);

    }

    @Override
    public void applyAbility(final Rogue r) {
        r.setCurrentHp(r.getCurrentHp() + ROGUEHP);
    }

    @Override
    public void applyAbility(final Wizard w) {
        w.setCurrentHp(w.getCurrentHp() + WIZARDHP);
    }


}
