package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class TreeRotator {
    public static void main(String[] args){
		//an example of two randomly generated BSTs on the same set of keys
        TreeSet<Integer> ts1 = new TreeSet<>();
        TreeSet<Integer> ts2 = new TreeSet<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) list.add(i);
        Collections.shuffle(list);
        for (Integer in : list) ts1.put(in);
        LinkedBinaryTree<Integer> tree1 = (LinkedBinaryTree<Integer>)ts1.getTree();
        Collections.shuffle(list);
        for (Integer in : list) ts2.put(in);
        LinkedBinaryTree<Integer> tree2 = (LinkedBinaryTree<Integer>)ts2.getTree();

        System.out.println(tree1);
        System.out.println(tree2);

        printRotations(tree1, tree2);
    }
    public static void printRotations(LinkedBinaryTree<Integer> bt1, LinkedBinaryTree<Integer> bt2){
		//start with shallow copies of each of the trees
		LinkedBinaryTree<Integer> copy1 = new LinkedBinaryTree<>(bt1);
		LinkedBinaryTree<Integer> copy2 = new LinkedBinaryTree<>(bt2);
		//your implementation goes here

        Position<Integer> r1 = copy1.root();
        Position<Integer> r2 = copy2.root();

        //1st part is an algorithm, if not having a left child goes to the right
        //then searches a node for a left child and if there is one rotates that left child

        //2nd part is same algorithm except we print the parent and then reverse the string once we find the left child

        while (!copy1.isExternal(r1)) {
            if (copy1.left(r1).getElement() == null) {
                r1 = copy1.right(r1);
            }

            else if (copy1.left(r1).getElement() != null) {
                System.out.print(copy1.left(r1).getElement());
                r1 = copy1.left(r1);
                copy1.rotate(r1);
            }
        }

        //we can use a stack to print the 2nd tree in reverse
        Stack<Integer> reverser = new Stack<Integer>();

        while(!copy2.isExternal(r2)) {
            if (copy2.left(r2).getElement() == null) {
                r2 = copy2.right(r2);
            }

            else if (copy2.left(r2).getElement() != null) {
                reverser.push(r2.getElement());
                r2 = copy2.left(r2);
                copy2.rotate(r2);
            }
        }

        while(reverser.size() != 0) {
            System.out.print(reverser.pop());
        }
    }

}

