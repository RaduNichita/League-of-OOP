package designpatterns;

public interface IObserver {
    void update(IObservable p);
    void addObservable(IObservable o);
    void addSecondObservable(IObservable o);
}
