package com.gauravkohli;


import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Tree<T extends Comparable> {

    private Node<T> root;
    private AtomicInteger nodesCount = new AtomicInteger();

    public Tree(Node<T> root) {
        this.root = root;
        //Incrementing the counter for the root node
        incrementCounter();
    }

    public void addChild(T child) {
        addChild(root.getValue(), child);
    }

    public void addChild(T parent, T child) {
        Node<T> parentNode = findParentNode(parent);
        if (parentNode != null) {
            parentNode.addChild(child);
        } else {
            root.addChild(child);
        }
        incrementCounter();
    }

    private Node<T> findParentNode(T parent) {
        Map<T, Node<T>> children = root.getChildren();
        return children.get(parent);
    }

    public void incrementCounter() {
        nodesCount.getAndIncrement();
    }

    public int size() {
        return nodesCount.get();
    }

    @Override
    public String toString() {
        return root.toString();
    }
}
