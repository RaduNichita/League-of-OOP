package abilities;

import players.AbstractPlayer;
import players.Wizard;
import players.Rogue;
import players.Knight;
import players.Pyromancer;

public interface IAbility {

    float getLandModifier(AbstractPlayer p);

    float damagewithoutmodifier(AbstractPlayer attack, AbstractPlayer defend);

    float opponent(Knight k);

    float opponent(Wizard w);

    float opponent(Pyromancer p);

    float opponent(Rogue r);
}
