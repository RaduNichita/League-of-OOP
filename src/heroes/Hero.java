package heroes;

import abilities.Debuff;
import abilities.IAbility;
import angels.Angel;
import designpatterns.INotification;
import designpatterns.IObservable;
import designpatterns.GreatMagician;
import map.Map;

import map.TerrainType;
import strategies.IStrategy;


import java.util.ArrayList;

public abstract class Hero implements IPlayer, IObservable {

    protected static final int MAX_HP = 0;
    private static final int EXPERIENCE_COEF = 200;

    private static final int EXPERIENCE_MULTIPLICATOR = 40;
    private static final int LEVEL_COEF = 250;
    private static final int LEVEL_MULTIPLICATOR = 50;
    private static final int EXTRA_HP = 0;
    private final ArrayList<IAbility> abilities;
    protected int xp;
    protected int bonushp;
    protected int level = 0;
    protected int xCoordonate;
    protected int yCoordonate;
    protected HeroStatus status;
    protected TerrainType currentTerrain;
    protected ArrayList<Float> strategyModificator;
    protected IStrategy strategy;
    protected int playerId;
    private int currentHp;
    private TerrainType favouriteTerrain;
    private float bonusPercent;
    private Debuff debuff;
    private boolean stunned = false;
    private INotification notification;

    Hero(final int x, final int y) {
        setCoordonate(x, y);
        status = HeroStatus.ALIVE;
        this.currentHp = this.getMaxHP();
        this.abilities = new ArrayList<>();
        this.strategyModificator = new ArrayList<>();
    }

    public static int getExtraHp() {
        return EXTRA_HP;
    }

    public final int getPlayerId() {
        return playerId;
    }

    public final void setPlayerId(final int playerId) {
        this.playerId = playerId;
    }

    public final void setDebuff(final Debuff debuff) {
        this.debuff = debuff;
    }

    public final void endDebuff() {
        this.debuff = null;
    }


    /**
     * If there is a debuff applied to current player, it would be applied before the start of
     * the current round. Debuff may consist in damage and/or stunned.
     */
    public final void preFightDamage() {

        stunned = false;
        if (debuff != null) {
            if (debuff.isStun()) {
                stunned = true;
            }
            this.receiveDamage(debuff.getDamage());
            // System.out.println(
            //       "Pentru jucatorul" + this.getClass().getSimpleName() + "au ramas" + debuff
            //             .getDuration());
            debuff.setDuration();
            if (debuff.getDuration() <= 0) {
                endDebuff();
            }
        }

    }

    /**
     * Checks if current player is stunned.
     *
     * @return true if player and no otherwise.
     */
    public final boolean isStuned() {
        return stunned;
    }

    public final HeroStatus getStatus() {
        return status;
    }

    public abstract int getMaxHP();

    public abstract float requestModifier(IAbility ability);

    /**
     * Classes that override this method should have a field FavoriteTerrain that represent
     * player favourite terrain, on which he does more damage.
     *
     * @return Player current terrain
     */
    public TerrainType getFavouriteTerrain() {
        return this.favouriteTerrain;
    }

    /**
     * Classes that override this method should have a field BonusPercent that represent the
     * bonus percent for doing damage in favourite Terrain.
     *
     * @return Bonus percent for doing damage in favorite terrain.
     */
    public float getBonusPercent() {
        return this.bonusPercent;
    }

    protected final void setCoordonate(final int x, final int y) {
        this.xCoordonate = x;
        this.yCoordonate = y;
    }

    public final void revive(final int hpRevive) {
        this.status = HeroStatus.ALIVE;
        this.setCurrentHp(hpRevive);
        reviveNotification();

    }

    /**
     * Total damage given by the current player to player p. It is calculated as the sum of
     * ability real damage * land_modifier * race_modifier. If a Knight performs an execution, it
     * is checked that by comparing defender current hp with 0.
     *
     * @param p the player who will take the damage.
     * @return
     */

    public final int getTotalDamage(final Hero p) {
        Float totalDamage = 0f;
        for (IAbility ability : abilities) {

            if (p.requestModifier(ability) != 0) {
                float abilityDamage = ability.damagewithoutmodifier(this, p);
                if (p.getCurrentHp() == 0) {
                    totalDamage += ability.damagewithoutmodifier(this, p);
                } else {
                    if (p.requestModifier(ability) != 1f) {
                        int partialDamage = ability.totaldamage(this, p);
                        totalDamage += partialDamage;
                    } else {
                        totalDamage += Math
                                .round(Math
                                        .round((ability.getLandModifier(this) * abilityDamage)) * p
                                        .requestModifier(ability));
                    }

                }
            }
        }
        return totalDamage.intValue();
    }

    public final ArrayList<Float> getStrategyModificator() {
        return strategyModificator;
    }

    public final void setStrategyModificator(final float strategyModificator) {
        this.strategyModificator.add(strategyModificator);
    }


    public final float modifierCalculator(final IAbility ability, final Hero p) {
        float abilityModificator = p.requestModifier(ability);

        for (float f : this.getStrategyModificator()) {
            abilityModificator += f;
        }
        return abilityModificator;
    }

    /**
     * Get the damage given by current player to other player without Modifiers.
     *
     * @param p -> the defender player
     * @return
     */
    public final int getDamageWithoutModifiers(final Hero p) {
        Float totalDamage = 0f;
        for (IAbility ability : abilities) {
            if (p.requestModifier(ability) != 0) {
                totalDamage += Math.round(ability.getLandModifier(this) * ability
                        .damagewithoutmodifier(this, p));
            }
        }
        return totalDamage.intValue();
    }


