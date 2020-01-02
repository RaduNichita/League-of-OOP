package angels;


import designpatterns.INotification;
import designpatterns.IObservable;
import designpatterns.PlayerObserver;
import players.AbstractPlayer;
import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;


public abstract class Angel implements IObservable {

    private int xAxis;
    private int yAxis;
    private INotification notification;

    Angel(final int x, final int y) {
        this.xAxis = x;
        this.yAxis = y;
    }

    public final int getxAxis() {
        return xAxis;
    }

    public final int getyAxis() {
        return yAxis;
    }

    public final void spawnNotification() {
        notifyObserver(() -> "Angel " + Angel.this.getClass()
                .getSimpleName() + " was spawned at " + Angel.this.getxAxis() + " " + Angel.this
                .getyAxis());
    }

    /**
     * The message should be change if the angel would have a negative impact on player.
     * @param p player affected.
     * @return a message after the angel has helped/hit the player.
     */
    public String helpMessage(final AbstractPlayer p) {
        return this.getClass().getSimpleName() + " helped " + p.getClass().getSimpleName() + " " + p
                .getPlayerId();
    }

    public final void helpNotification(final AbstractPlayer p) {
        notifyObserver(() -> Angel.this.helpMessage(p));
    }

    public abstract void applyAbility(Knight k);

    public abstract void applyAbility(Pyromancer p);

    public abstract void applyAbility(Rogue r);

    public abstract void applyAbility(Wizard w);


    public final void notifyObserver(final INotification newnotification) {
        this.notification = newnotification;
        PlayerObserver.getInstance().update(this);
    }

    @Override
    public final String notifyObserver() {
        return notification.execute();
    }
}
