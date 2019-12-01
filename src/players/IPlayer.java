package players;

import abilities.IAbility;

public interface IPlayer {

    int isAttacked(AbstractPlayer p);
    float requestModifier(IAbility ability);
}
