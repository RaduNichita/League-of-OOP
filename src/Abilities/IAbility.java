package Abilities;

import Players.*;

public interface IAbility {

    int PostFightDamage();

    int DamageWithoutModifier(AbstractPlayer p1);

    float opponent(Knight k);

    float opponent(Wizard w);

    float opponent(Pyromancer p);

    float opponent(Rogue r);
}
