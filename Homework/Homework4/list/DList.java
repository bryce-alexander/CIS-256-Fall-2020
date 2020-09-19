/* DList.java */

package list;

/**
 *  A DList is a mutable doubly-linked list ADT.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 *
 *  DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 */

public class DList {

  /**
   *  head references the sentinel node.
   *  size is the number of items in the list.  (The sentinel node does not
   *       store an item.)
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected list.DListNode head;
  protected int size;

  /* DList invariants:
   *  1)  head != null.
   *  2)  For any DListNode x in a DList, x.next != null.
   *  3)  For any DListNode x in a DList, x.prev != null.
   *  4)  For any DListNode x in a DList, if x.next == y, then y.prev == x.
   *  5)  For any DListNode x in a DList, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNodes, NOT COUNTING the sentinel,
   *      that can be accessed from the sentinel (head) by a sequence of
   *      "next" references.
   */

  /**
   *  newNode() calls the DListNode constructor.  Use this class to allocate
   *  new DListNodes rather than calling the DListNode constructor directly.
   *  That way, only this method needs to be overridden if a subclass of DList
   *  wants to use a different kind of node.
   *  @param item the item to store in the node.
   *  @param prev the node previous to this node.
   *  @param next the node following this node.
   */
  protected list.DListNode newNode(Object item, list.DListNode prev, list.DListNode next) {
    return new list.DListNode(item, prev, next);
  }

  /**
   *  DList() constructor for an empty DList.
   */
  public DList() {
    //  Your solution here.
    head = newNode(null, null, null);
    head.next = head;
    head.prev = head;
    size = 0;
  }

  /**
   *  isEmpty() returns true if this DList is empty, false otherwise.
   *  @return true if this DList is empty, false otherwise. 
   *  Performance:  runs in O(1) time.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /** 
   *  length() returns the length of this DList. 
   *  @return the length of this DList.
   *  Performance:  runs in O(1) time.
   */
  public int length() {
    return size;
  }

  /**
   *  insertFront() inserts an item at the front of this DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertFront(Object item) {
    // Your solution here.
    list.DListNode insertNode = newNode(item, head, head.next);
    head.next.prev = insertNode;
    head.next = insertNode;
    size++;
  }

  /**
   *  insertBack() inserts an item at the back of this DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertBack(Object item) {
    // Your solution here.

  }

  /**
   *  front() returns the node at the front of this DList.  If the DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the front of this DList.
   *  Performance:  runs in O(1) time.
   */
  public list.DListNode front() {
    // Your solution here.
    if (isEmpty()) { return null;}
    else { return head.next; }
  }

  /**
   *  back() returns the node at the back of this DList.  If the DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the back of this DList.
   *  Performance:  runs in O(1) time.
   */
  public list.DListNode back() {
    // Your solution here.
    if (isEmpty()) { return null;}
    else { return head.prev; }
  }

  /**
   *  next() returns the node following "node" in this DList.  If "node" is
   *  null, or "node" is the last node in this DList, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @param node the node whose successor is sought.
   *  @return the node following "node".
   *  Performance:  runs in O(1) time.
   */
  public list.DListNode next(list.DListNode node) {
    // Your solution here.
    if (node==null || node.next==head) { return null; }
    return node.next;
  }

  /**
   *  prev() returns the node prior to "node" in this DList.  If "node" is
   *  null, or "node" is the first node in this DList, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @param node the node whose predecessor is sought.
   *  @return the node prior to "node".
   *  Performance:  runs in O(1) time.
   */
  public list.DListNode prev(list.DListNode node) {
    // Your solution here.
    if (node==null || node.prev==head) { return null; }
    return node.prev;
  }

  /**
   *  insertAfter() inserts an item in this DList immediately following "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item after.
   *  Performance:  runs in O(1) time.
   */
  public void insertAfter(Object item, list.DListNode node) {
    // Your solution here.
    if (node==null) { return; }
    list.DListNode insertNode = newNode(item, node, node.next);
    node.next.prev = insertNode;
    node.next = insertNode;
    size++;

  }

  /**
   *  insertBefore() inserts an item in this DList immediately before "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item before.
   *  Performance:  runs in O(1) time.
   */
  public void insertBefore(Object item, list.DListNode node) {
    // Your solution here.
    if (node==null) { return; }
    list.DListNode insertNode = newNode(item, node.prev, node);
    node.prev.next = insertNode;
    node.prev = insertNode;
    size++;
  }

  /**
   *  remove() removes "node" from this DList.  If "node" is null, do nothing.
   *  Performance:  runs in O(1) time.
   */
  public void remove(list.DListNode node) {
    // Your solution here.
    if (node==null) { return; }
      node.prev.next = node.next;
      node.next.prev = node.prev;
      size--;
  }

  /**
   *  toString() returns a String representation of this DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this DList.
   *  Performance:  runs in O(n) time, where n is the length of the list.
   */
  public String toString() {
    String result = "[  ";
    list.DListNode current = head.next;
    System.out.println(head.next==head);
    while (current != head) {
      result = result + current.item + "  ";
      current = current.next;
    }
    return result + "]";
  }

