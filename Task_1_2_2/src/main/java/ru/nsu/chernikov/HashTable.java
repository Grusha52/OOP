package ru.nsu.chernikov;


import java.util.*;

public class HashTable<K, V> {

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; //16))
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private LinkedList<Entry<K, V>>[] hashtable;
    private int size;
    int modCount;

    public HashTable() {
        size = 0;
        modCount = 0;
        hashtable = new LinkedList[DEFAULT_INITIAL_CAPACITY];
    }

    private int hash(Object key) {
        return Math.abs(key.hashCode() % hashtable.length);
    }

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

    public void remove(K key, V value) {
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
        return null;

    }

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

    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {
            
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HashTable<?, ?> hashTable)) return false;
        return size == hashTable.size && modCount == hashTable.modCount && Objects.deepEquals(hashtable, hashTable.hashtable);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < hashtable.length; i++) {
            if (hashtable[i] == null) {
                continue;
            }
            for (Entry<K, V> entry : hashtable[i]) {
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

        for (LinkedList<Entry<K, V>> bucket : old){
            if (bucket != null){
                for (Entry<K, V> entry : bucket){
                    put(entry.key, entry.value);
                }
            }
        }
    }

    public int size() {
        return size;
    }
}