package com.gauravkohli;


import java.util.*;

public class Node<T extends Comparable> implements Comparable<Node<T>> {

    private T value;
    private Map<T, Node<T>> children;
    private int level = 0;

    public Node(T value, int level) {
        this.value = value;
        this.children = new HashMap<>();
        this.level = level;
    }

    public Map<T, Node<T>> getChildren() {
        return children;
    }

    public T getValue() {
        return value;
    }

    public Node<T> addChild(T child) {
        Node childNode = new Node<T>(child, this.level + 1);
        children.put(child, childNode);
        return childNode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LEVEL: ")
                .append(this.level)
                .append(" ")
                .append(value.toString()).append("\n");

        Iterator iterator = children.values().iterator();
        while (iterator.hasNext()) {
            builder.append(iterator.next().toString());
        }

        return builder.toString();
    }

    @Override
    public int compareTo(Node<T> o) {
        return this.value.compareTo(o.value);
    }
}
