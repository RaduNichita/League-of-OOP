package abilities;

import heroes.Hero;

public interface IAbility extends CharacterModificator {

    /**
     * check if player is on his favourite terrain.
     * @param p player to be checked
     * @return p's bonus modifier if he is on favourite terrain.
     */

    float getLandModifier(Hero p);


    /**
     * For each class that implements this interface, the method will calculate the damage given
     * by attack player to defend player.
     * @param attack the player who gives the attack
     * @param defend the player receiving the attack
     * @return the amount of damage, calculated diferently for each ability.
     */
    float damagewithoutmodifier(Hero attack, Hero defend);

    void levelUp();

    int totaldamage(Hero attack, Hero defend);

}
