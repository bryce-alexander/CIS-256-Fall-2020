package graph;

import list.*;

/**
 * The Edge class represents a weighted edge, connecting to vertices. The
 * vertices are represent as ListNode objects.
 */

public class Edge {

    protected ListNode vertex1;
    protected ListNode vertex2;
    protected int weight;

    /**
     * Constructs a weighted edge.
     *
     * Running Time: O(1).
     */
    public Edge(ListNode origin, ListNode dest, int weight) {
        vertex1 = origin;
        vertex2 = dest;
        this.weight = weight;
    }

    /**
     * setWeight() sets the weight for a given edge.
     *
     * @param weight is the item to set into the edge.
     *
     * Running Time: O(1).
     */
    void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * getWeight() returns the wieght of a given edge.
     *
     * @return weight of the edge.
     *
     * Running Time: O(1)
     */
    int getWeight() {
        return weight;
    }

    /**
     * remove() methods removes an edge connecting to ListNode vertices.
     *
     * Running Time: O(1).
     */
    void remove() {
        try {
            if (vertex1 == vertex2) {
                vertex1.remove();
            } else {
                vertex1.remove();
                vertex2.remove();
            }
        } catch (InvalidNodeException e) {
            System.out.println("Invalid Node Exception from Edge Class' remove method.");
        }
    }

}
