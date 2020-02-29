import java.util.ArrayList;
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
        return null;
    }
    public ArrayList<K> inOrderTraversal(){
        //your implementation goes here
        return null;
    }
}
