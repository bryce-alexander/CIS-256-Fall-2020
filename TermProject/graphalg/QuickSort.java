package TermProject.graphalg;

public final class QuickSort {

  /**
   *  Quicksort algorithm.
   *  @param a an array of Object items.
   **/
  public static void quicksort(Object[] a) {
    quicksort(a, 0, a.length - 1);
  }

  /**
   *  Method to swap two ints in an array.
   *  @param a an array of Objects.
   *  @param index1 the index of the first Object to be swapped.
   *  @param index2 the index of the second Object to be swapped.
   **/
  public static void swapReferences(Object[] a, int index1, int index2) {
    Object[] tmp = (Object[]) a[index1];
    a[index1] = a[index2];
    a[index2] = tmp;
  }

  /**
   *  quickSort() performs a standard quicksort on an array of objects which contain an edge (u,v) and
   *  a given weight.  This algorithm is designed to sort these objects by their weight property in
   *  order from smallest to largest.
   *  @param a       an Object array (which contains a length-3 Object arrays representing vertices and weight)
   *  @param lo0     left boundary of array partition
   *  @param hi0     right boundary of array partition
   **/
   private static void quicksort(Object a[], int lo0, int hi0) {
     int lo = lo0;
     int hi = hi0;
     int mid;

     if (hi0 > lo0) {
       swapReferences(a, lo0, (lo0 + hi0)/2);
       Object[] midObject = (Object[]) a[(lo0 + hi0) / 2];
       mid = (int)midObject[2];
       while (lo <= hi) {
         Object[] loObject = (Object[])a[lo];
         int loWeight = (int)loObject[2];
         while((lo < hi0) && (loWeight < mid)) {
           loObject = (Object[])a[lo];
           loWeight = (int)loObject[2];
           if (loWeight < mid) {
             lo++;
           }
         }

         Object[] hiObject = (Object[])a[hi];
         int hiWeight = (int)hiObject[2];
         while((hi > lo0) && (hiWeight > mid)) {
           hiObject = (Object[])a[hi];
           hiWeight = (int)hiObject[2];
           if (hiWeight > mid) {
             hi--;
           }
         }

         if (lo <= hi) {
           swapReferences(a, lo, hi);
           lo++;
           hi--;
         }
       }

       if (lo0 < hi) {
         quicksort(a, lo0, hi);
       }

       if (lo < hi0) {
         quicksort(a, lo, hi0);
       }
     }
   }


};
