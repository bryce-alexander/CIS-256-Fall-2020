/* Tree234.java */

package dict;

/**
 *  A Tree234 implements an ordered integer dictionary ADT using a 2-3-4 tree.
 *  Only int keys are stored; no object is associated with each key.  Duplicate
 *  keys are not stored in the tree.
 *
 *  @author Jonathan Shewchuk
 **/
public class Tree234 extends dict.IntDictionary {

  /**
   *  You may add fields if you wish, but don't change anything that
   *  would prevent toString() or find() from working correctly.
   *
   *  (inherited)  size is the number of keys in the dictionary.
   *  root is the root of the 2-3-4 tree.
   **/
  dict.Tree234Node root;

  /**
   *  Tree234() constructs an empty 2-3-4 tree.
   *
   *  You may change this constructor, but you may not change the fact that
   *  an empty Tree234 contains no nodes.
   */
  public Tree234() {
    root = null;
    size = 0;
  }

  /**
   *  toString() prints this Tree234 as a String.  Each node is printed
   *  in the form such as (for a 3-key node)
   *
   *      (child1)key1(child2)key2(child3)key3(child4)
   *
   *  where each child is a recursive call to toString, and null children
   *  are printed as a space with no parentheses.  Here's an example.
   *      ((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))
   *
   *  DO NOT CHANGE THIS METHOD.  The test code depends on it.
   *
   *  @return a String representation of the 2-3-4 tree.
   **/
  public String toString() {
    if (root == null) {
      return "";
    } else {
      /* Most of the work is done by Tree234Node.toString(). */
      return root.toString();
    }
  }

  /**
   *  printTree() prints this Tree234 as a tree, albeit sideways.
   *
   *  You're welcome to change this method if you like.  It won't be tested.
   **/
  public void printTree() {
    if (root != null) {
      /* Most of the work is done by Tree234Node.printSubtree(). */
      root.printSubtree(0);
    }
  }

  /**
   *  find() prints true if "key" is in this 2-3-4 tree; false otherwise.
   *
   *  @param key is the key sought.
   *  @return true if "key" is in the tree; false otherwise.
   **/
  public boolean find(int key) {
    dict.Tree234Node node = root;
    while (node != null) {
      if (key < node.key1) {
        node = node.child1;
      } else if (key == node.key1) {
        return true;
      } else if ((node.keys == 1) || (key < node.key2)) {
        node = node.child2;
      } else if (key == node.key2) {
        return true;
      } else if ((node.keys == 2) || (key < node.key3)) {
        node = node.child3;
      } else if (key == node.key3) {
        return true;
      } else {
        node = node.child4;
      }
    }
    return false;
  }

  /**
   *  insert() inserts the key "key" into this 2-3-4 tree.  If "key" is
   *  already present, a duplicate copy is NOT inserted.
   *
   *  @param key is the key sought.
   **/
   public void insert(int key) {
     // Fill in your solution here.

     // If key is not found, proceed with insert, otherwise do nothing.
     if (!find(key)) {
       // If there is no root, new key becomes root.
       // If root exists, start insertHelper() at the top of the tree.
       if (root==null) {
         root = new dict.Tree234Node(null, key);
       } else {
         insertHelper(root, key);
       }
     }
   }

  /**
   * insertHelper() lets you start the insert operation from the parent of a particular node rather than the root.
   * This is designed to allow for proper key placement without restructuring nodes above a recently restructured node
   * and a core purpose of this function is to prevent refactoring 3-nodes which have recently been created as insert()
   * moves through the tree.
   */
  private void insertHelper(dict.Tree234Node currentNode, int key) {
    // Walk down the tree until a leaf or a 3-node is found.
    while (currentNode.child1 != null) {
      if (currentNode.keys != 3) {
        if (key < currentNode.key1) {
          currentNode = currentNode.child1;
        } else if (currentNode.keys == 1 || key < currentNode.key2) {
          currentNode = currentNode.child2;
        } else {
          currentNode = currentNode.child3;
        }
      } else {
        break;
      }
    }
    // If current node is a 3-node, restructure current node and run insert function again.
    // If current node is NOT a 3-node, insert key into current node.
    if (currentNode.keys == 3) {
      // If current node is root, create new root with middle node.
      // If current node is NOT root, move middle key into parent.
      if (root == currentNode) {
        root = new dict.Tree234Node(null, currentNode.key2);
        currentNode.parent = root;
      } else {
        addToNode(currentNode.parent, currentNode.key2);
      }
      // Refactor 3-node and continue insertion algorithm at current point in the tree.
      nodeHelper(currentNode);
      insertHelper(whichChild(currentNode,key), key);
    } else {
      addToNode(currentNode, key);
    }
  }

