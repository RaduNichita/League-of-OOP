package players;

import abilities.Debuff;
import abilities.IAbility;
import terrain.Terrain;

import java.util.ArrayList;

public abstract class AbstractPlayer implements IPlayer {



    private static final int EXPERIENCE_COEF = 200;
    private static final int EXPERIENCE_MULTIPLICATOR = 40;
    private static final int LEVEL_COEF = 250;
    private static final int LEVEL_MULTIPLICATOR = 50;
    protected static final int MAX_HP = 0;
    private static final int EXTRA_HP = 0;
    private final ArrayList<IAbility> abilities;
    protected int xp;
    protected int bonushp;
    protected int level = 0;
    protected int xCoordonate;
    protected int yCoordonate;
    protected PlayerStatus status;
    protected Terrain currentTerrain;
    private int currentHp;
    private char favouriteTerrain;
    private float bonusPercent;
    private Debuff debuff;
    private boolean stunned = false;

    AbstractPlayer(final int x, final int y) {
        setCoordonate(x, y);
        status = PlayerStatus.ALIVE;
        this.currentHp = this.getMaxHP();
        this.abilities = new ArrayList<>();
    }

    public static int getExtraHp() {
        return EXTRA_HP;
    }

    public final void setDebuff(final Debuff debuff) {
        this.debuff = debuff;
    }

    public final void endDebuff() {
        this.debuff = null;
    }

    public final void preFightDamage() {

        stunned = false;
        if (debuff != null) {
            if (debuff.isStun()) {
                stunned = true;
            }
            this.receiveDamage(debuff.getDamage());
            System.out.println(
                    "Pentru jucatorul" + this.getClass().getSimpleName() + "au ramas" + debuff
                            .getDuration());
            debuff.setDuration();
            if (debuff.getDuration() <= 0) {
                endDebuff();
            }
        }

    }

    public final boolean isStuned() {
        return stunned;
    }

    public final PlayerStatus getStatus() {
        return status;
    }

    public abstract int getMaxHP();

    public abstract float requestModifier(IAbility ability);

    public char getFavouriteTerrain() {
        return this.favouriteTerrain;
    }

    public float getBonusPercent() {
        return this.bonusPercent;
    }

    protected final void setCoordonate(final int x, final int y) {
        this.xCoordonate = x;
        this.yCoordonate = y;
    }


    public final int getTotalDamage(final AbstractPlayer p) {
        int totalDamage = 0;
        for (IAbility ability : abilities) {

            if (p.requestModifier(ability) != 0) {
                float abilityDamage = ability.damagewithoutmodifier(this, p);
                if (p.getCurrentHp() == 0) {
                    totalDamage += p.getCurrentHp();
                } else {
                    totalDamage += Math.round((ability.getLandModifier(this) * abilityDamage) * p
                            .requestModifier(ability));
                }
            }
        }
        return totalDamage;
    }

    public final int getDamageWithoutModifiers(final AbstractPlayer p) {
        int totalDamage = 0;
        for (IAbility ability : abilities) {
            if (p.requestModifier(ability) != 0) {
                totalDamage += Math.round(ability.getLandModifier(this) * ability
                        .damagewithoutmodifier(this, p));
            }
        }
        return totalDamage;
    }


    public final Terrain getCurrentTerrain() {
        return currentTerrain;
    }


    public final int getCurrentHp() {
        return currentHp;
    }

    public final void setCurrentHp(final int currentHp) {
        this.currentHp = currentHp;
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

    public final void joinTerrain(final Terrain t) {
        currentTerrain = t;
        this.xCoordonate = t.getX_axis();
        this.yCoordonate = t.getY_axis();
        t.joinTerrain(this);
    }

    public final void leftTerrain(final Terrain t) {
        currentTerrain = null;
        t.leftTerrain(this);
    }

    public final int getyCoordonate() {
        return yCoordonate;
    }

    public final  int getxCoordonate() {
        return xCoordonate;
    }

    public final void getExperience(final AbstractPlayer p) {
        xp += Math.max(0, EXPERIENCE_COEF - (level - p.level) * EXPERIENCE_MULTIPLICATOR);
        if (status == PlayerStatus.ALIVE) {
            levelUp();
        }
    }

    private void setStatus() {
        this.status = PlayerStatus.DEAD;
    }

    public final void receiveDamage(final int damage) {
        this.currentHp -= damage;
        if (this.currentHp <= 0) {
            setStatus();
        }
    }

    protected final boolean checkLevelUp() {
        return (xp >= (LEVEL_COEF + level * LEVEL_MULTIPLICATOR));
    }

    public final void setLevel() {
        level++;
        this.currentHp = getMaxHP();
    }

    private void levelUp() {
        while (checkLevelUp()) {
            setLevel();
        }
    }

    @Override
    public final String toString() {
        if (status == PlayerStatus.ALIVE) {
            return (this.getClass().getSimpleName().charAt(0) + " " + this.getLevel() + " " + this
                    .getXp()
                    +
                    " " + this.getCurrentHp() + " " + this.getxCoordonate() + " " + this
                    .getyCoordonate());
        } else {
            return (this.getClass().getSimpleName().charAt(0) + " " + PlayerStatus.DEAD.toString()
                    .toLowerCase());
        }
    }

    public final ArrayList<IAbility> getAbilities() {
        return abilities;
    }

    protected final void addAbility(final IAbility ability) {

        this.abilities.add(ability);
    }

    public abstract int isAttacked(AbstractPlayer abstractPlayer);


    /**
     * UPDATE HEROES ATTRIBUTES - is used for rogue player to increment his count and may be use
     * for others heroes in a future update of the game.
     */
    public void update() {
    }

    /**
     *
     * @return if hero is going to give a critical attack in the current round.
     */
    public int getCritical() {
        return 0;
    }
}
