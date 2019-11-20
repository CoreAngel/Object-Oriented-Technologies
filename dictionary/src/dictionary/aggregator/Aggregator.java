package dictionary.aggregator;

import java.util.ArrayList;
import java.util.Iterator;

public class Aggregator<K, V> implements Iterable<Pair<K, V>> {
    ArrayList<Pair<K, V>> list = new ArrayList<>();

    public void add(K key, V value) {
        if (this.getValueByKey(key) == null) {
            list.add(new Pair<>(key, value));
        }
    }

    public V getValueByKey(K key) {
        for (Pair<K, V> pair : list) {
            if (pair.getKey() == key) {
                return pair.getValue();
            }
        }
        return null;
    };

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return new AggregatorIterator<>(this);
    }

}

class AggregatorIterator<K, V> implements Iterator<Pair<K, V>> {
    private Aggregator<K, V> aggregator;
    private int currentIndex = 0;

    AggregatorIterator(Aggregator<K, V> aggregator) {
        this.aggregator = aggregator;
    }

    @Override
    public boolean hasNext() {
        return this.aggregator.list.size() - 1 >= currentIndex;
    }

    @Override
    public Pair<K, V> next() {
        try {
           return this.aggregator.list.get(currentIndex);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}

