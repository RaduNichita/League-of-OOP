package heroes;

import abilities.Execute;
import abilities.IAbility;
import abilities.Slam;
import angels.Angel;
import map.TerrainType;
import strategies.IStrategy;

public final class Knight extends Hero {


    private static final int MAX_HP = 900;
    private static final int BONUSHP = 80;
    private static final float BONUS_PERCENT = 1.15f;
    private static final TerrainType FAVOURITE_TERRAIN = TerrainType.L;

    private static final int KNIGHT_ATTACK_COEF = 2;
    private static final int KNIGHT_DEFEND_COEF = 3;

    private static final float KNIGHT_ATTACK_STRATEGY_MODIFICATOR = 0.5f;
    private static final float KNIGHT_DEFEND_STRATEGY_MODIFICATOR = -0.2f;
    private static final int KNIGHT_ATTACK_HP_COEF = 5;
    private static final int KNIGHT_DEFEND_HP_COEF = 4;


    Knight(final int x, final int y) {
        super(x, y);
        addAbility(new Execute());
        addAbility(new Slam());
    }

    @Override
    public TerrainType getFavouriteTerrain() {
        return FAVOURITE_TERRAIN;
    }

    @Override
    public float requestModifier(final IAbility ability) {
        return ability.inContactWith(this);
    }

    @Override
    public int getMaxHP() {
        return MAX_HP + level * BONUSHP;
    }

    @Override
    public int isAttacked(final Hero abstractPlayer) {
        return abstractPlayer.getTotalDamage(this);

    }

    @Override
    public void acceptAngel(final Angel angel) {
        angel.applyAbility(this);
    }

    @Override
    public void strategyChoice() {

        strategy = null;

        if (this.getCurrentHp() < getMaxHP() / KNIGHT_DEFEND_COEF) {
            strategy = new KnightDefendStrategy();

        } else if (this.getCurrentHp() > getMaxHP() / KNIGHT_DEFEND_COEF && this
                .getCurrentHp() < getMaxHP() / KNIGHT_ATTACK_COEF) {
            strategy = new KnightAttackStrategy();
        }

        if (strategy != null) {
            strategy.applyStrategy();
        }

    }

    @Override
    public float getBonusPercent() {
        return BONUS_PERCENT;
    }

    private final class KnightAttackStrategy implements IStrategy {
        @Override
        public void applyStrategy() {
            setStrategyModificator(KNIGHT_ATTACK_STRATEGY_MODIFICATOR);
            setCurrentHp(getCurrentHp() - getCurrentHp() / KNIGHT_ATTACK_HP_COEF);
        }
    }

    private final class KnightDefendStrategy implements IStrategy {
        @Override
        public void applyStrategy() {
            setStrategyModificator(KNIGHT_DEFEND_STRATEGY_MODIFICATOR);
            setCurrentHp(getCurrentHp() + getCurrentHp() / KNIGHT_DEFEND_HP_COEF);
        }
    }
}
