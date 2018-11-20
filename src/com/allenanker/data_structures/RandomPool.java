package com.allenanker.data_structures;

import java.util.HashMap;

public class RandomPool<T> {
    /**
     * indexMap maps from object to index
     */
    private HashMap<T, Integer> indexMap;
    /**
     * objectMap maps from index to object
     */
    private HashMap<Integer, T> objectMap;
    /**
     * the current size in this data structure
     */
    private int size;

    public RandomPool() {
        indexMap = new HashMap<>();
        objectMap = new HashMap<>();
        size = 0;
    }

    public void insert(T key) {
        indexMap.put(key, size);
        objectMap.put(size, key);
        size++;
    }

    /**
     * Delete a specific key
     *
     * @param key
     */
    public void delete(T key) {
        if (!indexMap.containsKey(key)) {
            return;
        }
        // use the last object to fill the gap
        T last = objectMap.get(size - 1);
        int delIndex = indexMap.get(key);
        indexMap.remove(key);
        indexMap.put(last, delIndex);
        objectMap.put(delIndex, last);
        objectMap.remove(size - 1);
        size--;
    }

    public T getRandom() {
        if (size == 0) {
            return null;
        }

        return objectMap.get((int) (Math.random() * size));
    }

    public static void main(String[] args) {

    }
}
