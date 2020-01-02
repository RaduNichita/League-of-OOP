package angels;

import players.AbstractPlayer;
import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

public final class TheDoomer extends Angel {


    TheDoomer(final int x, final int y) {
        super(x, y);
    }

    @Override
    public void applyAbility(final Knight k) {
        k.setCurrentHp(0);
    }

    @Override
    public void applyAbility(final Pyromancer p) {
        p.setCurrentHp(0);
    }

    @Override
    public void applyAbility(final Rogue r) {
        r.setCurrentHp(0);
    }

    @Override
    public void applyAbility(final Wizard w) {
        w.setCurrentHp(0);
    }

    @Override
    public String helpMessage(final AbstractPlayer p) {
        return this.getClass().getSimpleName() + " hit " + p.getClass().getSimpleName() + " " + p
                .getPlayerId();
    }

}
