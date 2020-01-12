package angels;

import heroes.Knight;
import heroes.HeroStatus;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

/**
 * Spawner is the only type of angel that deals with DEAD players, by reviving them.
 */

public final class Spawner extends Angel {



    private static final int KNIGHTHP = 200;
    private static final int PYROMANCERHP = 150;
    private static final int ROGUEHP = 180;
    private static final int WIZARDHP = 120;

    public Spawner(final int x, final int y) {
        super(x, y);
    }

    @Override
    public void applyAbility(final Knight k) {
        if (k.getStatus() == HeroStatus.DEAD) {
            k.revive(KNIGHTHP);

        }
    }

    @Override
    public void applyAbility(final Pyromancer p) {
        if (p.getStatus() == HeroStatus.DEAD) {
            p.revive(PYROMANCERHP);
        }
    }

    @Override
    public void applyAbility(final Rogue r) {
        if (r.getStatus() == HeroStatus.DEAD) {
            r.revive(ROGUEHP);
        }
    }

    @Override
    public void applyAbility(final Wizard w) {
        if (w.getStatus() == HeroStatus.DEAD) {
            w.revive(WIZARDHP);
        }
    }
}
