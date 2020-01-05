package heroes;

import abilities.FireBlast;
import abilities.IAbility;
import abilities.Ignite;
import angels.Angel;
import map.TerrainType;
import strategies.IStrategy;

public final class Pyromancer extends Hero {


    private static final int MAX_HP = 500;
    private static final int BONUSHP = 50;
    private static final float BONUS_PERCENT = 1.25f;
    private static final TerrainType FAVOURITE_TERRAIN = TerrainType.V;

    private static final int PYROMANCER_ATTACK_COEF = 3;
    private static final int PYROMANCER_DEFEND_COEF = 4;

    private static final float PYROMANCER_ATTACK_STRATEGY_MODIFICATOR = 0.7f;
    private static final float PYROMANCER_DEFEND_STRATEGY_MODIFICATOR = -0.3f;
    private static final int PYROMANCER_ATTACK_HP_COEF = 4;
    private static final int PYROMANCER_DEFEND_HP_COEF = 3;

    Pyromancer(final int x, final int y) {
        super(x, y);
        addAbility(new FireBlast());
        addAbility(new Ignite());
        super.setCurrentHp(MAX_HP);
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
    public float getBonusPercent() {
        return BONUS_PERCENT;
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

        if (this.getCurrentHp() < getMaxHP() / PYROMANCER_DEFEND_COEF) {
            strategy = new Pyromancer.PyromancerDefendStrategy();

        } else if (this.getCurrentHp() > getMaxHP() / PYROMANCER_DEFEND_COEF && this
                .getCurrentHp() < getMaxHP() / PYROMANCER_ATTACK_COEF) {
            strategy = new Pyromancer.PyromancerAttackStrategy();
        }

        if (strategy != null) {
            strategy.applyStrategy();
        }
    }

    private final class PyromancerAttackStrategy implements IStrategy {
        @Override
        public void applyStrategy() {
            setStrategyModificator(PYROMANCER_ATTACK_STRATEGY_MODIFICATOR);
            setCurrentHp(getCurrentHp() - getCurrentHp() / PYROMANCER_ATTACK_HP_COEF);
        }
    }

    private final class PyromancerDefendStrategy implements IStrategy {
        @Override
        public void applyStrategy() {
            setStrategyModificator(PYROMANCER_DEFEND_STRATEGY_MODIFICATOR);
            setCurrentHp(getCurrentHp() + getCurrentHp() / PYROMANCER_DEFEND_HP_COEF);
        }

    }


}
