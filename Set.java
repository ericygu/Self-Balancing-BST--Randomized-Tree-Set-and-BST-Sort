public interface Set<K> {
    public boolean contains(K key) throws IllegalArgumentException;
    public boolean put(K key) throws IllegalArgumentException;
    public K remove(K key) throws IllegalArgumentException;
    public int size();
}
