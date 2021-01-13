package list;

public class LockDListNode extends list.DListNode {

    protected boolean locked;

    LockDListNode(Object i, LockDListNode p, LockDListNode n) {
        super(i, p, n);
        locked = false;
    }


}
