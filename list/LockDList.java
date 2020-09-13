/* LockDList.java */
package list;

/**
 * A LockDList is a mutable doubly-linked list ADT, a subclass of DList. Its
 * implementation is circularly-linked and employs a sentinel (dummy) node at
 * the head of the list. It has overrides the remove method to prevent a
 * lockNode from being deleted.
 *
 */

public class LockDList extends DList {

    private DListNode lockNode;

    /**
     * lockNode() assigns given node as a lockNode.
     *
     * @param node is the node being assigned as a lockNode.
     */
    public void lockNode(DListNode node) {
        lockNode = node;
    }

    @Override
    /**
     * remove() return node removed from this DList. If node is null, do nothing. If
     * node is also lockNode does nothing. Performance: runs in O(1) time.
     *
     * @return node the node removed from DList if it in not empty, or null if
     *         empty.
     * @param node the removed from DList.
     *
     */
    public void remove(DListNode node) {

        if (node == lockNode) {
            return;
        }
        super.remove(node);
    }

    public static void main(String[] args) {
        DList list = new LockDList();
        list.displayLinkedList(list);

        list.insertFront("cow");
        System.out.println("Inserting item to front of Linked List.");
        list.displayLinkedList(list);

        list.insertFront("The");
        System.out.println("Inserting item to front of Linked List.");
        list.displayLinkedList(list);

        list.insertBack("over");
        System.out.println("Inserting item to back of Linked List.");
        list.displayLinkedList(list);

        list.insertBack("moon");
        System.out.println("Inserting item to back of Linked List.");
        list.displayLinkedList(list);

        list.insertAfter("jumped", list.head.next.next);
        System.out.println("Inserting item to after node 2.");
        list.displayLinkedList(list);

        list.insertBefore("the", list.head.prev);
        System.out.println("Inserting item to before node 4.");
        list.displayLinkedList(list);

        System.out.print("The front item of the Linked List is: ");
        System.out.println("\"" + list.front().item + "\".");
        System.out.print("The back item of the Linked List is: ");
        System.out.println("\"" + list.back().item + "\".");
        System.out.print("The item after the second item is: ");
        System.out.println("\"" + list.next(list.head.next.next).item + "\".");
        System.out.print("The item before the second item is: ");
        System.out.println("\"" + list.prev(list.head.next.next).item + "\".\n");

        list.remove(list.head.prev.prev);
        System.out.println("Removing item located at node 5.");
        list.displayLinkedList(list);

        list.remove(list.head.next.next.next);
        System.out.println("Removing item located at node 3.");
        list.displayLinkedList(list);

        list.remove(list.head.next.next);
        System.out.println("Removing item located at node 2.");
        list.displayLinkedList(list);

        list.remove(list.head.prev);
        System.out.println("Removing item located at node 3.");
        list.displayLinkedList(list);

        list.remove(list.head.next);
        System.out.println("Removing item located at node 1.");
        list.displayLinkedList(list);

        System.out.println("Locking last remaining node...\"over\". \n");
        ((LockDList) list).lockNode(list.head.prev);

        list.remove(list.head.prev);
        System.out.println("Removing item located at node 1.");
        list.displayLinkedList(list);

    }
}
