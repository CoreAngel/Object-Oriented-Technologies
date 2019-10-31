package observer;

public interface IObserver<T> {
    void addObserver(T observer);
    void removeObserver(T observer);
    void notifyObservers();
    void notifyObserver(T observer);
}
