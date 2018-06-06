package com.gauravkohli;


public class TreeSize implements Runnable {

    private final Tree<String> tree;
    private final int id;
    private volatile boolean stopRunning = false;

    public TreeSize(Tree<String> tree, int id) {
        this.tree = tree;
        this.id = id;

    }

    @Override
    public void run() {
        while (!stopRunning) {
            System.out.println("Size of the tree for reader: " + id + " is: " + tree.size());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setStopRunning(boolean stopRunning) {
        System.out.println("Stopping the tree reader: " + id);
        this.stopRunning = stopRunning;
    }
}
