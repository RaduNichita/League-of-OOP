package Players;

import Abilities.IAbility;
import Terrain.Terrain;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class AbstractPlayer implements IPlayer {



    private static final int MAX_HP = 0;
    private int current_hp;
    private static final int extraHP = 0;
    private ArrayList<IAbility> abilities;
    protected int xp;
    protected int bonushp;
    protected int level = 0;
    protected int xCoordonate;
    protected int yCoordonate;
    protected PlayerStatus status;
    protected Terrain current_terrain;
    public char favouriteTerrain;
    public float bonusPercent;

    public PlayerStatus getStatus() {
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

    AbstractPlayer(int x, int y) {
        setCoordonate(x,y);
        status = PlayerStatus.ALIVE;
       this.current_hp = this.getMaxHP();
       this.abilities = new ArrayList<>();
    }

    protected final void setCoordonate(final int x, final int y) {
        this.xCoordonate = x;
        this.yCoordonate = y;
    }

    protected final void moveUP() {
        this.yCoordonate -=1;
    }
    protected final void moveDOWN() {
        this.yCoordonate +=1;
    }
    protected final void moveLEFT() {
        this.xCoordonate -= 1;
    }

    public void getTotalDamage(AbstractPlayer p) {
        int total_damage = 0;
        for (IAbility ability : abilities) {
            total_damage += Math.round(ability.DamageWithoutModifier(this) * p.requestModifier(ability));
        }
        System.out.println(total_damage);
    }

    public int getDamageWithoutModifiers(AbstractPlayer p) {
        int total_damage = 0;
        for (IAbility ability : abilities) {
            total_damage +=ability.DamageWithoutModifier(this);
        }
        System.out.println("Damage fara modificatori" + total_damage);
        return total_damage;
    }

    protected final void getPostFightDamage(AbstractPlayer p) {
        int total_damage = 0;
        for (IAbility ability : abilities) {

        }
    }

    public Terrain getCurrent_terrain() {
        return current_terrain;
    }

    public static int getMaxHp() {
        return MAX_HP;
    }

    public int getCurrent_hp() {
        return current_hp;
    }

    public static int getExtraHP() {
        return extraHP;
    }

    public int getXp() {
        return xp;
    }

    public int getBonushp() {
        return bonushp;
    }

    public int getLevel() {
        return level;
    }

    public final void joinTerrain(Terrain t) {
        current_terrain = t;
        this.xCoordonate = t.getX_axis();
        this.yCoordonate = t.getY_axis();
        t.joinTerrain(this);
    }

    public final void leftTerrain(Terrain t) {
        current_terrain = null;
        t.leftTerrain(this);
    }

    protected final void moveRIGHT() {
        this.xCoordonate += 1;
    }

    public int getyCoordonate() {
        return yCoordonate;
    }

    public int getxCoordonate() {
        return xCoordonate;
    }

    protected final void getExperience(AbstractPlayer p) {
        xp += Math.max(0,200-(level - p.level) * 40);
    }

    protected final void receiveDamage(int damage) {
        this.current_hp -= damage;
        if (this.current_hp < 0) {
            status = PlayerStatus.DEAD;
        }
    }
    protected final boolean checkLevelUp() {
        return (xp > (250 +  level * 50));
    }

    @Override
    public String toString() {
        if (status == PlayerStatus.ALIVE) {
            return (this.getClass().getSimpleName().charAt(0) + " " + this.getLevel() + " " + this.getXp() +
                    " " + this.getCurrent_hp() + " " + this.getxCoordonate() + " " +this.getyCoordonate());
        } else {
            return (this.getClass().getSimpleName().charAt(0) + PlayerStatus.DEAD.toString());
        }
    }

    protected void addAbility(IAbility ability) {

        this.abilities.add(ability);
    }

    public abstract void isAttacked(AbstractPlayer abstractPlayer);


    public abstract void attack(Knight knight);

    public abstract void attack(Rogue rogue);

    public abstract void attack(Pyromancer pyromancer);

    public abstract void attack(Wizard wizard);

//    public void attack(AbstractPlayer abstractPlayer) {
//        System.out.println("De ce intra pe aici");
//    }

    protected abstract float getTerrainBonus();
}
