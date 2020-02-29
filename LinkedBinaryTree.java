public class LinkedBinaryTree<K> implements BinaryTree<K> {
    protected static class Node<K> implements Position<K> {
        private K element;
        private Node<K> parent;
        private Node<K> left;
        private Node<K> right;
        private int aux;

        public Node(K key, Node<K> above, Node<K> leftChild, Node<K> rightChild) {
            element = key;
            parent = above;
            left = leftChild;
            right = rightChild;
        }

        public K getElement() { return element; }
        public Node<K> getParent() { return parent; }
        public Node<K> getLeft() { return left; }
        public Node<K> getRight() { return right; }
        public int getAux() { return aux; }

        public void setElement(K key) { element = key; }
        public void setParent(Node<K> parentNode) { parent = parentNode; }
        public void setLeft(Node<K> leftChild) { left = leftChild; }
        public void setRight(Node<K> rightChild) { right = rightChild; }
        public void setAux(int value) { aux = value; }
    }

    protected Node<K> createNode(K key, Node<K> parent, Node<K> left, Node<K> right) {
        return new Node<K>(key, parent, left, right);
    }

    public LinkedBinaryTree(){}

    public LinkedBinaryTree(LinkedBinaryTree<K> lbt){
        root = shallowCopy(validate(lbt.root()));
    }

    protected Node<K> root = null;
    private int size = 0;

    protected Node<K> validate(Position<K> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Not valid position type.");
        Node<K> node = (Node<K>) p;
        if (node.getParent() == node)
            throw new IllegalArgumentException("p is no longer in the tree.");
        return node;
    }

    protected int numChildren(Position<K> p){
        Node<K> node = validate(p);
        int num = 0;
        if (node != null){
            if (left(node) != null) num++;
            if (right(node) != null) num++;
        }
        return num;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Position<K> root() {
        return root;
    }

    public Position<K> parent(Position<K> p) throws IllegalArgumentException {
        Node<K> node = validate(p);
        return node.getParent();
    }

    public Position<K> left(Position<K> p) throws IllegalArgumentException {
        Node<K> node = validate(p);
        return node.getLeft();
    }

    public Position<K> right(Position<K> p) throws IllegalArgumentException {
        Node<K> node = validate(p);
        return node.getRight();
    }

    public Position<K> sibling(Position<K> p) throws IllegalArgumentException {
        Position<K> parent = parent(p);
        if (parent == null) return null;
        if (p == left(parent)){
            return right(parent);
        }
        else {
            return left(parent);
        }
    }

    public boolean isExternal(Position<K> p) { return numChildren(p) == 0; }
    public boolean isRoot(Position<K> p) { return p == root(); }

    public void set(Position<K> p, K key) throws IllegalArgumentException {
        Node<K> node = validate(p);
        node.setElement(key);
    }

    public Position<K> addRoot(K key) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty.");
        root = createNode(key, null, null, null);
        size = 1;
        return root;
    }

    public Position<K> addLeft(Position<K> p, K key) throws IllegalArgumentException {
        Node<K> parent = validate(p);
        if (parent.getLeft() != null)
            throw new IllegalArgumentException("p already has a left child.");
        Node<K> child = createNode(key, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    public Position<K> addRight(Position<K> p, K key) throws IllegalArgumentException {
        Node<K> parent = validate(p);
        if (parent.getRight() != null)
            throw new IllegalArgumentException("p already has a right child.");
        Node<K> child = createNode(key, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    public K remove(Position<K> p) throws IllegalArgumentException {
        Node<K> node = validate(p);
        if (numChildren(p) == 2)
            throw new IllegalArgumentException("p has two children");
        Node<K> child = (node.getLeft() != null ? node.getLeft() : node.getRight() );
        if (child != null)
            child.setParent(node.getParent());
        if (node == root)
            root = child;
        else {
            Node<K> parent = node.getParent();
            if (node == parent.getLeft())
                parent.setLeft(child);
            else
                parent.setRight(child);
        }
        size--;
        K temp = node.getElement();
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);
        return temp;
    }

    public int getAux(Position<K> p) {
        return ((Node<K>) p).getAux();
    }

    public void setAux(Position<K> p, int value) {
        ((Node<K>) p).setAux(value);
    }


    public Node<K> shallowCopy(Node<K> node){
        if (node == null) return null;
        Node<K> left = shallowCopy(node.getLeft());
        Node<K> right = shallowCopy(node.getRight());
        Node<K> newNode = createNode(node.getElement(), null, null, null);
        if (left != null)
            relink(newNode, left, true);
        if (right != null)
            relink(newNode, right, false);
        return newNode;
    }
	public String toString(){
		return recursivePrint(root, 0);
	}
	public String recursivePrint(Node<K> node, int depth){
		String s = "";
		if (node.getRight() != null)
			s += recursivePrint(node.getRight(), depth+1);
		if (node.getElement() != null){
			for (int i = 0; i < depth; i++)
				s += "  ";
			s += node.getElement();
		}
		if (node.getLeft() != null)
			s += "\n" + recursivePrint(node.getLeft(), depth+1);
		return s;
	}
	
    protected void relink(Node<K> parent, Node<K> child, boolean makeLeftChild) {
        child.setParent(parent);
        if (makeLeftChild)
            parent.setLeft(child);
        else
            parent.setRight(child);
    }
    
    public void rotate(Position<K> p) {
        Node<K> x = validate(p);
        Node<K> y = x.getParent();   
        Node<K> z = y.getParent();     
        if (z == null) {
            root = x;                        
            x.setParent(null);
        } else
            relink(z, x, y == z.getLeft());      
        if (x == y.getLeft()) {
            relink(y, x.getRight(), true);    
            relink(x, y, false);                 
        } else {
            relink(y, x.getLeft(), false);    
            relink(x, y, true);    
        }
    }
	
    public Position<K> restructure(Position<K> x) {
        Position<K> y = parent(x);
        Position<K> z = parent(y);
        if ((x == right(y)) == (y == right(z))) { 
			rotate(y);                          
            return y;                            
        } 
		else {                                
            rotate(x);                              
            rotate(x);
            return x;                           
        }
    }
   
}
