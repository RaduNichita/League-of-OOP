package designpatterns;

import gameoutput.GameOutput;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public final class GreatMagician implements IObserver {


    private static ArrayList<IObservable> players = new ArrayList<>();
    private static ArrayList<IObservable> angels = new ArrayList<>();
    private static GreatMagician instance = new GreatMagician();
    private GreatMagician() {

    }

    public static GreatMagician getInstance() {
        return instance;
    }

    /**
     * Receiving an update from an observable object.
     * @param p the observable object, notifying the Great Magician that something relevant  has
     *          happened.
     */
    @Override
    public void update(final IObservable p) {
        if (players.contains(p) || angels.contains(p)) {
            String message = p.notifyObserver();
            GameOutput.writeRequestedMessage(message);
        } else {
            throw new NoSuchElementException("The element is not observable");
        }
    }

    /**
     * Adding heroes as observables.
     * @param o hero to be added.
     */
    @Override
    public void addObservable(final IObservable o) {
        players.add(o);
    }

    /**
     * Adding angels as observables.
     * @param o angel to be added.
     */
    @Override
    public void addSecondObservable(final IObservable o) {
        angels.add(o);
    }

    /**
     * After current round, current angels are no longer observed by the Great Magician, because
     * they do not affect heroes anymore.
     */
    public void resetAngels() {
        angels.removeAll(angels);
    }
}
