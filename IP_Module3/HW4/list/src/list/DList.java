package list;/* list.DList.java */

/**
 *  A list.DList is a mutable doubly-linked list ADT.  Its implementation is
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

  protected DListNode head;
  protected int size;



  /* list.DList invariants:
   *  1)  head != null.
   *  2)  For any list.DListNode x in a list.DList, x.next != null.
   *  3)  For any list.DListNode x in a list.DList, x.prev != null.
   *  4)  For any list.DListNode x in a list.DList, if x.next == y, then y.prev == x.
   *  5)  For any list.DListNode x in a list.DList, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNodes, NOT COUNTING the sentinel,
   *      that can be accessed from the sentinel (head) by a sequence of
   *      "next" references.
   */

  /**
   *  newNode() calls the list.DListNode constructor.  Use this class to allocate
   *  new DListNodes rather than calling the list.DListNode constructor directly.
   *  That way, only this method needs to be overridden if a subclass of list.DList
   *  wants to use a different kind of node.
   *  @param item the item to store in the node.
   *  @param prev the node previous to this node.
   *  @param next the node following this node.
   */
  protected DListNode newNode(Object item, DListNode prev, DListNode next) {
    return new DListNode(item, prev, next);

  }

  /**
   *  list.DList() constructor for an empty list.DList.
   */
  public DList() {
    head = newNode (null, null, null);
    head.next = head;
    head.prev = head;
    size =0;
  }

  public DList(Object i) {
    head = newNode(null,  null, null);
    head.next= head;
    head.prev= head;
    head.next = newNode(i, head.next, head.prev);
    head.prev = head.next;
    size++;
  }




  /**
   *  isEmpty() returns true if this list.DList is empty, false otherwise.
   *  @return true if this list.DList is empty, false otherwise.
   *  Performance:  runs in O(1) time.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /** 
   *  length() returns the length of this list.DList.
   *  @return the length of this list.DList.
   *  Performance:  runs in O(1) time.
   */
  public int length() {

    return size;
  }

  /**
   *  insertFront() inserts an item at the front of this list.DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertFront(Object item) {
   head.next = newNode(item, head, head.next);
   head.next.next.prev = head.next;
   size ++;
  }

  /**
   *  insertBack() inserts an item at the back of this list.DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertBack(Object item) {
   head.prev = newNode(item, head.prev, head);
   head.prev.prev.next = head.prev;
   size++;
  }

  /**
   *  front() returns the node at the front of this list.DList.  If the list.DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the front of this list.DList.
   *  Performance:  runs in O(1) time.
   */
  public DListNode front() {

    if (isEmpty())return null;
    else;
     return head.next;
  }

  /**
   *  back() returns the node at the back of this list.DList.  If the list.DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the back of this list.DList.
   *  Performance:  runs in O(1) time.
   */
  public DListNode back() {
    DListNode node;
    if (!isEmpty()) return head.prev;
    else return null;
  }

  /**
   *  next() returns the node following "node" in this list.DList.  If "node" is
   *  null, or "node" is the last node in this list.DList, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @param node the node whose successor is sought.
   *  @return the node following "node".
   *  Performance:  runs in O(1) time.
   */
  public DListNode next(DListNode node) {
    if (node == null ||node.next == head ) {
      return null;
    } else
      return node.next;
  }

  /**
   *  prev() returns the node prior to "node" in this list.DList.  If "node" is
   *  null, or "node" is the first node in this list.DList, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @param node the node whose predecessor is sought.
   *  @return the node prior to "node".
   *  Performance:  runs in O(1) time.
   */
  public DListNode prev(DListNode node) {
    if (node.prev == head)
      return null;
    else return node.prev;
  }

  /**
   *  insertAfter() inserts an item in this list.DList immediately following "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item after.
   *  Performance:  runs in O(1) time.
   */
  public void insertAfter(Object item, DListNode node) {
    if(node.next!=head){
    node.next = newNode(item, node, node.next);
    node.next.next.prev = node.next;
    }
    else {
      head.prev = newNode(item, node, head);}
    size++;
  }

  /**
   *  insertBefore() inserts an item in this list.DList immediately before "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item before.
   *  Performance:  runs in O(1) time.
   */
  public void insertBefore(Object item, DListNode node) {
    if(node.prev !=head){
    node.prev = newNode(item, node.prev, node);
    node.prev.prev.next = node.prev;
    }
    else if (node.prev == head){
      head.next = newNode(item, head, node.next);}
    size++;
  }

  /**
   *  remove() removes "node" from this list.DList.  If "node" is null, do nothing.
   *  Performance:  runs in O(1) time.
   */
  public void remove(DListNode node) {
    node.prev.next= node.next;
    node.next.prev = node.prev;
    size--;
  }



  /**
   *  toString() returns a String representation of this list.DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this list.DList.
   *  Performance:  runs in O(n) time, where n is the length of the list.
   */
  public String toString() {
    String result = "[  ";
    DListNode current = head.next;
    while (current != head) {
      result = result + current.item + "  ";
      current = current.next;
    }
    return result + "]";
  }
  public static void main (String [] args) {
//    DList l1 = new DList(2);
//    System.out.println(l1.length());
//    System.out.println(l1.isEmpty());
//    l1.insertFront(1);
//    System.out.println(l1.length());
//    l1.insertBack(3);
//    System.out.println(l1.length());
//    System.out.println(l1.toString());
//    System.out.println(l1.toString());
//    System.out.println(l1.length());
//    DList testList = new DList();
//
//    System.out.println("Initialize new DList. [ ]  New list is: " + testList.toString());
//    System.out.println("Size (should be 0): " + testList.length());
//    System.out.println("Is empty? (should be true): " + testList.isEmpty());
//    System.out.println("Sentinel prev (should be null): " + testList.prev(testList.head));
//    System.out.println("Sentinel next (should be null): " + testList.next(testList.head));
//    System.out.println("Sentinel points to self in both directions? " + (testList.head.next == testList.head && testList.head.prev == testList.head));
//
//    testList.insertFront(1);
//    System.out.println("\nInsert front '1'. [ 1 ]  New list is: " + testList.toString());
//    System.out.println("Size (should be 1): " + testList.length());
//    System.out.println("Is empty? (should be false): " + testList.isEmpty());
//    System.out.println("Front (should be 1): " + testList.front().item);
//    System.out.println("Back (should be 1): " + testList.back().item);
//    System.out.println("1 prev (should be null): " + testList.prev(testList.head.next));
//    System.out.println("1 next (should be null): " + testList.next(testList.head.next));
//
//    testList.insertBack(3);
//    System.out.println("\nInsert back '3'. [ 1  3 ]  New list is: " + testList.toString());
//    System.out.println("Size (should be 2): " + testList.length());
//    System.out.println("Is empty? (should be false): " + testList.isEmpty());
//    System.out.println("Front (should be 1): " + testList.front().item);
//    System.out.println("Back (should be 3): " + testList.back().item);
//    System.out.println("3 prev (should be 1): " + testList.prev(testList.head.next.next).item);
//    System.out.println("1 next (should be 3): " + testList.next(testList.head.next).item);
//    System.out.println("3 next (should be null): " + testList.next(testList.head.next.next));
//    System.out.println("1 prev (should be null): " + testList.prev(testList.head.next));
//
//    testList.insertAfter(2, testList.head.next);
//    System.out.println("\nInsert 2 after 1. [ 1  2  3 ]  New list is: " + testList.toString());
//    System.out.println("Size (should be 3): " + testList.length());
//    System.out.println("Is empty? (should be false): " + testList.isEmpty());
//    System.out.println("Front (should be 1): " + testList.front().item);
//    System.out.println("Back (should be 3): " + testList.back().item);
//    System.out.println("3 prev (should be 2): " + testList.prev(testList.head.prev).item);
//    System.out.println("1 next (should be 2): " + testList.next(testList.head.next).item);
//    System.out.println("3 next (should be null): " + testList.next(testList.head.prev));
//    System.out.println("1 prev (should be null): " + testList.prev(testList.head.next));
//
//    testList.insertBefore(4, testList.back());
//    System.out.println("\nInsert 4 before 3. [ 1  2  4  3 ]  New list is: " + testList.toString());
//    System.out.println("Size (should be 4): " + testList.length());
//    System.out.println("Is empty? (should be false): " + testList.isEmpty());
//    System.out.println("Front (should be 1): " + testList.front().item);
//    System.out.println("Back (should be 3): " + testList.back().item);
//    System.out.println("3 prev (should be 4): " + testList.prev(testList.back()).item);
//    System.out.println("1 next (should be 2): " + testList.next(testList.front()).item);
//    System.out.println("4 next (should be 3): " + testList.next(testList.back().prev).item);
//    System.out.println("1 prev (should be null): " + testList.prev(testList.head.next));
//    System.out.println("Head prev (should be 3): " + testList.head.prev.item);
//
//    testList.remove(testList.front());
//    System.out.println("\nRemove 1. [ 2  4  3 ]  New list is: " + testList.toString());
//    System.out.println("Size (should be 3): " + testList.length());
//    System.out.println("Is empty? (should be false): " + testList.isEmpty());
//    System.out.println("Front (should be 2): " + testList.front().item);
//    System.out.println("Back (should be 3): " + testList.back().item);
//    System.out.println("3 prev (should be 4): " + testList.prev(testList.back()).item);
//    System.out.println("2 next (should be 4): " + testList.next(testList.front()).item);
//    System.out.println("3 next (should be null): " + testList.next(testList.back()));
//    System.out.println("2 prev (should be null): " + testList.prev(testList.front()));
//    System.out.println("Head next (should be 2): " + testList.head.next.item);
//
//    testList.remove(testList.back());
//    System.out.println("\nRemove 3. [ 2  4 ]  New list is: " + testList.toString());
//    System.out.println("Size (should be 2): " + testList.length());
//    System.out.println("Is empty? (should be false): " + testList.isEmpty());
//    System.out.println("Front (should be 2): " + testList.front().item);
//    System.out.println("Back (should be 4): " + testList.back().item);
//    System.out.println("4 prev (should be 2): " + testList.prev(testList.back()).item);
//    System.out.println("2 next (should be 4): " + testList.next(testList.front()).item);
//    System.out.println("4 next (should be null): " + testList.next(testList.back()));
//    System.out.println("2 prev (should be null): " + testList.prev(testList.front()));
//    System.out.println("Head prev (should be 4): " + testList.head.prev.item);


    LockDList test = new LockDList();

    System.out.println("Initializing new LockDList: " + test.toString());
    test.insertFront(2);
    test.insertBack(3);
    test.insertFront(1);

    System.out.println("List is now [  1  2  3  ]:   " + test.toString());
    test.lockNode(test.front());
    System.out.println("First node is locked, attempting removal.");
    test.remove(test.front());
    System.out.println("List is still [  1  2  3  ]:   " + test.toString());
    test.remove(test.back());
    System.out.println("Removing 3.");
    System.out.println("List is now [  1  2  ]:   " + test.toString());
    test.insertBack(3);
    test.lockNode(test.back());
    test.remove(test.back());
    System.out.println("3 re-added and locked.  Attempting removal.");
    System.out.println("List is now [  1  2  3  ]:   " + test.toString());



    System.out.println("Type Of 1:   " + test.front().getClass());
    System.out.println("Type Of 2:   " + test.front().next.getClass());
    System.out.println("Type Of 3:   " + test.back().getClass());
    System.out.println("Type Of head:   " + test.head.getClass());

  }

  }
