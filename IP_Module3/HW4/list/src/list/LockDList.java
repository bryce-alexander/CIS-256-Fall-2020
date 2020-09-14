package list;

public class LockDList extends DList {



    @Override
    protected DListNode newNode(Object item, DListNode prev, DListNode next) {
        return new LockDListNode(item, prev, next);
    }
    LockDList(){
        super();
    }

    public void lockNode (DListNode node) {
            ((LockDListNode) node).locked =true;

        }

        @Override
        public void remove(DListNode node) {
            if (((LockDListNode) node).locked) {
            }
            else super.remove(node);
        }

}

