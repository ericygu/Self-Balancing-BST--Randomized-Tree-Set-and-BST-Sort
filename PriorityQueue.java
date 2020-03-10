package com.company;

public interface PriorityQueue<K> {
    public boolean isEmpty();
    public int size();
    public void insert(K k);
    public K removeMin();
    public K min();
}