  /**
   * addToNode() checks where the key to be inserted should land in the current Tree243Node.
   * This was created to reduce repetition and increase readability in the insert() function.
   **/
  private void addToNode(dict.Tree234Node node, int key) {
    // If only one key exists:
    //  -> If new key < current key, shift current key and insert new key in front
    //  -> If new key > current key, insert new key behind
    if (node.keys==1) {
      if (key < node.key1) {
        node.key2 = node.key1;
        node.key1 = key;
      } else {
        node.key2 = key;
      }
    // If two keys exist, find appropriate placement in the 3-node.
    } else {
      if (key < node.key1) {
        node.key3 = node.key2;
        node.key2 = node.key1;
        node.key1 = key;
      }
      else if (key < node.key2) {
        node.key3 = node.key2;
        node.key2 = key;
      }
      else {
        node.key3 = key;
      }
    }
    node.keys++;
  }

  /**
   * nodeHelper() helps to restructure a node and its children which has been affected by insert().
   * It works by splitting the current node into two nodes and assigning the new nodes as the appropriate children
   * of its parent node.  This function also maintains necessary parent/child relationships.
   */
  private void nodeHelper(dict.Tree234Node node) {
    // Create new nodes out of the first and third keys
    dict.Tree234Node firstKey = new dict.Tree234Node(node.parent, node.key1);
    dict.Tree234Node thirdKey = new dict.Tree234Node(node.parent, node.key3);
    // If parent has one key, firstKey and thirdKey become 1st and 2nd children.
    if (node.parent.keys==1) {
      node.parent.child1 = firstKey;
      node.parent.child2 = thirdKey;
    }
    // If parent has two keys:
    // 1) If current node is first child, firstKey and thirdKey become 1st and 2nd children, 2nd child gets moved
    //    to third.
    // 2) If current node is second child, firstKey and thirdKey become 2nd and 3rd children.
    else if (node.parent.keys==2) {
      if (node == node.parent.child1) {
        node.parent.child3 = node.parent.child2;
        node.parent.child1 = firstKey;
        node.parent.child2 = thirdKey;
      }
      else {
        node.parent.child2 = firstKey;
        node.parent.child3 = thirdKey;
      }
    }
    // If parent has three keys:
    // 1) If current node is first child, firstKey and thirdKey become 1st and 2nd children, 2nd and 3rd children get
    //    moved to third and fourth.
    // 2) If current node is second child, firstKey and thirdKey become 2nd and 3rd children, 3rd child becomes 4th.
    // 3) If current node is third child, firstKey and thirdKey become 3rd and 4th children.
    else {
      if (node == node.parent.child1) {
        node.parent.child4 = node.parent.child3;
        node.parent.child3 = node.parent.child2;
        node.parent.child1 = firstKey;
        node.parent.child2 = thirdKey;
      }
      else if (node == node.parent.child2) {
        node.parent.child4 = node.parent.child3;
        node.parent.child2 = firstKey;
        node.parent.child3 = thirdKey;
      }
      else {
        node.parent.child3 = firstKey;
        node.parent.child4 = thirdKey;
      }
    }
    // Once refactoring the node is complete, assign parents and children (if they exist)
    if (node.child1 != null) {
      node.child1.parent = firstKey;
      firstKey.child1 = node.child1;
    }
    if (node.child2 != null) {
      node.child2.parent = firstKey;
      firstKey.child2 = node.child2;
    }
    if (node.child3 != null) {
      node.child3.parent = thirdKey;
      thirdKey.child1 = node.child3;
    }
    if (node.child4 != null) {
      node.child4.parent = thirdKey;
      thirdKey.child2 = node.child4;
    }
  }



