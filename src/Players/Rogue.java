package Players;

import Abilities.BackStab;
import Abilities.IAbility;
import Abilities.Paralysis;
import Terrain.TerrainType;

import java.util.ArrayList;

public class Rogue extends AbstractPlayer{


   ArrayList<IAbility> abilities = new ArrayList<>(2);


    private static final int MAX_HP = 600;
    private int current_hp;
    public final char favouriteTerrain = 'W';
    public final float bonusPercent = 1.15f;

    @Override
    public char getFavouriteTerrain() {
        return this.favouriteTerrain;
    }

    @Override
    public float getBonusPercent() {
        return bonusPercent;
    }


    @Override
    public int getMaxHP() {
        return this.MAX_HP + this.bonushp * level;
    }

    @Override
    public float requestModifier(IAbility ability) {
        return ability.opponent(this);
    }

    Rogue(int x, int y) {
        super(x, y);
        //abilities.add(new BackStab());
        //abilities.add(new Paralysis());

        addAbility(new BackStab());
        addAbility(new Paralysis());

        //abilities.add(new )
    }


    @Override
    protected float getTerrainBonus() {
        if (current_terrain.getType() == TerrainType.W.toString().toLowerCase().charAt(0)) {
            return (1 + bonusPercent);
        } else {
            return 1f;
        }
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
//        System.out.println("Rogue vs wizard de ce");
//        int damage = 0;
//        for (IAbility ability : abilities) {
//            damage += Math.round(ability.getDamage(this) * wizard.requestModifier(ability));
//        }
//        System.out.println(damage);
    }



    @Override
    public void isAttacked(AbstractPlayer abstractPlayer) {
       // abstractPlayer.attack(this);
        abstractPlayer.getTotalDamage(this);
    }
}
