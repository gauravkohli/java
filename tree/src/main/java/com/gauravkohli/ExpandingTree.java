package com.gauravkohli;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ExpandingTree implements Runnable {

    private final Tree<String> tree;
    private volatile boolean stopRunning = false;
    private final List<String> childAdded = new ArrayList<>();
    private Random random = new Random();


    public ExpandingTree(Tree<String> tree) {
        this.tree = tree;
    }

    @Override
    public void run() {
        while (!stopRunning) {

            System.out.println("Starting the Expanding Tree job");

            boolean addToRoot = random.nextBoolean();
            String childNode = UUID.randomUUID().toString();
            if (addToRoot || childAdded.size() == 0) {

                tree.addChild(childNode);
                childAdded.add(childNode);
                System.out.println("Adding to root, chile node:" + childNode);
            } else {

                String parentNode = childAdded.get(random.nextInt(childAdded.size()));
                tree.addChild(parentNode, childNode);
                System.out.println("Adding to " + parentNode + ", chile node:" + childNode);
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setStopRunning(boolean stopRunning) {
        System.out.println("Stopping the tree expanding job");
        this.stopRunning = stopRunning;
    }
}
