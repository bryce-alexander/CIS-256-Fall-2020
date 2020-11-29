/* Sorts.java */

package sort;

public class Sorts {

  /**
   *  Place any final static fields you would like to have here.
   **/


  /**
   *  countingSort() sorts an array of int keys according to the
   *  values of _one_ of the base-16 digits of each key.  "whichDigit"
   *  indicates which digit is the sort key.  A zero means sort on the least
   *  significant (ones) digit; a one means sort on the second least
   *  significant (sixteens) digit; and so on, up to a seven, which means
   *  sort on the most significant digit.
   *  @param key is an array of ints.  Assume no key is negative.
   *  @param whichDigit is a number in 0...7 specifying which base-16 digit
   *    is the sort key.
   *  @return an array of type int, having the same length as "keys"
   *    and containing the same keys sorted according to the chosen digit.
   *
   *    Note:  Return a _newly_ created array.  DO NOT CHANGE THE ARRAY keys.
   **/
  public static int[] countingSort(int[] keys, int whichDigit) {
    // Replace the following line with your solution.
    int[] counts = new int[16];                                        // Create array to hold counts of each number
    for (int i=0; i<keys.length; i++) {
      int digit = (int)(keys[i]*Math.pow(16,(-whichDigit))) & 15;
      counts[digit]++;
    }
    int total = 0;                                                      // Change counts array to new sorted index positions
    for (int j = 0; j < counts.length; j++) {
      int c = counts[j];
      counts[j] = total;
      total = total + c;
    }

    int[] sortedArray = new int[keys.length];                           // Use target index values to create new sorted array
    for (int i = 0; i < keys.length; i++) {
      int digit = (int)(keys[i]*Math.pow(16,(-whichDigit))) & 15;
      sortedArray[counts[digit]] = keys[i];
      counts[digit]++;
    }

    return sortedArray;
  }

  /**
   *  radixSort() sorts an array of int keys (using all 32 bits
   *  of each key to determine the ordering).
   *  @param key is an array of ints.  Assume no key is negative.
   *  @return an array of type int, having the same length as "keys"
   *    and containing the same keys in sorted order.
   *
   *    Note:  Return a _newly_ created array.  DO NOT CHANGE THE ARRAY keys.
   **/
  public static int[] radixSort(int[] keys) {
    // Replace the following line with your solution.
    int[] sortedArray = countingSort(keys,0);          // Initialize array sorted at least significant digit
    for (int i=1; i<=7; i++) {                                  // Continue sorting on each digit
      sortedArray = countingSort(sortedArray, i);
    }
    return sortedArray;
  }

  /**
   *  yell() prints an array of int keys.  Each key is printed in hexadecimal
   *  (base 16).
   *  @param key is an array of ints.
   **/
  public static void yell(int[] keys) {
    System.out.print("keys are [ ");
    for (int i = 0; i < keys.length; i++) {
      System.out.print(Integer.toString(keys[i], 16) + " ");
    }
    System.out.println("]");
  }

  /**
   *  main() creates and sorts a sample array.
   *  We recommend you add more tests of your own.
   *  Your test code will not be graded.
   **/
  public static void main(String[] args) {
    int[] keys = { Integer.parseInt("60013879", 16),
                   Integer.parseInt("11111119", 16),
                   Integer.parseInt("2c735010", 16),
                   Integer.parseInt("2c732010", 16),
                   Integer.parseInt("7fffffff", 16),
                   Integer.parseInt("4001387c", 16),
                   Integer.parseInt("10111119", 16),
                   Integer.parseInt("529a7385", 16),
                   Integer.parseInt("1e635010", 16),
                   Integer.parseInt("28905879", 16),
                   Integer.parseInt("00011119", 16),
                   Integer.parseInt("00000000", 16),
                   Integer.parseInt("7c725010", 16),
                   Integer.parseInt("1e630010", 16),
                   Integer.parseInt("111111e5", 16),
                   Integer.parseInt("61feed0c", 16),
                   Integer.parseInt("3bba7387", 16),
                   Integer.parseInt("52953fdb", 16),
                   Integer.parseInt("40013879", 16) };

    ////////
    System.out.print("non-string keys are [ ");
    int digit = 0;
    for (int i = 0; i < keys.length; i++) {
      System.out.print((((int)(keys[i]*Math.pow(16,(-digit))) & 15)) + " ");
    }
    System.out.println("]");

    System.out.println("Testing countingSort() on least significant digit...");
    int[] counts = countingSort(keys,0);

//    System.out.print("counts array is [ ");
//    for (int i = 0; i < counts.length; i++) {
//      System.out.print(i + ":(" + counts[i] + "), ");
//    }
//    System.out.println("]");

    System.out.print("sorted array is [ ");
    for (int i = 0; i < counts.length; i++) {
      System.out.print(Integer.toString(counts[i], 16) + " ");
    }
    System.out.println("]");

    yell(keys);
    keys = radixSort(keys);
    yell(keys);
  }

}
