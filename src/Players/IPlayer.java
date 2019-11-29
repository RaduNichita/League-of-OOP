package Players;

import Abilities.IAbility;

public interface IPlayer {

    void attack(Knight knight);
    void attack(Rogue rogue);
    void attack(Pyromancer pyromancer);
    void attack (Wizard wizard);

    void isAttacked (AbstractPlayer p);
    float requestModifier(IAbility ability);
}
