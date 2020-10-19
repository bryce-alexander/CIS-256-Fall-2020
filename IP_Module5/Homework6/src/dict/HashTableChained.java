/* HashTableChained.java */

package dict;

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

public class HashTableChained implements Dictionary {

  private LinkedList[] hashTable;
  private int N; // number of buckets
  private int n=0; // number of Entries



  /**
   * Construct a new empty hash table intended to hold roughly sizeEstimate
   * entries.  (The precise number of buckets is up to you, but we recommend
   * you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
//    while (!isPrime(sizeEstimate)){
//      sizeEstimate+=1;
//    }
//      N = sizeEstimate;
//      hashTable = new LinkedList[N];
    BigInteger estimate = new BigInteger(String.valueOf(sizeEstimate));
    if (estimate.isProbablePrime(1)) {
      hashTable = new LinkedList[sizeEstimate];
      N=sizeEstimate;
    } else {
      hashTable = new LinkedList[estimate.nextProbablePrime().intValue()];
      N=estimate.nextProbablePrime().intValue();
    }

    }



  /**
   * Construct a new empty hash table with a default size.  Say, a prime in
   * the neighborhood of 100.
   **/

  public HashTableChained() {
    N = 103;
    hashTable = new LinkedList[N];
  }

  /**
   * Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   * to a value in the range 0...(size of hash table) - 1.
   * <p>
   * This function should have package protection (so we can test it), and
   * should be used by insert, find, and remove.
   **/

  protected int compFunction(int code) {
    return Math.abs(((code+65)% 121439)%N) ;
  }

  /**
   * Returns the number of entries stored in the dictionary.  Entries with
   * the same key (or even the same key and value) each still count as
   * a separate entry.
   *
   * @return number of entries in the dictionary.
   **/

  public int size() {
    return n;
  }

  /**
   * Tests if the dictionary is empty.
   *
   * @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    if (n>0){
      return false;
    }
    else return true;
  }

  /**
   * Create a new Entry object referencing the input key and associated value,
   * and insert the entry into the dictionary.  Return a reference to the new
   * entry.  Multiple entries with the same key (or even the same key and
   * value) can coexist in the dictionary.
   * <p>
   * This method should run in O(1) time if the number of collisions is small.
   *
   * @param key   the key by which the entry can be retrieved.
   * @param value an arbitrary object.
   * @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    int i = compFunction(key.hashCode());
    Entry add = new Entry();
    add.key = key;
    add.value = value;
    if (find(key)==null) {
     hashTable[i] = new LinkedList();
    }
    hashTable[i].add(add);
    n++;
    return add;
  }

  /**
   * Search for an entry with the specified key.  If such an entry is found,
   * return it; otherwise return null.  If several entries have the specified
   * key, choose one arbitrarily and return it.
   * <p>
   * This method should run in O(1) time if the number of collisions is small.
   *
   * @param key the search key.
   * @return an entry containing the key and an associated value, or null if
   * no entry contains the specified key.
   **/

  public Entry find(Object key) {
    int i = compFunction(key.hashCode());
    if (hashTable[i] != null) {
      return (Entry)hashTable[i].getFirst();
    }
    else {
      return null;
    }
  }


  /**
   * Remove an entry with the specified key.  If such an entry is found,
   * remove it from the table and return it; otherwise return null.
   * If several entries have the specified key, choose one arbitrarily, then
   * remove and return it.
   * <p>
   * This method should run in O(1) time if the number of collisions is small.
   *
   * @param key the search key.
   * @return an entry containing the key and an associated value, or null if
   * no entry contains the specified key.
   */

  public Entry remove(Object key) {
   int i = compFunction(key.hashCode());
    if (find(key)!=null) {
      n--;
      return (Entry) hashTable[i].removeFirst();
    }
    else {
      return null;
    }
  }

  /**
   * Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    hashTable = new LinkedList[N];
    n=0;
  }

  public boolean isPrime(int toCheck) {
    if (toCheck <= 1) {
      return false;
    }
    for (int i = 2; i < Math.sqrt(toCheck); i++) {
      if (toCheck % i == 0) {
        return false;
      }
    }
    return true;
  }
  public int collisionCount(){
   int count =0;
   for (int i =0; i<N; i++){
     if (hashTable[i]!=null){
     count += hashTable[i].size()-1;
   }else continue;
   }
   return count;
  }
}