    public final TerrainType getCurrentTerrain() {
        return currentTerrain;
    }


    public final int getCurrentHp() {
        return currentHp;
    }

    public final void setCurrentHp(final int currentHp) {

        this.currentHp = Math.min(currentHp, this.getMaxHP());
        if (currentHp <= 0) {
            this.setStatus();
        }
    }

    public final int getXp() {
        return xp;
    }

    public final int getBonushp() {
        return bonushp;
    }

    public final int getLevel() {
        return level;
    }


    public final void killNotification(final Hero p) {
        notifyObserver(() -> "Player " + Hero.this.getClass()
                .getSimpleName() + " " + getPlayerId() + " was killed by " + p.getClass()
                .getSimpleName() + " " + p.getPlayerId());
    }

    public final void killNotification() {
        notifyObserver(() -> "Player " + Hero.this.getClass()
                .getSimpleName()
                +
                " "
                +
                Hero.this.getPlayerId()
                +
                " "
                +
                "was killed "
                +
                "by"
                +
                " "
                +
                "an angel");
    }

    public final void reviveNotification() {
        notifyObserver(() -> "Player " + Hero.this.getClass()
                .getSimpleName()
                +
                " "
                +
                Hero.this.getPlayerId()
                +
                " "
                +
                "was "
                +
                "brought "
                +
                "to life by an"
                +
                " angel");
    }

    /**
     * Join a new terrain.
     *
     * @param x = the x-axis coordonate
     * @param y = the y-axis coordonate
     */
    public final void joinTerrain(final int x, final int y) {
        this.xCoordonate = x;
        this.yCoordonate = y;
        if (x >= 0 && x < Map.getRows() && y >= 0 && y <= Map.getColumns()) {
            this.currentTerrain = Map.getType(x, y);
            Map.joinCell(this);
        } else {
            this.currentTerrain = null;
        }
    }


    public final void leftTerrain() {
        if (currentTerrain != null) {
            Map.leaveCell(this);
        }
        this.xCoordonate = -1;
        this.yCoordonate = -1;
    }

    public final int getyCoordonate() {
        return yCoordonate;
    }

    public final int getxCoordonate() {
        return xCoordonate;
    }

    /**
     * receive amount of experience for killing a player.
     *
     * @param p player which was taken down by current player
     */
    public final void getExperience(final Hero p) {
        xp += Math.max(0, EXPERIENCE_COEF - (level - p.level) * EXPERIENCE_MULTIPLICATOR);
        if (status == HeroStatus.ALIVE) {
            levelUp();
        }
    }

    public final void getExperience(final int xpGained) {
        this.xp += xpGained;
        if (status == HeroStatus.ALIVE) {
            levelUp();
        }
    }

    public final void getExperiencetoReachLevel() {
        xp = LEVEL_COEF + this.getLevel() * LEVEL_MULTIPLICATOR;
        levelUp();
    }


    /**
     * change player status to dead.
     */
    private void setStatus() {
        this.status = HeroStatus.DEAD;
    }

    /**
     * Receive amount of damange and check if the player is still alive.
     *
     * @param damage -> damage received from the other player
     */
    public final void receiveDamage(final int damage) {
        this.currentHp -= damage;
        if (this.currentHp <= 0) {
            setStatus();
        }
    }

    /**
     * Check if current current player has level up.
     *
     * @return
     */
    protected final boolean checkLevelUp() {
        return (xp >= (LEVEL_COEF + level * LEVEL_MULTIPLICATOR));
    }

    public final void setLevel() {
        level++;
        this.currentHp = getMaxHP();
        for (IAbility ability : getAbilities()) {
            ability.levelUp();
        }
        notifyObserver(() -> Hero.this.getClass()
                .getSimpleName() + " " + Hero.this.getPlayerId() + " reached "
                + "level " + Hero.this.getLevel());
    }

    private void levelUp() {
        boolean newlevel = false;
        while (checkLevelUp()) {
            newlevel = true;
            setLevel();
        }
    }

    @Override
    public final String toString() {
        if (status == HeroStatus.ALIVE) {
            return (this.getClass().getSimpleName().charAt(0) + " " + this.getLevel() + " " + this
                    .getXp()
                    +
                    " " + this.getCurrentHp() + " " + this.getxCoordonate() + " " + this
                    .getyCoordonate());
        } else {
            return (this.getClass().getSimpleName().charAt(0) + " " + HeroStatus.DEAD.toString()
                    .toLowerCase());
        }
    }

    public final ArrayList<IAbility> getAbilities() {
        return abilities;
    }

    protected final void addAbility(final IAbility ability) {

        this.abilities.add(ability);
    }

    public abstract int isAttacked(Hero abstractPlayer);


    /**
     * UPDATE HEROES ATTRIBUTES - is used for rogue player to increment his count and may be use
     * for others heroes in a future update of the game.
     */
    public void update() {
    }

    /**
     * @return if hero is going to give a critical attack in the current round.
     */
    public int getCritical() {
        return 0;
    }


    public abstract void acceptAngel(Angel angel);

    public abstract void strategyChoice();


    public final void notifyObserver(final INotification iNotification) {
        this.notification = iNotification;
        GreatMagician.getInstance().update(this);
    }

    @Override
    public final String notifyObserver() {
        return notification.execute();
    }
}
