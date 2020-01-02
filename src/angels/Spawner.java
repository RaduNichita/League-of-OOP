package angels;

import players.Knight;
import players.PlayerStatus;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

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
        if (k.getStatus() == PlayerStatus.DEAD) {
            k.revive(KNIGHTHP);

        }
    }

    @Override
    public void applyAbility(final Pyromancer p) {
        if (p.getStatus() == PlayerStatus.DEAD) {
            p.revive(PYROMANCERHP);
        }
    }

    @Override
    public void applyAbility(final Rogue r) {
        if (r.getStatus() == PlayerStatus.DEAD) {
            r.revive(ROGUEHP);
        }
    }

    @Override
    public void applyAbility(final Wizard w) {
        if (w.getStatus() == PlayerStatus.DEAD) {
            w.revive(WIZARDHP);
        }
    }
}
