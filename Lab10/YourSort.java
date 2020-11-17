/* YourSort.java */

public class YourSort {

    public static void sort(int[] A) {
        // Place your Part III code here.

        // insertSort faster for 20 items or less.
        if(A.length < 20) { Sort.insertionSort(A); }

        // quickSort faster for greater than 20 items.
        else { Sort.quicksort(A); }
    }
}
