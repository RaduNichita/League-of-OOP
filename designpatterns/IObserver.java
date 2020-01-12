package designpatterns;


/**
 * Interface implemented by the Great Magician to track changes of players.
 */
public interface IObserver {
    void update(IObservable p);
    void addObservable(IObservable o);
    void addSecondObservable(IObservable o);
}
