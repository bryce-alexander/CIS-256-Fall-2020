package list;

public class LockDList extends list.DList {

    @Override
    protected list.DListNode newNode(Object item, list.DListNode prev, list.DListNode next) {
        return new list.LockDListNode(item, (list.LockDListNode)prev, (list.LockDListNode)next);
    }

    public LockDList() {
        super();
    }

    @Override
    public void remove(list.DListNode node) {
        // Your solution here.
        if (node==null || ((list.LockDListNode) node).locked==true) { return; }
        super.remove(node);
    }

    public void lockNode(list.DListNode node) {
        ((list.LockDListNode) node).locked = true;
    }

    public static void main(String[] args) {

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