  /**
   * whichChild() determines the appropriate child for continuation of the insert() method.
   * The primary purpose of this function is to ensure that, after refactoring a 3-node, the
   * insert() algorithm can continue from where it left off rather than starting at the top
   * of the tree.
   */
  private dict.Tree234Node whichChild(dict.Tree234Node node, int key) {
    if (key < node.parent.key1) {
      return node.parent.child1;
    } else if (key < node.parent.key2 || node.parent.keys==1) {
      return node.parent.child2;
    } else if (key < node.parent.key3 || node.parent.keys==2) {
      return node.parent.child3;
    } else {
      return node.parent.child4;
    }
  }
  /**
   *  testHelper() prints the String representation of this tree, then
   *  compares it with the expected String, and prints an error message if
   *  the two are not equal.
   *
   *  @param correctString is what the tree should look like.
   **/
  public void testHelper(String correctString) {
    String treeString = toString();
    System.out.println(treeString);
    if (!treeString.equals(correctString)) {
      System.out.println("ERROR:  Should be " + correctString);
    }
  }

  /**
   *  main() is a bunch of test code.  Feel free to add test code of your own;
   *  this code won't be tested or graded.
   **/
  public static void main(String[] args) {
    Tree234 t = new Tree234();

    System.out.println("\nInserting 84.");
    t.insert(84);
    t.testHelper("84");

    System.out.println("\nInserting 7.");
    t.insert(7);
    t.testHelper("7 84");

    System.out.println("\nInserting 22.");
    t.insert(22);
    t.testHelper("7 22 84");

    System.out.println("\nInserting 95.");
    t.insert(95);
    t.testHelper("(7)22(84 95)");

    System.out.println("\nInserting 50.");
    t.insert(50);
    t.testHelper("(7)22(50 84 95)");

    System.out.println("\nInserting 11.");
    t.insert(11);
    t.testHelper("(7 11)22(50 84 95)");

    System.out.println("\nInserting 37.");
    t.insert(37);
    t.testHelper("(7 11)22(37 50)84(95)");

    System.out.println("\nInserting 60.");
    t.insert(60);
    t.testHelper("(7 11)22(37 50 60)84(95)");

    System.out.println("\nInserting 1.");
    t.insert(1);
    t.testHelper("(1 7 11)22(37 50 60)84(95)");

    System.out.println("\nInserting 23.");
    t.insert(23);
    t.testHelper("(1 7 11)22(23 37)50(60)84(95)");

    System.out.println("\nInserting 16.");
    t.insert(16);
    t.testHelper("((1)7(11 16)22(23 37))50((60)84(95))");

    System.out.println("\nInserting 100.");
    t.insert(100);
    t.testHelper("((1)7(11 16)22(23 37))50((60)84(95 100))");

    System.out.println("\nInserting 28.");
    t.insert(28);
    t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(95 100))");

    System.out.println("\nInserting 86.");
    t.insert(86);
    t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(86 95 100))");

    System.out.println("\nInserting 49.");
    t.insert(49);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))");

    System.out.println("\nInserting 81.");
    t.insert(81);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((60 81)84(86 95 100))");

    System.out.println("\nInserting 51.");
    t.insert(51);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86 95 100))");

    System.out.println("\nInserting 99.");
    t.insert(99);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86)95(99 100))");

    System.out.println("\nInserting 75.");
    t.insert(75);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(75 81)84(86)95" +
                 "(99 100))");

    System.out.println("\nInserting 66.");
    t.insert(66);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(66 75 81))84((86)95" +
                 "(99 100))");

    System.out.println("\nInserting 4.");
    t.insert(4);
    t.testHelper("((1 4)7(11 16))22((23)28(37 49))50((51)60(66 75 81))84" +
                 "((86)95(99 100))");

    System.out.println("\nInserting 80.");
    t.insert(80);
    t.testHelper("(((1 4)7(11 16))22((23)28(37 49)))50(((51)60(66)75" +
                 "(80 81))84((86)95(99 100)))");

    System.out.println("\nFinal tree:");
    t.printTree();
  }

}
