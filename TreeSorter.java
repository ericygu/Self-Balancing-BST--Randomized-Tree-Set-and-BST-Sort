package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class TreeSorter<K> {
    public static final int REMOVE_MIN = 0;
    public static final int GET_INORDER = 1;
    private TreeSet<K> set;
    private int removalType;
    public TreeSorter(TreeSet<K> set, int removalType){
        this.set = set;
        this.removalType = removalType;
    }
    public ArrayList<K> sort(ArrayList<K> input){
        set.insertList(input);
        switch (removalType){
            case REMOVE_MIN: return priorityQueueSort();
            case GET_INORDER: return inOrderTraversal();
        }
        return null;
    }
    public ArrayList<K> priorityQueueSort(){
        //your implementation goes here
        ArrayList<K> elementStorage = new ArrayList<>();

        while (!set.isEmpty()) {
            elementStorage.add(set.removeMin());
        }

        return elementStorage;
    }
    public ArrayList<K> inOrderTraversal(){
        //your implementation goes here
        return set.inOrder();
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = -2000000; i <= 2000000; i++) {
            a.add(i);
        }

        Collections.shuffle(a);
        //First one sorts correctly

        long startTime = System.currentTimeMillis();

        TreeSorter<Integer> removeMinSort = new TreeSorter<>(new TreeSet<>(), 0);
        //System.out.println(removeMinSort.sort(a));
        removeMinSort.sort(a);
        long endTime1 = System.currentTimeMillis();
        //Collections.shuffle(a);
        //Second one sorts correctly

        TreeSorter<Integer> inorderSort = new TreeSorter<>(new TreeSet<>(), 1);
        //System.out.println(inorderSort.sort(a));
        inorderSort.sort(a);
        long endTime2 = System.currentTimeMillis();
        //Collections.shuffle(a);
        //Third sorts correctly

        TreeSorter<Integer> splayMinSort = new TreeSorter<>(new SelfBalancingTreeSet<>(), 0);
        //System.out.println(splayMinSort.sort(a));
        splayMinSort.sort(a);
        long endTime3 = System.currentTimeMillis();
        //Collections.shuffle(a);
        //Fourth sorts correctly

        TreeSorter<Integer> splayInorderSort = new TreeSorter<>(new SelfBalancingTreeSet<>(), 1);
        //System.out.println(splayInorderSort.sort(a));
        splayInorderSort.sort(a);
        long endTime4 = System.currentTimeMillis();

        System.out.println("removeMin took " + (endTime1 - startTime) + " milliseconds");
        System.out.println("inorder took " + (endTime2 - endTime1) + " milliseconds");
        System.out.println("splay remove min took " + (endTime3 - endTime2) + " milliseconds");
        System.out.println("splay inorder took " + (endTime4 - endTime3) + " milliseconds");

    }
}
