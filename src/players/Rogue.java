package players;

import abilities.BackStab;
import abilities.IAbility;
import abilities.Paralysis;
import angels.Angel;
import map.TerrainType;
import strategies.IStrategy;

public final class Rogue extends AbstractPlayer {


    public static final TerrainType FAVOURITE_TERRAIN = TerrainType.W;
    public static final float BONUS_PERCENT = 1.15f;
    private static final int MAX_HP = 600;
    private static final int BONUSHP = 40;
    private static final int ROGUE_ATTACK_COEF = 5;
    private static final int ROGUE_DEFEND_COEF = 7;
    private static final float ROGUE_ATTACK_STRATEGY_MODIFICATOR = 0.4f;
    private static final float ROGUE_DEFEND_STRATEGY_MODIFICATOR = -0.1f;
    private static final int ROGUE_ATTACK_HP_COEF = 7;
    private static final int ROGUE_DEFEND_HP_COEF = 2;
    private int criticalround = 0;

    Rogue(final int x, final int y) {
        super(x, y);
        addAbility(new BackStab());
        addAbility(new Paralysis());
        super.setCurrentHp(MAX_HP);
    }

    @Override
    public TerrainType getFavouriteTerrain() {
        return FAVOURITE_TERRAIN;
    }

    @Override
    public float getBonusPercent() {
        return BONUS_PERCENT;
    }

    @Override
    public int getMaxHP() {
        return MAX_HP + BONUSHP * level;
    }

    @Override
    public int getCritical() {
        return criticalround;
    }

    @Override
    public void acceptAngel(Angel angel) {
        angel.applyAbility(this);
    }

    @Override
    public void strategyChoice() {

        strategy = null;

        if (this.getCurrentHp() < getMaxHP() / ROGUE_DEFEND_COEF) {
            strategy = new Rogue.RogueDefendStrategy();

        } else if (this.getCurrentHp() > getMaxHP() / ROGUE_DEFEND_COEF && this
                .getCurrentHp() < getMaxHP() / ROGUE_ATTACK_COEF) {
            strategy = new Rogue.RogueAttackStrategy();
        }

        if (strategy != null) {
            strategy.applyStrategy();
        }
    }

    @Override
    public void update() {
        super.update();
        criticalround++;
    }

    @Override
    public float requestModifier(final IAbility ability) {
        return ability.inContactWith(this);
    }

    @Override
    public int isAttacked(final AbstractPlayer abstractPlayer) {
        return abstractPlayer.getTotalDamage(this);
    }

    private final class RogueAttackStrategy implements IStrategy {
        @Override
        public void applyStrategy() {
            setStrategyModificator(ROGUE_ATTACK_STRATEGY_MODIFICATOR);
            setCurrentHp(getCurrentHp() - getCurrentHp() / ROGUE_ATTACK_HP_COEF);
        }
    }

    private final class RogueDefendStrategy implements IStrategy {
        @Override
        public void applyStrategy() {
            setStrategyModificator(ROGUE_DEFEND_STRATEGY_MODIFICATOR);
            setCurrentHp(getCurrentHp() + getCurrentHp() / ROGUE_DEFEND_HP_COEF);
        }

    }
}
