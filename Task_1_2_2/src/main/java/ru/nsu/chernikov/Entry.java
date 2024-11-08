package ru.nsu.chernikov;

/**
 * Node in a Linked List.
 *
 * @param <K> key
 * @param <V> value
 */
public class Entry<K, V> {
    K key;
    V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
