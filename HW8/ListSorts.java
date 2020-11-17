/* ListSorts.java */

import list.*;

public class ListSorts {

    private final static int SORTSIZE = 1000;

    /**
     * makeQueueOfQueues() makes a queue of queues, each containing one item of q.
     * Upon completion of this method, q is empty.
     *
     * @param q is a LinkedQueue of objects.
     * @return a LinkedQueue containing LinkedQueue objects, each of which contains
     *         one object from q.
     **/
    public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {

        LinkedQueue newQueue = new LinkedQueue();

        while (!q.isEmpty()) {
            try {
                Queue n = new LinkedQueue();
                n.enqueue(q.dequeue());
                newQueue.enqueue(n);

            } catch (QueueEmptyException e) {
                System.out.println("makeQueueOfQueue Exception.");
                e.printStackTrace();
            }
        }
        return newQueue;
    }

    /**
     * mergeSortedQueues() merges two sorted queues into a third. On completion of
     * this method, q1 and q2 are empty, and their items have been merged into the
     * returned queue.
     *
     * @param q1 is LinkedQueue of Comparable objects, sorted from smallest to
     *           largest.
     * @param q2 is LinkedQueue of Comparable objects, sorted from smallest to
     *           largest.
     * @return a LinkedQueue containing all the Comparable objects from q1 and q2
     *         (and nothing else), sorted from smallest to largest.
     **/
    public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {

        LinkedQueue mergedQueue = new LinkedQueue();
        try {
            while (!q1.isEmpty() && !q2.isEmpty()) {
                // Takes next element from q1.
                if (((Comparable) q1.front()).compareTo(q2.front()) < 0) {
                    mergedQueue.enqueue(q1.dequeue());
                }

                // Takes next element from q2.
                else {
                    mergedQueue.enqueue(q2.dequeue());
                }
            }

            // Moves any elements that remain from q1.
            while (!q1.isEmpty()) {
                mergedQueue.enqueue(q1.dequeue());
            }

            // Moves any elements that remain is q2.
            while (!q2.isEmpty()) {
                mergedQueue.enqueue(q2.dequeue());

            }

        } catch (QueueEmptyException e) {
            System.out.println("mergeSortQueues Exception.");
            e.printStackTrace();
        }

        return mergedQueue;
    }

    /**
     * partition() partitions qIn using the pivot item. On completion of this
     * method, qIn is empty, and its items have been moved to qSmall, qEquals, and
     * qLarge, according to their relationship to the pivot.
     *
     * @param qIn     is a LinkedQueue of Comparable objects.
     * @param pivot   is a Comparable item used for partitioning.
     * @param qSmall  is a LinkedQueue, in which all items less than pivot will be
     *                enqueued.
     * @param qEquals is a LinkedQueue, in which all items equal to the pivot will
     *                be enqueued.
     * @param qLarge  is a LinkedQueue, in which all items greater than pivot will
     *                be enqueued.
     **/
    public static void partition(LinkedQueue qIn, Comparable pivot, LinkedQueue qSmall, LinkedQueue qEquals,
                                 LinkedQueue qLarge) {

        try {
            while(qIn.size() > 0) {

                Object frontItem = qIn.dequeue();
                if( ((Comparable)frontItem).compareTo(pivot) < 0 ) {
                    qSmall.enqueue(frontItem);
                }
                else if(((Comparable)frontItem).compareTo(pivot) > 0 ) {
                    qLarge.enqueue(frontItem);
                }
                else { qEquals.enqueue(frontItem); }

            }
        }catch(QueueEmptyException e) {
            System.out.println("partition method excpetion.");
        }
    }

    /**
     * mergeSort() sorts q from smallest to largest using mergesort.
     *
     * @param q is a LinkedQueue of Comparable objects.
     **/
    public static void mergeSort(LinkedQueue q) {

        LinkedQueue tempQueue = new LinkedQueue();

        if (q.size() > 1) { tempQueue = makeQueueOfQueues(q); }

        try {
            while (tempQueue.size() > 1) {
                LinkedQueue firstItem = (LinkedQueue) tempQueue.dequeue();
                LinkedQueue secondItem = (LinkedQueue) tempQueue.dequeue();
                tempQueue.enqueue(mergeSortedQueues(firstItem, secondItem));
            }

            // moves tempQueue to q.
            q.append((LinkedQueue) tempQueue.front());
        } catch (QueueEmptyException e) {
            System.out.println("mergeSort Exception.");
            e.printStackTrace();
        }
    }

    /**
     * quickSort() sorts q from smallest to largest using quicksort.
     *
     * @param q is a LinkedQueue of Comparable objects.
     **/
    public static void quickSort(LinkedQueue q) {

        if(q.size() > 1) {
            // Creates three LinkedQueue objects to hold partitions.
            LinkedQueue smallerValuesQueue = new LinkedQueue();
            LinkedQueue largerValuesQueue = new LinkedQueue();
            LinkedQueue equalValuesQueue = new LinkedQueue();

            int randomNum = (int) Math.ceil(Math.random() * q.size());
            Comparable pivot = (Comparable) q.nth(randomNum);


            partition(q, pivot, smallerValuesQueue, equalValuesQueue, largerValuesQueue);

            // Sort the smaller and larger partitions using quickSort.
            quickSort(smallerValuesQueue);
            quickSort(largerValuesQueue);

            // Joins the three partitions together into q.
            q.append(smallerValuesQueue);
            q.append(equalValuesQueue);
            q.append(largerValuesQueue);
        }
        else
            q = q;
    }

    /**
     * makeRandom() builds a LinkedQueue of the indicated size containing Integer
     * items. The items are randomly chosen between 0 and size - 1.
     *
     * @param size is the size of the resulting LinkedQueue.
     **/
    public static LinkedQueue makeRandom(int size) {
        LinkedQueue q = new LinkedQueue();
        for (int i = 0; i < size; i++) {
            q.enqueue(new Integer((int) (size * Math.random())));
        }
        return q;
    }

    /**
     * main() performs some tests on mergesort and quicksort. Feel free to add more
     * tests of your own to make sure your algorithms works on boundary cases. Your
     * test code will not be graded.
     **/
    public static void main(String[] args) {

        LinkedQueue q = makeRandom(10);
        /*
         * System.out.println("q Queue(Before):\n" + q); LinkedQueue s = new
         * LinkedQueue(); s.enqueue(makeQueueOfQueues(q));
         * System.out.println("\nq Queue(After):\n" + q);
         * System.out.println("\ns Queue:\n" + s);
         */

        System.out.println(q.toString());
        mergeSort(q);
        System.out.println(q.toString());

        q = makeRandom(10);
        System.out.println(q.toString());
        quickSort(q);
        System.out.println(q.toString());


       // Remove these comments for Part III.
        Timer stopWatch = new Timer();
        q = makeRandom(SORTSIZE); stopWatch.start(); mergeSort(q); stopWatch.stop();
        System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                stopWatch.elapsed() + " msec.");

        stopWatch.reset();
        q = makeRandom(SORTSIZE); stopWatch.start(); quickSort(q);
        stopWatch.stop(); System.out.println("Quicksort time, " + SORTSIZE +
                " Integers:  " + stopWatch.elapsed() + " msec.");
    }

}
