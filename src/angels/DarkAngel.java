package angels;

import players.AbstractPlayer;
import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

public final class DarkAngel extends Angel {


    private static final int KNIGHTHP = -40;
    private static final int PYROMANCERHP = -30;
    private static final int ROGUEHP = -10;
    private static final int WIZARDHP = -20;

    public DarkAngel(final int x, final int y) {
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

    @Override
    public String helpMessage(final AbstractPlayer p) {
        return this.getClass().getSimpleName() + " hit " + p.getClass().getSimpleName() + " " + p
                .getPlayerId();
    }
}
