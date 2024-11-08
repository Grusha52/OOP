package ru.nsu.chernikov;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;


/**
 * Iterable Hashtable.
 *
 * @param <K> key
 * @param <V> value
 */
public class HashTable<K, V> implements Iterable<Entry<K, V>> {

    static final int DEFAULT_INITIAL_CAPACITY = 16; //16))
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private LinkedList<Entry<K, V>>[] hashtable;
    private int size;
    int modCount;

    /**
     * constructor.
     */
    public HashTable() {
        size = 0;
        modCount = 0;
        hashtable = new LinkedList[DEFAULT_INITIAL_CAPACITY];
    }

    private int hash(Object key) {
        return Math.abs(key.hashCode() % hashtable.length);
    }

    /**
     * Put the key and its value.
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        int index = hash(key);
        if (hashtable[index] == null) {
            hashtable[index] = new LinkedList<>();
        }
        for (Entry<K, V> entry : hashtable[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                modCount++;
                return;
            }
        }

        hashtable[index].add(new Entry<>(key, value));
        modCount++;
        size++;

        if (size > DEFAULT_LOAD_FACTOR * hashtable.length) {
            resize();
        }
    }

    /**
     * Remove key.
     *
     * @param key key in hashtable.
     */
    public void remove(K key) {
        int index = hash(key);
        if (hashtable[index] == null) {
            return;
        }

        Iterator<Entry<K, V>> iterator = hashtable[index].iterator();
        while (iterator.hasNext()) {
            Entry<K, V> entry = iterator.next();
            if (entry.key.equals(key)) {
                iterator.remove();
                size--;
                modCount++;
                return;
            }
        }
    }

    /**
     * Get the value of the key.
     *
     * @param key key)
     * @return value of the key
     */
    public V get(K key) {
        int index = hash(key);
        if (hashtable[index] == null) {
            return null;
        }

        for (Entry<K, V> entry : hashtable[index]) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        throw new NoSuchElementException();

    }

    /**
     * updating the value of the key.
     *
     * @param key   key)
     * @param value value)
     */
    public void update(K key, V value) {
        int index = hash(key);
        if (hashtable[index] == null) {
            return;
        }
        for (Entry<K, V> entry : hashtable[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                modCount++;
                return;
            }
        }
    }

    public boolean containsKey(K key) {
        int index = hash(key);
        if (hashtable[index] == null) {
            return false;
        }
        for (Entry<K, V> entry : hashtable[index]) {
            if (entry.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Iterator for hashtable, to iterate our hashtable.
     *
     * @return iterator of hastable.
     */
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int indexBucket;
            private Iterator<Entry<K, V>> bucketIterator =
                    hashtable[indexBucket] != null ? hashtable[indexBucket].iterator() : null;

            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                while ((bucketIterator == null || !bucketIterator.hasNext())
                        &&
                        indexBucket < hashtable.length - 1) {

                    indexBucket++;
                    bucketIterator = hashtable[indexBucket] != null ? hashtable[indexBucket].iterator() : null;
                }
                return bucketIterator != null && bucketIterator.hasNext();
            }

            public Entry<K, V> next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return bucketIterator.next();
            }
        };
    }

    /**
     * Equals for hashtables.
     *
     * @param o object
     * @return true or false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        HashTable<K, V> table = (HashTable<K, V>) o;
        if (o == null || getClass() != o.getClass() || size != table.size()) {
            return false;
        }

        for (int i = 0; i < hashtable.length; i++) {
            LinkedList<Entry<K, V>> bucket = hashtable[i];
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    if (!entry.value.equals(table.get(entry.key))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (LinkedList<Entry<K, V>> entries : hashtable) {
            if (entries == null) {
                continue;
            }
            for (Entry<K, V> entry : entries) {
                sb.append(entry.key).append("=").append(entry.value).append(", ");
            }
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("}");
        return sb.toString();
    }

    public void resize() {
        LinkedList<Entry<K, V>>[] old = hashtable;
        hashtable = new LinkedList[old.length * 2];
        size = 0;

        for (LinkedList<Entry<K, V>> bucket : old) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    put(entry.key, entry.value);
                }
            }
        }
    }

    /**
     * size of hashtable.
     *
     * @return size
     */
    public int size() {
        return size;
    }
}