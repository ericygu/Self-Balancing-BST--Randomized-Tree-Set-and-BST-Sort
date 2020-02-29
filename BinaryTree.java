public interface BinaryTree<K> {
    public Position<K> root();
    public Position<K> left(Position<K> p);
    public Position<K> right(Position<K> p);
    public Position<K> parent(Position<K> p);
    public int size();
    public boolean isEmpty();
}