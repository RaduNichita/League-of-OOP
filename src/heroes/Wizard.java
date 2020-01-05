package heroes;

import abilities.Deflect;
import abilities.Drain;
import abilities.IAbility;
import angels.Angel;
import map.TerrainType;
import strategies.IStrategy;


public final class Wizard extends Hero {


    private static final int MAXHP = 400;
    private static final int BONUSHP = 30;
    private static final TerrainType FAVOURITE_TERRAIN = TerrainType.D;
    private static final float BONUS_PERCENT = 1.1f;

    private static final int WIZARD_ATTACK_COEF = 2;
    private static final int WIZARD_DEFEND_COEF = 4;

    private static final float WIZARD_ATTACK_STRATEGY_MODIFICATOR = 0.6f;
    private static final float WIZARD_DEFEND_STRATEGY_MODIFICATOR = -0.2f;
    private static final int WIZARD_ATTACK_HP_COEF = 10;
    private static final int WIZARD_DEFEND_HP_COEF = 5;

    Wizard(final int x, final int y) {
        super(x, y);
        super.setCurrentHp(MAXHP);
        addAbility(new Drain());
        addAbility(new Deflect());
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
    public float requestModifier(final IAbility ability) {
        return ability.inContactWith(this);
    }

    @Override
    public int getMaxHP() {
        return MAXHP + BONUSHP * level;
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

        if (this.getCurrentHp() < getMaxHP() / WIZARD_DEFEND_COEF) {
            strategy = new Wizard.WizardDefendStrategy();

        } else if (this.getCurrentHp() > getMaxHP() / WIZARD_DEFEND_COEF && this
                .getCurrentHp() < getMaxHP() / WIZARD_ATTACK_COEF) {
            strategy = new Wizard.WizardAttackStrategy();
        }

        if (strategy != null) {
            strategy.applyStrategy();
        }

    }

    private final class WizardAttackStrategy implements IStrategy {
        @Override
        public void applyStrategy() {
            setStrategyModificator(WIZARD_ATTACK_STRATEGY_MODIFICATOR);
            setCurrentHp(getCurrentHp() - getCurrentHp() / WIZARD_ATTACK_HP_COEF);
        }
    }

    private final class WizardDefendStrategy implements IStrategy {
        @Override
        public void applyStrategy() {
            setStrategyModificator(WIZARD_DEFEND_STRATEGY_MODIFICATOR);
            setCurrentHp(getCurrentHp() + getCurrentHp() / WIZARD_DEFEND_HP_COEF);
        }
    }


}
