package abilities;

import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

// Implementing visitor pattern by these 4 methods, which are called by the
// requestModificator in each player class.

public interface CharacterModificator {

    float inContactWith(Knight k);

    float inContactWith(Wizard w);

    float inContactWith(Pyromancer p);

    float inContactWith(Rogue r);
}
