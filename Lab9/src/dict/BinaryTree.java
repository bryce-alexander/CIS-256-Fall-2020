/* BinaryTree.java */

package dict;

import com.sun.source.tree.CaseTree;

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
   * size is the number of items stored in the dictionary.
   * root is the BinaryTreeNode that serves as root of the tree.
   * If there are no items, size is zero and root is null.
   **/
  protected int size;
  protected BinaryTreeNode root;

  /**
   * Construct an empty binary tree.
   **/
  public BinaryTree() {
    makeEmpty();
  }

  /**
   * makeEmpty() removes all the entries from the dictionary.
   */
  public void makeEmpty() {
    size = 0;
    root = null;
  }

  /**
   * size() returns the number of entries stored in the dictionary.
   *
   * @return the number of entries stored in the dictionary.
   **/
  public int size() {
    return size;
  }

  /**
   * isEmpty() tests if the dictionary is empty.
   *
   * @return true if the dictionary has no entries; false otherwise.
   **/
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * insert() constructs and inserts a new Entry object, consisting of
   * a (key, value) pair, into the dictionary, and returns a reference to the
   * new Entry.  Multiple entries with the same key (or even the same key and
   * value) can coexist in the dictionary.
   *
   * @param key   the key by which the entry can be retrieved.  Must be of
   *              a class that implements java.lang.Comparable.
   * @param value an arbitrary object associated with the key.
   * @return an Entry object referencing the key and value.
   **/
  public Entry insert(Object key, Object value) {
    Entry entry = new Entry(key, value);
    if (root == null) {
      root = new BinaryTreeNode(entry);
    } else {
      insertHelper(entry, (Comparable) key, root);
    }

    size++;
    return entry;
  }

  /**
   * insertHelper() recursively does the work of inserting a new Entry object
   * into the dictionary.
   *
   * @param entry the Entry object to insert into the tree.
   * @param key   the key by which the entry can be retrieved.
   * @param node  the root of a subtree in which the new entry will be
   *              inserted.
   **/
  private void insertHelper(Entry entry, Comparable key, BinaryTreeNode node) {
    if (key.compareTo(node.entry.key()) <= 0) {
      if (node.leftChild == null) {
        node.leftChild = new BinaryTreeNode(entry, node);
      } else {
        insertHelper(entry, key, node.leftChild);
      }
    } else {
      if (node.rightChild == null) {
        node.rightChild = new BinaryTreeNode(entry, node);
      } else {
        insertHelper(entry, key, node.rightChild);
      }
    }
  }

  /**
   * find() searches for an entry with the specified key.  If such an entry is
   * found, it returns the Entry object; otherwise, it returns null.  If more
   * than one entry has the key, one of them is chosen arbitrarily and
   * returned.
   *
   * @param key the search key.  Must be of a class that implements
   *            java.lang.Comparable.
   * @return an Entry referencing the key and an associated value, or null if
   * no entry contains the specified key.
   **/
  public Entry find(Object key) {
    BinaryTreeNode node = findHelper((Comparable) key, root);
    if (node == null) {
      return null;
    } else {
      return node.entry ;
    }
  }

  /**
   * Search for a node with the specified key, starting from "node".  If
   * a matching key is found (meaning that key1.compareTo(key2) == 0), return
   * a node containing that key.  Otherwise, return null.
   * <p>
   * Be sure this method returns null if node == null.
   **/

  private BinaryTreeNode findHelper(Comparable key, BinaryTreeNode node) {
    if (node == null) {
      return null;
    }
    if (key.compareTo(node.entry.key()) < 0) {
      return findHelper(key, node.leftChild);
    }
    else if (key.compareTo(node.entry.key()) > 0) {
      return findHelper(key, node.rightChild);
    }
    return node;
  }


  /**
   * remove() searches for an entry with the specified key.  If such an entry
   * is found, it removes the Entry object from the Dictionary and returns it;
   * otherwise, it returns null.  If more than one entry has the key, one of
   * them is chosen arbitrarily, removed, and returned.
   *
   * @param key the search key.  Must be of a class that implements
   *            java.lang.Comparable.
   * @return an Entry referencing the key and an associated value, or null if
   * no entry contains the specified key.
   **/
  public Entry remove(Object key) {
    BinaryTreeNode remNode = findHelper((Comparable) key, root);
    if (remNode == null) {
      return null;
    }
    if (remNode.leftChild != null && remNode.rightChild == null) {
      if (remNode == root) {
        root = remNode.leftChild;
        size--;
        return remNode.entry;
      }
       if (remNode.parent.leftChild == remNode) {
        remNode.parent.leftChild = remNode.leftChild;
        size--;
        return remNode.entry;
      }
       if (remNode.parent.rightChild == remNode) {
        remNode.parent.rightChild = remNode.leftChild;
        size--;
        return remNode.entry;
      }

    }
    if (remNode.leftChild == null && remNode.rightChild == null) {
      if (remNode == root) {
        root = null;
        size--;
        return remNode.entry;
      } else if (remNode.parent.leftChild == remNode) {
        remNode.parent.leftChild = null;
        size--;
        return remNode.entry;
      } else if (remNode.parent.rightChild == remNode) {
        remNode.parent.rightChild = null;
        size--;
        return remNode.entry;
      }
    }
    if (remNode.rightChild != null && remNode.leftChild == null) {
      if (remNode == root) {
        root = remNode.rightChild;
        size--;
        return remNode.entry;

      } else if (remNode.parent.leftChild == remNode) {
        remNode.parent.leftChild = remNode.rightChild;
        size--;
        return remNode.entry;

      } else if (remNode.parent.rightChild == remNode) {
        remNode.parent.rightChild = remNode.rightChild;
        size--;
        return remNode.entry;
      }
    }

    if (remNode.leftChild != null && remNode.rightChild != null) {
      BinaryTreeNode holdNode;
      if (remNode.rightChild.leftChild != null){
        holdNode = new BinaryTreeNode(remNode.rightChild.leftChild.entry);
        while (holdNode.leftChild != null) {
          holdNode = findHelper((Comparable) holdNode.entry.key(), holdNode);
          holdNode = holdNode.leftChild;
        }
      }
      else holdNode = new BinaryTreeNode(remNode.rightChild.entry);
      if (remNode == root) {
          remove(holdNode.entry.key);
          holdNode.rightChild = remNode.rightChild;
          holdNode.leftChild=remNode.leftChild;
          root=holdNode;
          return remNode.entry;
        }
      if (remNode.parent.leftChild == remNode) {
          remove(holdNode.entry.key);
          holdNode.rightChild = remNode.rightChild.rightChild;
          holdNode.leftChild = remNode.leftChild;
          remNode.parent.leftChild = holdNode;
          return remNode.entry;
        }
      else {
          remove(holdNode.entry.key);
          holdNode.leftChild=remNode.leftChild;
          remNode.parent.rightChild = holdNode;
          remNode.entry=holdNode.entry;
          return remNode.entry;
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
