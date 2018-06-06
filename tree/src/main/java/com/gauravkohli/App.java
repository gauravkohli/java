package com.gauravkohli;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Node<String> root = new Node<>("root", 0);
        Tree<String> simpleTree = new Tree<>(root);

        ExpandingTree expandingTree;
        List<TreeSize> treeSizes = new ArrayList<>();
        ExecutorService executorService = Executors.newCachedThreadPool();

        expandingTree = new ExpandingTree(simpleTree);
        executorService.submit(expandingTree);

        for (int i = 0; i < 10; i++) {
            TreeSize treeSize = new TreeSize(simpleTree, i + 1);
            treeSizes.add(treeSize);
            executorService.submit(treeSize);
        }

        Thread.sleep(10000);

        expandingTree.setStopRunning(true);

        for (TreeSize treeSize : treeSizes) {
            treeSize.setStopRunning(true);
        }

        System.out.println("Printing the current tree");
        System.out.println(simpleTree);

        System.out.println("Final size of the tree is: " + simpleTree.size());

        executorService.shutdown();
        executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
    }
}
