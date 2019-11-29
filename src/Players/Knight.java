package Players;

import Abilities.IAbility;

public class Knight extends AbstractPlayer {
    @Override
    public float requestModifier(IAbility ability) {
           return ability.opponent(this);
    }

    @Override
    public int getMaxHP() {
        return 0;
    }

    Knight(int x, int y) {
        super(x, y);
    }

    @Override
    public void isAttacked(AbstractPlayer abstractPlayer) {
        abstractPlayer.attack(this);
    }

    @Override
    public void attack(Knight knight) {
    }

    @Override
    public void attack(Rogue rogue) {

    }

    @Override
    public void attack(Pyromancer pyromancer) {

    }

    @Override
    public void attack(Wizard wizard) {

    }

    @Override
    protected float getTerrainBonus() {
        return 0;
    }


}
