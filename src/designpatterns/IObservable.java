package designpatterns;

/**
 * Interface implemented by both heroes and players, to notify Great Magician when something
 * relevant happens.
 */

public interface IObservable {

    String notifyObserver();
}
