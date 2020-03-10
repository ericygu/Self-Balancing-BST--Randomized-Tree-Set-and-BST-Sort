package com.company;

public class SelfBalancingTreeSet<K> extends TreeSet<K> {
	//your implementation goes here
    //Splay Tree
    private void splay(Position<K> p) {
        while (!isRoot(p)) {
            Position<K> parent = parent(p);
            Position<K> grand = parent(parent);
            if (grand == null) // zig case
                rotate(p);
            else if ((parent == left(grand)) == (p == left(parent))) { // zig-zig case
                rotate(parent); // move PARENT upward
                rotate(p); // then move p upward
            }
            else { // zig-zag case
                rotate(p); // move p upward
                rotate(p); // move p upward again
            }
        }
    }
    protected void rebalanceInsert(Position<K> p){
        splay(p);
    }
    protected void rebalanceDelete(Position<K> p){
        if (!isRoot(p)) splay(parent(p));
    }
    protected void rebalanceAccess(Position<K> p){
        if (isExternal(p)) p = parent(p);
        if (p != null) splay(p);
    }

    /*
    public static void main(String[] args) {
        SelfBalancingTreeSet<Integer> a = new SelfBalancingTreeSet<>();
        a.insert(5);
        a.insert(4);
        a.insert(-1);
        a.insert(-2);
        System.out.println(a);

        while(!a.isEmpty()) {
            System.out.println(a.removeMin());
            System.out.println(a.size());
        }
    }

 */

    /*
    AVL Implementation
    protected int height(Position<K> p) {
        return tree.getAux(p);
    }

    protected boolean isBalanced(Position<K> p) {
        return Math.abs(height(left(p))-height(right(p))) <= 1;
    }

    protected void recomputeHeight(Position<K> p) {
        tree.setAux(p, 1 + Math.max(height(left(p)), height(right(p))));
    }

    protected Position<K> tallerChild(Position<K> p) {
        if (height(left(p)) > height(right(p))) {
            return left(p); // clear winner
        }
        if (height(left(p)) < height(right(p))) {
            return right(p); // clear winner
        }
        // equal height children; break tie while matching parent's orientation
        if (isRoot(p)) {
            return left(p); // choice is irrelevant
        }
        if (p == left(parent(p))) {
            return left(p); // return aligned child
        }
        else {
            return right(p);
        }
    }

    protected void rebalance(Position<K> p) {
        int oldHeight, newHeight;
        do {
            oldHeight = height(p); // not yet recalculated if internal
            if (!isBalanced(p)) { // imbalance detected
            // perform trinode restructuring, setting p to resulting root,
            // and recompute new local heights after the restructuring
            p = restructure(tallerChild(tallerChild(p)));
            recomputeHeight(left(p));
            recomputeHeight(right(p));
            }

            recomputeHeight(p);
            newHeight = height(p);
            p = parent(p);
            } while (oldHeight != newHeight && p != null);

    }

 */

}
