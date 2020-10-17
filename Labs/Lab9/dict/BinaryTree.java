/* BinaryTree.java */

package dict;

/**
 *  BinaryTree implements a Dictionary as a binary tree (unbalanced).  Multiple
 *  entries with the same key are permitted.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 *
 *  @author Jonathan Shewchuk
 **/
public class BinaryTree implements Dictionary {

  /** 
   *  size is the number of items stored in the dictionary.
   *  root is the BinaryTreeNode that serves as root of the tree.
   *  If there are no items, size is zero and root is null.
   **/
  protected int size;
  protected dict.BinaryTreeNode root;

  /**
   *  Construct an empty binary tree.
   **/
  public BinaryTree() {
    makeEmpty();
  }

  /**
   *  makeEmpty() removes all the entries from the dictionary.
   */
  public void makeEmpty() {
    size = 0;
    root = null;
  }

  /** 
   *  size() returns the number of entries stored in the dictionary.
   *
   *  @return the number of entries stored in the dictionary.
   **/
  public int size() {
    return size;
  }

  /** 
   *  isEmpty() tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/
  public boolean isEmpty() {
    return size == 0;
  }

  /** 
   *  insert() constructs and inserts a new Entry object, consisting of
   *  a (key, value) pair, into the dictionary, and returns a reference to the
   *  new Entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  @param key the key by which the entry can be retrieved.  Must be of
   *  a class that implements java.lang.Comparable.
   *  @param value an arbitrary object associated with the key.
   *  @return an Entry object referencing the key and value.
   **/
  public Entry insert(Object key, Object value) {
    Entry entry = new Entry(key, value);
    if (root == null) {
      root = new dict.BinaryTreeNode(entry);
    } else {
      insertHelper(entry, (Comparable) key, root);
    }

    size++;
    return entry;
  }

  /**
   *  insertHelper() recursively does the work of inserting a new Entry object
   *  into the dictionary.
   *
   *  @param entry the Entry object to insert into the tree.
   *  @param key the key by which the entry can be retrieved.
   *  @param node the root of a subtree in which the new entry will be
   *         inserted.
   **/
  private void insertHelper(Entry entry, Comparable key, dict.BinaryTreeNode node) {
    if (key.compareTo(node.entry.key()) <= 0) {
      if (node.leftChild == null) {
	node.leftChild = new dict.BinaryTreeNode(entry, node);
      } else {
	insertHelper(entry, key, node.leftChild);
      }
    } else {
      if (node.rightChild == null) {
	node.rightChild = new dict.BinaryTreeNode(entry, node);
      } else {
	insertHelper(entry, key, node.rightChild);
      }
    }
  }

  /** 
   *  find() searches for an entry with the specified key.  If such an entry is
   *  found, it returns the Entry object; otherwise, it returns null.  If more
   *  than one entry has the key, one of them is chosen arbitrarily and
   *  returned.
   *
   *  @param key the search key.  Must be of a class that implements
   *         java.lang.Comparable.
   *  @return an Entry referencing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/
  public Entry find(Object key) {
    dict.BinaryTreeNode node = findHelper((Comparable) key, root);
    if (node == null) {
      return null;
    } else {
      return node.entry;
    }
  }

  /**
   *  Search for a node with the specified key, starting from "node".  If
   *  a matching key is found (meaning that key1.compareTo(key2) == 0), return
   *  a node containing that key.  Otherwise, return null.
   *
   *  Be sure this method returns null if node == null.
   **/

  private dict.BinaryTreeNode findHelper(Comparable key, dict.BinaryTreeNode node) {
    // Replace the following line with your solution.
    while (node != null) {
      int comparedValue = key.compareTo(node.entry.key());
      if (comparedValue < 0) {
        node = node.leftChild;
      } else if (comparedValue > 0) {
        node = node.rightChild;
      } else {
        return node;
      }
    }
    return null;
  }

