package com.gauravkohli;


import java.util.*;

public class Node<T> {

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

    public Node<T> findParentNode(T parent) {
        Map<T, Node<T>> children = this.getChildren();
        Node<T> node = children.get(parent);
        if (node != null) {
            return node;
        } else {
            for (Node<T> childNode : children.values()) {
                Node<T> parentNode = childNode.findParentNode(parent);
                if (parentNode != null) {
                    return parentNode;
                }
            }
            return null;
        }
    }

    public Node<T> addChild(T child) {
        Node childNode = new Node<T>(child, this.level + 1);
        children.put(child, childNode);
        return childNode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LEVEL: |");
        for (int i = 0; i < this.level; i++) {
            builder.append("---");
        }
        builder.append(this.level)
                .append(" ")
                .append(value.toString()).append("\n");

        Iterator iterator = children.values().iterator();
        while (iterator.hasNext()) {
            builder.append(iterator.next().toString());
        }

        return builder.toString();
    }
}
