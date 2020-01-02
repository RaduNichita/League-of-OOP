package players;

import abilities.IAbility;

public interface IPlayer {


    int isAttacked(AbstractPlayer p);

    /**
     * Implementing visitor pattern between player and abilty.
     * @param ability ability to visit
     * @return the modifier for battling with a p'type.
     */
    float requestModifier(IAbility ability);
}
