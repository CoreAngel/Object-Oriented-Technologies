package observer;

public interface Observable<T> {
    void addObserver(T observer);
    void removeObserver(T observer);
    void notifyObservers();
    void notifyObserver(T observer);
}