  /** 
   *  remove() searches for an entry with the specified key.  If such an entry
   *  is found, it removes the Entry object from the Dictionary and returns it;
   *  otherwise, it returns null.  If more than one entry has the key, one of
   *  them is chosen arbitrarily, removed, and returned.
   *
   *  @param key the search key.  Must be of a class that implements
   *         java.lang.Comparable.
   *  @return an Entry referencing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/
  public Entry remove(Object key) {
    // Replace the following line with your solution.
    dict.BinaryTreeNode keyNode = findHelper((Comparable) key, root);
    // If keyNode is not null, continue evaluation, else return null.
    if (keyNode != null) {

      // If keyNode has no children, replace pointer from parent with null
      if (keyNode.leftChild == null && keyNode.rightChild == null) {
        // If keyNode is the root with no children, make root null
        if (keyNode == root) {
          root = null;
        } else {
          // If keyNode is not root, assign the parent's appropriate child to null.
          if (keyNode.parent.leftChild == keyNode) {
            keyNode.parent.leftChild = null;
          } else {
            keyNode.parent.rightChild = null;
          }
          size--;
          return keyNode.entry;
        }

      // If keyNode has only one child, make parent's appropriate child point to keyNode's child.
      } else if (keyNode.leftChild == null || keyNode.rightChild == null) {
        // If the only child is the left child:
        if (keyNode.leftChild != null) {
          // If keyNode is not the root, connect parent to appropriate child, else left child is new root
          if (keyNode.parent != null) {
            if (keyNode.parent.leftChild == keyNode) {
              keyNode.parent.leftChild = keyNode.leftChild;
            } else {
              keyNode.parent.rightChild = keyNode.leftChild;
            }
          } else {
            root = keyNode.leftChild;
          }
          // If the only child is the right child:
        } else if (keyNode.rightChild != null) {
          // If keyNode is not the root, connect parent to appropriate child, else right child is new root
          if (keyNode.parent != null) {
            if (keyNode.parent.leftChild == keyNode) {
              keyNode.parent.leftChild = keyNode.rightChild;
            } else {
              keyNode.parent.rightChild = keyNode.rightChild;
            }
          } else {
            root = keyNode.rightChild;
          }
        }
        size--;
        return keyNode.entry;

      // If keyNode has more than two children:
      } else {
        // Find the smallest number in the right subtree
        dict.BinaryTreeNode min = keyNode.rightChild;
        Entry removedEntry = keyNode.entry;
        while (min.leftChild != null) {
          min = min.leftChild;
        }
        // Recursively remove the smallest key found from the tree
        remove(min.entry.key());
        // Replace keyNode's entry with smallest entry in right subtree
        keyNode.entry = min.entry;
        return removedEntry;
      }
    }
    return null;
  }

  /**
   *  Convert the tree into a string.
   **/

  public String toString() {
    if (root == null) {
      return "";
    } else {
      return root.toString();
    }
  }

  /* Tests the binary search tree. */
  public static void main(String[] args) {
    BinaryTree tree = new BinaryTree();

    System.out.println("Inserting 1A, 6V, 3K, 2Z, 5L, 9L:");
    tree.insert(new Integer(1), "A");
    tree.insert(new Integer(6), "V");
    tree.insert(new Integer(3), "K");
    tree.insert(new Integer(2), "Z");
    tree.insert(new Integer(5), "L");
    tree.insert(new Integer(9), "L");
    System.out.println("The tree is:  " + tree);
    System.out.println("Size:  " + tree.size());

    System.out.println("\nTesting find() ...");
    tree.testFind(1, "A");
    tree.testFind(9, "L");
    tree.testFind(5, "L");
    tree.testFind(4, null);
    tree.testFind(6, "V");
    tree.testFind(3, "K");

    System.out.println("\nTesting remove() (for nodes with < 2 children) ...");
    tree.testRemove(5, "1A(((2Z)3K)6V(9L))");
    tree.testRemove(3, "1A((2Z)6V(9L))");
    tree.testRemove(1, "(2Z)6V(9L)");
    tree.insert(new Integer(7), "S");
    tree.insert(new Integer(8), "X");
    tree.insert(new Integer(10), "B");
    System.out.println("After inserting 7S, 8X, 10B:  " + tree);
    System.out.println("Size:  " + tree.size());
    if (tree.size() != 6) {
      System.out.println("  SHOULD BE 6.");
    }

    System.out.println("\nTesting remove() (for nodes with 2 children) ...");
    tree.testRemove(6, "(2Z)7S((8X)9L(10B))");
    tree.testRemove(9, "(2Z)7S((8X)10B)");
    System.out.println("Size:  " + tree.size());
    if (tree.size() != 4) {
      System.out.println("  SHOULD BE 4.");
    }
  }

  private void testRemove(int n, String shouldBe) {
    Integer key = new Integer(n);
    System.out.print("After remove(" + n + "):  ");
    remove(key);
    System.out.println(this);
    if (!toString().equals(shouldBe)) {
      System.out.println("  SHOULD BE " + shouldBe);
    }
  }

  private void testFind(int n, Object truth) {
    Integer key = new Integer(n);
    Entry entry = find(key);
    System.out.println("Calling find() on " + n);
    if (entry == null) {
      System.out.println("  returned null.");
      if (truth != null) {
        System.out.println("  SHOULD BE " + truth + ".");
      }
    } else {
      System.out.println("  returned " + entry.value() + ".");
      if (!entry.value().equals(truth)) {
        if (truth == null) {
          System.out.println("  SHOULD BE null.");
        } else {
          System.out.println("  SHOULD BE " + truth + ".");
        }
      }
    }
  }
  
}
