/* HashTableChained.java */

package TermProject.dict;

import java.math.BigInteger;
import java.util.LinkedList;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained extends Object implements TermProject.dict.Dictionary {

  /**
   *  Place any data fields here.
   **/
  private int size;
  private LinkedList[] buckets;




  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
    size = 0;
    BigInteger estimate = new BigInteger(String.valueOf(sizeEstimate));
    if (estimate.isProbablePrime(1)) {
      buckets = new LinkedList[sizeEstimate];
    } else {
      buckets = new LinkedList[estimate.nextProbablePrime().intValue()];
    }
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
    size = 0;
    buckets = new LinkedList[101];
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
    return Math.abs(((2*code+14) % 999999937) % this.buckets.length);
  }

  /**
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return this.size;
  }

  /**
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    if (this.size==0) {return true;}
    else {return false;}
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
    Entry newEntry = new Entry();
    newEntry.key = key;
    newEntry.value = value;
    if (find(key)==null) {
      this.buckets[compFunction(key.hashCode())] = new LinkedList();
    }
    buckets[compFunction(key.hashCode())].add((Entry)newEntry);
    this.size++;
    checkResize();
    return newEntry;
  }

  /**
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
    try {
      if (buckets[compFunction(key.hashCode())] != null) {
        return (Entry) buckets[compFunction(key.hashCode())].getFirst();
      }
    } catch (Exception e){ }
    return null;
  }

  /**
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
    if (find(key)==null) { return null; }
    else {
      size--;
      checkResize();
      return (Entry)buckets[compFunction(key.hashCode())].remove();
    }
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
    this.buckets = new LinkedList[buckets.length];
    this.size = 0;
  }

  /**
   * Function to determine number of collisions
   */

  public int countCollisions() {
    int numCollisions = 0;
    for (int i=0; i<buckets.length; i++) {
      if (buckets[i]!=null) {
        numCollisions += buckets[i].size()-1;
      } else { continue; }
    }
    return numCollisions;
  }

  /**
   * checkResize() is a helper function which will calculate the current load factor and
   * determine whether or not it is appropriate to resize the hash table.  For the purpose
   * of this exercise, we will be operating with an ideal load factor of 0.75.  If it is
   * determined that the load factor is too high or too low, the hash table will be resized and rehashed.
   */

  protected void checkResize() {
    // Compute the current load factor and determine whether or not it needs to be resized.
    // If so, resize to new load factor of approx. 0.75
    double loadFactor = (double) size / (double) buckets.length;
    if (loadFactor >= 0.75 || loadFactor <= 0.8) {
      int newSize = (int) (size * (1 + (1.0 / 3.0))) + 1;
      BigInteger estimate = new BigInteger(String.valueOf(newSize));
      if (!estimate.isProbablePrime(1)) {
        estimate = new BigInteger(String.valueOf(estimate.nextProbablePrime().intValue()));
      }
      LinkedList[] oldBuckets = buckets;
      buckets = new LinkedList[estimate.intValue()];
//      System.out.println("SIZE: " + size);
//      System.out.println("OLDBUCKETS LENGTH: " + oldBuckets.length + " NEWBUCKETS LENGTH: " + buckets.length);


      // Iterate through current buckets and insert each into restructured hashtable
      for (int i = 0; i < oldBuckets.length; i++) {
        /*        System.out.println("i=" + i);*/
        if (oldBuckets[i] != null) {
          for (int j = 0; j < oldBuckets[i].size(); j++) {
//            System.out.println("i=" + i + " j=" + j + " oldbuckets[i] size: " + oldBuckets[i].size());
            Entry entry = (Entry) oldBuckets[i].get(j);
//            insert(entry.key, entry.value);
//            System.out.println("HASHCODE INDEX: " + compFunction(entry.key.hashCode()));
//            if (find(entry.key)==null) {
//              this.buckets[compFunction(entry.key.hashCode())] = new LinkedList();
//            }
            buckets[compFunction(entry.key.hashCode())] = new LinkedList();
            buckets[compFunction(entry.key.hashCode())].add(entry);
            /*            System.out.println("COMPLETE");*/
          }
        }
      }
    }
  }

  /**
   * WRITE DESCRIPTION FOR GETKEYS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
   * @return
   */
  public Object[] getKeys() {
    Object[] keys = new Object[size];
    int indexCounter = 0;
    for (int i=0; i<buckets.length-1; i++) {
      if (buckets[i]!=null && buckets[i].size()!=0) {
        Entry entry = (Entry)buckets[i].get(0);
        keys[indexCounter] = entry.key;
        indexCounter++;
      }
    }
    return keys;
  }

  /**
   * WRITE DESCRIPTION FOR CHANGEVALUE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
   */

  /**
     * Test suite (REMOVE BEFORE SUBMISSION)
     */

    public static void main(String[] args) {
      HashTableChained test = new HashTableChained(100);

      for (int i=0; i<80; i++){
        test.insert(i, i);
      }
      System.out.println("OLD LOAD FACTOR: " + (double)test.size()/(double)test.buckets.length);
      test.checkResize();
      System.out.println("NEW LOAD FACTOR: " + (double)test.size()/(double)test.buckets.length);
      test.remove(1);
      System.out.println("NEW LOAD FACTOR (2): " + (double)test.size()/(double)test.buckets.length);

      System.out.println("CREATING NEW HASHTABLE WITH SIZE 0...");
      HashTableChained test2 = new HashTableChained(0);
      System.out.println("NEW HASHTABLE SIZE: " + test2.buckets.length);

      System.out.println("CHECKING GETKEYS FOR HASHTABLE WITH " + test.size + " ENTRIES: ");
      Object[] array = test.getKeys();
      String arrayString = "[";
      for (int i = 0; i<array.length; i++) {
        arrayString+=" " + i;
      }
      arrayString+= " ]";
      System.out.println(arrayString);

    }


// CONSIDER CHANGING RESIZE TO MAKE LOAD FACTOR HIGHER SO THERE ARE LESS ITERATIONS FOR GETVERTICES()




  }

