package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int index = getIndex(key);
        boolean isEmpty = table[index] == null;
        if (isEmpty) {
            table[index] = new MapEntry(key, value);
            count++;
            modCount++;
        }
        return isEmpty;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private int getIndex(K key) {
        int hashCode = Objects.hashCode(key);
        int hash = hash(hashCode);
        return indexFor(hash);
    }

    private boolean keysAreEquals(K key, MapEntry<K, V> entry) {
        boolean result = false;
        if (entry != null
                && Objects.hashCode(key) == Objects.hashCode(entry.key)
                && Objects.equals(key, entry.key)) {
            result = true;
        }
        return result;
    }

    private void expand() {
        capacity = capacity << 1;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                int index = getIndex(table[i].key);
                newTable[index] = table[i];
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        int index = getIndex(key);
        V result = null;
        if (keysAreEquals(key, table[index])) {
            result = table[index].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        int index = getIndex(key);
        boolean result = false;
        if (keysAreEquals(key, table[index])) {
            table[index] = null;
            result = true;
            count--;
            modCount++;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {

        return new Iterator<K>() {

            private final MapEntry<K, V>[] data = table;
            private final int length = capacity;
            private int index;
            private final int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < length && data[index] == null) {
                    index++;
                }
                return index < length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return data[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
