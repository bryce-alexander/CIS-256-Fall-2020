/* SibTreeNode.java */

package tree;

import javax.security.auth.Subject;

/**
 *  A SibTreeNode object is a node in a SibTree (sibling-based general tree).
 *  @author Jonathan Shewchuk
 */

class SibTreeNode extends TreeNode {

  /**
   *  (inherited)  item references the item stored in this node.
   *  (inherited)  valid is true if and only if this is a valid node in some
   *               Tree.
   *  myTree references the Tree that contains this node.
   *  parent references this node's parent node.
   *  firstChild references this node's first (leftmost) child.
   *  nextSibling references this node's next sibling.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  /**
   *  ADT implementation invariants:
   *  1) if valid == true, myTree != null.
   *  2) if valid == true, then this is a descendent of myTree.root.
   *  3) if valid == true, myTree satisfies all the invariants of a
   *     SibTree (listed in SibTree.java).
   */

  protected tree.SibTree myTree;
  protected SibTreeNode parent;
  protected SibTreeNode firstChild;
  protected SibTreeNode nextSibling;

  /**
   * Construct a valid SibTreeNode referring to a given item.
   */
  SibTreeNode(tree.SibTree tree, Object item) {
    this.item = item;
    valid = true;
    myTree = tree;
    parent = null;
    firstChild = null;
    nextSibling = null;
  }

  /**
   * Construct an invalid SibTreeNode.
   */
  SibTreeNode() {
    valid = false;
  }

  /**
   *  children() returns the number of children of the node at this position.
   *  WARNING:  Does not run in constant time.  Actually counts the kids.
   */
  public int children() {
    if (isValidNode()) {
      int count = 0;
      SibTreeNode countNode = firstChild;
      while (countNode != null) {
        count++;
        countNode = countNode.nextSibling;
      }
      return count;
    } else {
      return 0;
    }
  }

  /**
   *  parent() returns the parent TreeNode of this TreeNode.  Throws an
   *  exception if `this' is not a valid node.  Returns an invalid TreeNode if
   *  this node is the root.
   */
  public TreeNode parent() throws tree.InvalidNodeException {
    // REPLACE THE FOLLOWING LINE WITH YOUR SOLUTION TO PART I.
    if (!isValidNode()) { throw new tree.InvalidNodeException(); }
    else if ((TreeNode)this==this.myTree.root()) { return new SibTreeNode(); }
    return (TreeNode)this.parent;
  }

  /**
   *  child() returns the cth child of this TreeNode.  Throws an exception if
   *  `this' is not a valid node.  Returns an invalid TreeNode if there is no
   *  cth child.
   */
  public TreeNode child(int c) throws tree.InvalidNodeException {
    if (isValidNode()) {
      if (c < 1) {
        return new SibTreeNode();
      }
      SibTreeNode kid = firstChild;
      while ((kid != null) && (c > 1)) {
        kid = kid.nextSibling;
        c--;
      }
      if (kid == null) {
        return new SibTreeNode();
      } else {
        return kid;
      }
    } else {
      throw new tree.InvalidNodeException();
    }
  }

  /**
   *  nextSibling() returns the next sibling TreeNode to the right from this
   *  TreeNode.  Throws an exception if `this' is not a valid node.  Returns
   *  an invalid TreeNode if there is no sibling to the right of this node.
   */
  public TreeNode nextSibling() throws tree.InvalidNodeException {
    if (isValidNode()) {
      if (nextSibling == null) {
        return new SibTreeNode();
      } else {
        return nextSibling;
      }
    } else {
      throw new tree.InvalidNodeException();
    }
  }

  /**
   *  insertChild() inserts an item as the cth child of this node.  Existing
   *  children numbered c or higher are shifted one place to the right
   *  to accommodate.  If the current node has fewer than c children,
   *  the new item is inserted as the last child.  If c < 1, act as if c is 1.
   *
   *  Throws an InvalidNodeException if "this" node is invalid.
   */
  public void insertChild(Object item, int c) throws tree.InvalidNodeException {
    // FILL IN YOUR SOLUTION TO PART II HERE.
    if (!isValidNode()) {
      throw new tree.InvalidNodeException();
    }
    SibTreeNode newChild = new SibTreeNode(this.myTree, item);
    SibTreeNode currentNode = (SibTreeNode) this.child(1);
    newChild.parent = this;
    myTree.size++;
    if (c <= 1) {
      if (currentNode.isValidNode()) { newChild.nextSibling = currentNode; }
      firstChild = newChild;
      return;
    } else if (c > this.children()) {
      c = this.children() + 1;
    }
    SibTreeNode prevNode = new SibTreeNode();
    for (int childIndex = 1; c > childIndex; childIndex++) {
      prevNode = currentNode;
      currentNode = currentNode.nextSibling;
    }
    newChild.nextSibling = currentNode;
    prevNode.nextSibling = newChild;
  }

  /**
   *  removeLeaf() removes the node at the current position from the tree if
   *  it is a leaf.  Does nothing if `this' has one or more children.  Throws
   *  an exception if `this' is not a valid node.  If 'this' has siblings to
   *  its right, those siblings are all shifted left by one.
   */
  public void removeLeaf() throws tree.InvalidNodeException {
    // FILL IN YOUR SOLUTION TO PART III HERE.
      if (!isValidNode()) {
        throw new tree.InvalidNodeException();
      }
      if (children() == 0) {
        if (this == myTree.root) {
          myTree.root = null;
        } else {
          SibTreeNode parentNode = parent;
          if (parentNode.firstChild == this) {
            parentNode.firstChild = nextSibling;
          } else {
            SibTreeNode currentChild = parentNode.firstChild;
            while (currentChild.nextSibling != this) {
              currentChild = currentChild.nextSibling;
            }
            currentChild.nextSibling = nextSibling;
          }
        }
        valid = false;
        myTree.size--;
      }
    }

}
