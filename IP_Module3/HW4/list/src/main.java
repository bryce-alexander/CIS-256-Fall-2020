public class main {
    public static void main (String [] args) {
    DList l1 = new DList(2);
    System.out.println(l1.length());
    System.out.println(l1.isEmpty());
    l1.insertFront(1);
    System.out.println(l1.length());
    l1.insertBack(3);
    System.out.println(l1.length());
    System.out.println(l1.toString());
}
}
