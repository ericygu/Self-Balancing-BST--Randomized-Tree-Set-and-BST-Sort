# Multiple Sorts with Runtime and Splay Rebalancing in BST
# Part 1 of Project:
Self-balancing binary search trees are all about rotations. 
In LinkedBinaryTree.java there is an implementation of a binary tree. The rotate( ) method
takes in a Position and rotates it with its parent.

Two binary search trees on the same set of keys can be transformed into each other via
a sequence of tree rotations. In the file TreeRotator.java, I implemented the printRotations( )
method, which prints out a sequence of keys corresponding to the nodes one needs to
rotate to get from the first tree to the second. 

For example, given the following two trees:
0 1 4 3 2 5 (BST, think of inserting as something linear)
and
1 0 3 2 4 5 (likewise)
the method should print a sequence like
3 2 3 1

Made an efficient  implementation of this method running in time O(n) that prints out of
a sequence of O(n) many rotations, where n is the number of keys.

# Part 2 of Project:

And, as binary search trees can be used to implement the priority queue
abstract data type and in fact can be used to sort elements in a variety of different
ways, I looked at two different strategies for maintaining the binary search tree:
• Using a self-balancing binary search tree (splay tree in this instance, although I have barebones AVL Tree code as well)
• Inserting keys in a random order without rebalancing. (RandomizedTreeSet)
and two strategies for extracting the elements in sorted order:
• Thinking of a binary search tree as a priority queue and repeatedly calling removeMin( ).
• Using an in-order traversal of the tree.
TreeSet.java implements the PriorityQueue interface. SelfBalancingTreeSet.java has splay tree implemented. 
Coded up the two extraction methods in TreeSorter.java 

In the same file, wrote a tester method that compares the four approaches on a large randomly
shuffled list (e.g., of 2,000,000 elements). 

# Text File:
Attached a seperate txt file that is a write up which includes the time it took for those above strategies for part 2, and measured performance for both part 1 & 2. 
