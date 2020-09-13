package Homework;


public class HW4_test extends list.DList {

    public static void main(String[] args) {
        list.DList testList = new list.DList();
        testList.insertFront(1);
        testList.insertBack(2);
        testList.insertBack(3);
        System.out.println("New list is: " + testList.toString());


        System.out.println("\nAttempting violation of invariants through method calls:");
//        testList.remove(testList.head);
//        System.out.println("Removed head.  Call for head node (should now be null: " + testList.head.toString());
        testList.insertBack(null);
        System.out.println("Inserted null node at the end.  New list: " + testList.toString());
        System.out.println("Prev to first node is: " + testList.prev(testList.front()));
        System.out.println("Next to third node is: " + testList.next(testList.prev(testList.back())).item);





    }
}
