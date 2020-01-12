package abilities;

public final class Debuff {


    private int duration;
    private final float damage;
    private final boolean stun;


    Debuff(final int duration, final float damage, final boolean stun) {
        this.damage = damage;
        this.duration = duration;
        this.stun = stun;
    }

    public  boolean isStun() {
        return stun;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration() {
        this.duration--;
    }

    public int getDamage() {
        return Math.round(damage);
    }
}
