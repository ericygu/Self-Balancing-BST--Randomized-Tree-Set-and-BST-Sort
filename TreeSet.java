import java.util.Comparator;
import java.util.ArrayList;
public class TreeSet<K> implements Set<K> {
    protected LinkedBinaryTree<K> tree = new LinkedBinaryTree<>();
    private Comparator<K> comp;

    public TreeSet() {
        this(new DefaultComparator<K>());
    }

    public TreeSet(Comparator<K> comp) {
        this.comp = comp;
        tree.addRoot(null);
    }

    public int size() {
        return (tree.size() - 1) / 2;
    }

    /** Utility used when inserting a new entry at a leaf of the tree */
    private void expandExternal(Position<K> p, K key) {
        tree.set(p, key);
        tree.addLeft(p, null);
        tree.addRight(p, null);
    }

    protected int compare(K a, K b) {
        return comp.compare(a, b);
    }

    protected Position<K> root() { return tree.root(); }
    protected Position<K> parent(Position<K> p) { return tree.parent(p); }
    protected Position<K> left(Position<K> p) { return tree.left(p); }
    protected Position<K> right(Position<K> p) { return tree.right(p); }
    protected Position<K> sibling(Position<K> p) { return tree.sibling(p); }
    protected boolean isRoot(Position<K> p) { return tree.isRoot(p); }
    protected boolean isExternal(Position<K> p) { return tree.isExternal(p); }
    protected boolean isInternal(Position<K> p) { return !isExternal(p); }
    protected void set(Position<K> p, K e) { tree.set(p, e); }
    protected K remove(Position<K> p) { return tree.remove(p); }
    protected void rotate(Position<K> p) { tree.rotate(p); }
    protected Position<K> restructure(Position<K> x) { return tree.restructure(x); }

    public BinaryTree<K> getTree(){
        return tree;
    }

    private Position<K> treeSearch(Position<K> p, K key) {
        if (isExternal(p))
            return p;
        int comp = compare(key, p.getElement());
        if (comp == 0)
            return p;
        else if (comp < 0)
            return treeSearch(left(p), key);
        else
            return treeSearch(right(p), key);
    }

    protected Position<K> treeMin(Position<K> p) {
        Position<K> walk = p;
        while (isInternal(walk))
            walk = left(walk);
        return parent(walk);
    }

    protected Position<K> treeMax(Position<K> p) {
        Position<K> walk = p;
        while (isInternal(walk))
            walk = right(walk);
        return parent(walk);
    }

    public boolean contains(K key) throws IllegalArgumentException {
        Position<K> p = treeSearch(root(), key);
        rebalanceAccess(p);
        return !isExternal(p);
    }

    public boolean put(K key) throws IllegalArgumentException {
        Position<K> p = treeSearch(root(), key);
        if (isExternal(p)) {
            expandExternal(p, key);
            rebalanceInsert(p);
            return true;
        }
        return false;
    }

    public K remove(K key) throws IllegalArgumentException {
        Position<K> p = treeSearch(root(), key);
        if (isExternal(p)){
            rebalanceAccess(p);
            return null;
        }
        else {
            K old = p.getElement();
            if (isInternal(left(p)) && isInternal(right(p))) {
                Position<K> replacement = treeMax(left(p));
                set(p, replacement.getElement());
                p = replacement;
            }
            Position<K> leaf = (isExternal(left(p)) ? left(p) : right(p));
            Position<K> sib = sibling(leaf);
            remove(leaf);
            remove(p);
            rebalanceDelete(sib);
            return old;
        }
    }

    public void insertList(ArrayList<K> list){
        for (K key : list) put(key);
    }

    protected void rebalanceInsert(Position<K> p){}
    protected void rebalanceDelete(Position<K> p){}
    protected void rebalanceAccess(Position<K> p){}

	public ArrayList<K> inOrder(){
		ArrayList<K> list = new ArrayList<>();
		placeInOrder(list, root());
		return list;
	}
	
	protected void placeInOrder(ArrayList<K> output, Position<K> node){
		if (!isExternal(left(node))){
			placeInOrder(output, left(node));
		}
		output.add(node.getElement());
		if (!isExternal(right(node))){
			placeInOrder(output, right(node));
		}
	}

    /*
    public K min(){}
    public K removeMin(){}
    public void insert(K key){}
    public boolean isEmpty(){}
    *
    * your implementation goes here
    */
}
