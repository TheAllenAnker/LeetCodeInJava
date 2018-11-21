package com.allenanker.data_structures;

public class Trie {
    private TrieNode root;

    public void insert(String word) {
        if (word == null || word.trim().equals("")) {
            return;
        }

        char[] chars = word.toCharArray();
        TrieNode curr = root;
        int index = 0;
        for (char c : chars) {
            index = c - 97;
            if (curr.nexts[index] == null) {
                curr.nexts[index] = new TrieNode();
            }
            curr.nexts[index].path++;
            curr = curr.nexts[index];
        }
        curr.nexts[index].end++;
    }

    /**
     * Delete a word from the trie if it exists
     *
     * @param word
     */
    public void delete(String word) {
        if (word == null || word.trim().equals("") || count(word) == 0) {
            return;
        }

        char[] chars = word.toCharArray();
        TrieNode curr = root;
        int index;
        for (char c : chars) {
            index = c - 97;
            if (--curr.nexts[index].path == 0) {
                curr.nexts[index] = null;
                return;
            }
            curr = curr.nexts[index];
        }
        curr.end--;
    }

    /**
     * The number of words with the specific prefix.
     *
     * @param prefix
     * @return
     */
    public int getPathTo(String prefix) {
        if (prefix == null || prefix.trim().equals("")) {
            return 0;
        }

        char[] chars = prefix.toCharArray();
        TrieNode curr = root;
        int index;
        for (char c : chars) {
            index = c - 97;
            if (curr.nexts[index] == null) {
                return 0;
            }
            curr = curr.nexts[index];
        }

        return curr.path;
    }

    /**
     * Return the number of times a given word is added into the trie.
     *
     * @param word
     * @return
     */
    public int count(String word) {
        if (word == null || word.trim().equals("")) {
            return 0;
        }

        char[] chars = word.toCharArray();
        TrieNode curr = root;
        int index;
        for (char c : chars) {
            index = c - 97;
            if (curr.nexts[index] == null) {
                return 0;
            }
            curr = curr.nexts[index];
        }

        return curr.end;
    }
}

class TrieNode {
    int path;
    int end;
    TrieNode[] nexts;

    /**
     * There are 26 letters in English, so the length of next is 26.
     */
    public TrieNode() {
        path = 0;
        end = 0;
        nexts = new TrieNode[26];
    }
}
