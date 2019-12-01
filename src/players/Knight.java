package players;

import abilities.Execute;
import abilities.IAbility;
import abilities.Slam;

public final class Knight extends AbstractPlayer {


    private static final int MAX_HP = 900;
    private int current_hp;
    private static final int bonushp = 80;
    public final float bonusPercent = 1.15f;
    private static final char FavouriteTerrain = 'L';


    @Override
    public char getFavouriteTerrain() {
        return FavouriteTerrain;
    }


    @Override
    public float requestModifier(final IAbility ability) {
           return ability.opponent(this);
    }

    @Override
    public int getMaxHP() {
        return MAX_HP + level * bonushp;
    }

    Knight(final int x, final int y) {
        super(x, y);
        addAbility(new Execute());
        addAbility(new Slam());
    }

    @Override
    public int isAttacked(final AbstractPlayer abstractPlayer) {
        return abstractPlayer.getTotalDamage(this);

    }


    @Override
    public float getBonusPercent() {
        return bonusPercent;
    }
}
