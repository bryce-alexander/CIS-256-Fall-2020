/* ListSorts.java */

import list.*;
import list.LinkedQueue;
import sun.awt.image.ImageWatched;

public class ListSorts {

  private final static int SORTSIZE = 10;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
    // Replace the following line with your solution.
    // Create new LinkedQueue to store new values
    LinkedQueue newQueue = new LinkedQueue();

    try {
      // For each item in the LinkedQueue q, add a new queue to newQueue
      int qSize = q.size();
      for (int i = 1; i <= qSize; i++) {
        LinkedQueue nodeQueue = new LinkedQueue();
        nodeQueue.enqueue(q.dequeue());
        newQueue.enqueue(nodeQueue);;
      }
    } catch (list.QueueEmptyException e) {
      System.out.println("MAKEQUEUEOFQUEUES EXCEPTION");
    }
    return newQueue;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
    // Replace the following line with your solution.

    // Create new LinkedQueue to act as the final returned queue.
    list.LinkedQueue newQueue = new LinkedQueue();

    try {
      // While neither queue is empty, compare the first element of each and add the smallest
      // to the end of the new queue.
      while (!q1.isEmpty() && !q2.isEmpty()) {
        Object item1 = q1.front();
        Object item2 = q2.front();
        if (((Comparable) item1).compareTo(((Comparable) item2)) < 0) {
          newQueue.enqueue(q1.dequeue());
        } else {
          newQueue.enqueue(q2.dequeue());
        }
      }
      // If either queue has remaining values, concatenate those to the end of the current queue.
      if (!q1.isEmpty()) {
        newQueue.append(q1);
      } else if (!q2.isEmpty()) {
        newQueue.append(q2);
      }
    } catch (list.QueueEmptyException e) {
      System.out.println("MERGESORTEDQUEUES EXCEPTION");
    }
    return newQueue;
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
    // Your solution here.
    try {
      while (qIn.size() > 0) {
        Object frontNode = qIn.dequeue();
        int compareValue = ((Comparable) frontNode).compareTo(pivot);
        if (compareValue < 0) {
          qSmall.enqueue(frontNode);
        } else if (compareValue > 0) {
          qLarge.enqueue(frontNode);
        } else {
          qEquals.enqueue(frontNode);
        }
      }
    } catch (list.QueueEmptyException e) {
      System.out.println("PARTITION EXCEPTION");
    }
  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
    // Your solution here.
    try {
      if (q.size()>1) {
        LinkedQueue newQueue = makeQueueOfQueues(q);
        while (newQueue.size() > 1) {
          LinkedQueue item1 = (LinkedQueue) newQueue.dequeue();
          LinkedQueue item2 = (LinkedQueue) newQueue.dequeue();
          newQueue.enqueue(mergeSortedQueues(item1, item2));

        }
        q.append((LinkedQueue) newQueue.front());
      }
    } catch (list.QueueEmptyException e) {
      System.out.println("MERGESORT EXCEPTION");
    }
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
    // Your solution here.

    if (q.size()>1) {
      // Create queues and pivot value for partition()
      LinkedQueue qSmall = new LinkedQueue();
      LinkedQueue qEquals = new LinkedQueue();
      LinkedQueue qLarge = new LinkedQueue();
      int randomIndex = (int) Math.ceil(Math.random() * q.size());
      Comparable pivot = (Comparable) q.nth(randomIndex);

      // Run partition to update the queues
      partition(q, pivot, qSmall, qEquals, qLarge);
      quickSort(qSmall);
      quickSort(qLarge);

      // Assemble all queues in order
      q.append(qSmall);
      q.append(qEquals);
      q.append(qLarge);
    }
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/
  public static void main(String [] args) {

    LinkedQueue q = makeRandom(10);

    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString());

    q = makeRandom(10);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    /* Remove these comments for Part III. */
    Timer stopWatch = new Timer();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

  }

}
