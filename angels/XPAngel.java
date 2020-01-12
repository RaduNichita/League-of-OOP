package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;


/**
 * XPAngel provides heroes a medium amount of XP, for nothing in exchange.
 */
public final class XPAngel extends Angel {


    private static final int KNIGHTMODIFICATOR = 45;
    private static final int PYROMANCERMODIFICATOR = 50;
    private static final int ROGUEMODIFICATOR = 40;
    private static final int WIZARDMODIFICATOR = 60;

    public XPAngel(final int x, final int y) {
        super(x, y);
    }

    @Override
    public void applyAbility(final Knight k) {
        k.getExperience(KNIGHTMODIFICATOR);
    }

    @Override
    public void applyAbility(final Pyromancer p) {
        p.getExperience(PYROMANCERMODIFICATOR);
    }

    @Override
    public void applyAbility(final Rogue r) {
        r.getExperience(ROGUEMODIFICATOR);
    }

    @Override
    public void applyAbility(final Wizard w) {
        w.getExperience(WIZARDMODIFICATOR);
    }
}
