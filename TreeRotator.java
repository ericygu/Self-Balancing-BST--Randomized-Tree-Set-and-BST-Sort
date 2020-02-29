import java.util.ArrayList;
import java.util.Collections;
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
    }
}
