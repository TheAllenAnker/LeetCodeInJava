package com.allenanker.topinterviewed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Design a data structure that supports all following operations in average O(1) time.
 * <p>
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements. Each element must have the same probability of
 * being returned.
 */
public class RandomizedSet {
    private ArrayList<Integer> numsList;
    private HashMap<Integer, Integer> integerLocs;
    private Random random = new Random();

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        numsList = new ArrayList<>();
        integerLocs = new HashMap<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (integerLocs.containsKey(val)) return false;

        integerLocs.put(val, numsList.size());
        numsList.add(val);

        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!integerLocs.containsKey(val)) return false;

        // swap the val with the last one
        if (integerLocs.get(val) != numsList.size() - 1) {
            int loc = integerLocs.get(val);
            int temp = numsList.get(numsList.size() - 1);
            numsList.set(loc, temp);
            integerLocs.put(temp, loc);
        }
        integerLocs.remove(val);
        numsList.remove(numsList.size() - 1);

        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return numsList.get(random.nextInt(numsList.size()));
    }

    public static void main(String[] args) {
        RandomizedSet obj = new RandomizedSet();
        System.out.println(obj.insert(1));
        System.out.println(obj.remove(2));
        System.out.println(obj.insert(2));
        System.out.println(obj.getRandom());
    }
}
