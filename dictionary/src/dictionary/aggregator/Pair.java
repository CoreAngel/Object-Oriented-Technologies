package dictionary.aggregator;

import java.util.Objects;

public class Pair<K, V> {
    private K key;
    private V value;

    Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair<K, V> pair = (Pair<K, V>) o;
        return getKey().equals(pair.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey());
    }
}