  public static void main(String[] args){

    DList testList = new DList();

    System.out.println("Initialize new DList. [ ]  New list is: " + testList.toString());
    System.out.println("Size (should be 0): " + testList.length());
    System.out.println("Is empty? (should be true): " + testList.isEmpty());
    System.out.println("Sentinel prev (should be null): " + testList.prev(testList.head));
    System.out.println("Sentinel next (should be null): " + testList.next(testList.head));
    System.out.println("Sentinel points to self in both directions? " + (testList.head.next==testList.head && testList.head.prev==testList.head));

    testList.insertFront(1);
    System.out.println("\nInsert front '1'. [ 1 ]  New list is: " + testList.toString());
    System.out.println("Size (should be 1): " + testList.length());
    System.out.println("Is empty? (should be false): " + testList.isEmpty());
    System.out.println("Front (should be 1): " + testList.front().item);
    System.out.println("Back (should be 1): " + testList.back().item);
    System.out.println("1 prev (should be null): " + testList.prev(testList.head.next));
    System.out.println("1 next (should be null): " + testList.next(testList.head.next));

    testList.insertBack(3);
    System.out.println("\nInsert back '3'. [ 1  3 ]  New list is: " + testList.toString());
    System.out.println("Size (should be 2): " + testList.length());
    System.out.println("Is empty? (should be false): " + testList.isEmpty());
    System.out.println("Front (should be 1): " + testList.front().item);
    System.out.println("Back (should be 3): " + testList.back().item);
    System.out.println("3 prev (should be 1): " + testList.prev(testList.head.next.next).item);
    System.out.println("1 next (should be 3): " + testList.next(testList.head.next).item);
    System.out.println("3 next (should be null): " + testList.next(testList.head.next.next));
    System.out.println("1 prev (should be null): " + testList.prev(testList.head.next));

    testList.insertAfter(2, testList.head.next);
    System.out.println("\nInsert 2 after 1. [ 1  2  3 ]  New list is: " + testList.toString());
    System.out.println("Size (should be 3): " + testList.length());
    System.out.println("Is empty? (should be false): " + testList.isEmpty());
    System.out.println("Front (should be 1): " + testList.front().item);
    System.out.println("Back (should be 3): " + testList.back().item);
    System.out.println("3 prev (should be 2): " + testList.prev(testList.head.prev).item);
    System.out.println("1 next (should be 2): " + testList.next(testList.head.next).item);
    System.out.println("3 next (should be null): " + testList.next(testList.head.prev));
    System.out.println("1 prev (should be null): " + testList.prev(testList.head.next));

    testList.insertBefore(4, testList.back());
    System.out.println("\nInsert 4 before 3. [ 1  2  4  3 ]  New list is: " + testList.toString());
    System.out.println("Size (should be 4): " + testList.length());
    System.out.println("Is empty? (should be false): " + testList.isEmpty());
    System.out.println("Front (should be 1): " + testList.front().item);
    System.out.println("Back (should be 3): " + testList.back().item);
    System.out.println("3 prev (should be 4): " + testList.prev(testList.back()).item);
    System.out.println("1 next (should be 2): " + testList.next(testList.front()).item);
    System.out.println("4 next (should be 3): " + testList.next(testList.back().prev).item);
    System.out.println("1 prev (should be null): " + testList.prev(testList.head.next));
    System.out.println("Head prev (should be 3): " + testList.head.prev.item);

    testList.remove(testList.front());
    System.out.println("\nRemove 1. [ 2  4  3 ]  New list is: " + testList.toString());
    System.out.println("Size (should be 3): " + testList.length());
    System.out.println("Is empty? (should be false): " + testList.isEmpty());
    System.out.println("Front (should be 2): " + testList.front().item);
    System.out.println("Back (should be 3): " + testList.back().item);
    System.out.println("3 prev (should be 4): " + testList.prev(testList.back()).item);
    System.out.println("2 next (should be 4): " + testList.next(testList.front()).item);
    System.out.println("3 next (should be null): " + testList.next(testList.back()));
    System.out.println("2 prev (should be null): " + testList.prev(testList.front()));
    System.out.println("Head next (should be 2): " + testList.head.next.item);

    testList.remove(testList.back());
    System.out.println("\nRemove 3. [ 2  4 ]  New list is: " + testList.toString());
    System.out.println("Size (should be 2): " + testList.length());
    System.out.println("Is empty? (should be false): " + testList.isEmpty());
    System.out.println("Front (should be 2): " + testList.front().item);
    System.out.println("Back (should be 4): " + testList.back().item);
    System.out.println("4 prev (should be 2): " + testList.prev(testList.back()).item);
    System.out.println("2 next (should be 4): " + testList.next(testList.front()).item);
    System.out.println("4 next (should be null): " + testList.next(testList.back()));
    System.out.println("2 prev (should be null): " + testList.prev(testList.front()));
    System.out.println("Head prev (should be 4): " + testList.head.prev.item);



  }
}
