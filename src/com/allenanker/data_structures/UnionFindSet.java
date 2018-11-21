package com.allenanker.data_structures;

import java.util.HashMap;
import java.util.List;

public class UnionFindSet {
    private HashMap<Node, Node> fatherMap;
    private HashMap<Node, Integer> sizeMap;

    public UnionFindSet(List<Node> nodes) {
        fatherMap = new HashMap<>();
        sizeMap = new HashMap<>();
        for (Node node : nodes) {
            fatherMap.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    public Node findHead(Node node) {
        Node father = fatherMap.get(node);
        if (father != node) {
            father = findHead(father);
        }
        fatherMap.put(node, father);
        return father;
    }

    public boolean isSameSet(Node a, Node b) {
        return findHead(a) == findHead(b);
    }

    public void union(Node a, Node b) {
        if (a == null || b == null) {
            return;
        }

        Node aHead = findHead(a);
        Node bHead = findHead(b);
        if (aHead != bHead) {
            int aSize = sizeMap.get(a);
            int bSize = sizeMap.get(b);
            if (aSize > bSize) {
                fatherMap.put(bHead, aHead);
                sizeMap.put(aHead, aSize + bSize);
            } else {
                fatherMap.put(aHead, bHead);
                sizeMap.put(bHead, aSize + bSize);
            }
        }
    }
}

class Node {
}
