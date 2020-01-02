package designpatterns;

import gameoutput.GameOutput;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public final class PlayerObserver implements IObserver {


    private static ArrayList<IObservable> players = new ArrayList<>();
    private static ArrayList<IObservable> angels = new ArrayList<>();
    private static PlayerObserver instance = new PlayerObserver();
    private PlayerObserver() {

    }

    public static PlayerObserver getInstance() {
        return instance;
    }

    @Override
    public void update(final IObservable p) {
        if (players.contains(p) || angels.contains(p)) {
            String message = p.notifyObserver();
            GameOutput.writeRequestedMessage(message);
        } else {
            throw new NoSuchElementException("The element is not observable");
        }
    }

    @Override
    public void addObservable(final IObservable o) {
        players.add(o);
    }

    @Override
    public void addSecondObservable(final IObservable o) {
        angels.add(o);
    }

    public void resetAngels() {
        angels.removeAll(angels);
    }
}
