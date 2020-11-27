package graph;

import list.*;

/**
 * The Vertex class represents a vertex backed by a Doubly Linked-List. The
 * vertex contains a key so it can be used is a dictionary, hashTable, or
 * hashNap.
 */
public class Vertex {

    protected Object vertexKey;
    protected DList edgeList;

    /**
     * Creates a Vertex with an empty doubly linked-list.
     *
     * @param vertex is item used to create a vertex object.
     *
     * Running Time: O(1n(.
     */
    public Vertex(Object vertex) {
        vertexKey = vertex;
        edgeList = new DList();
    }

    /**
     * degree() returns the number of degrees (connections) to a given vertex.
     *
     * @return edgeList the number of the vertex's degree.
     *
     * Running Time O(1).
     */
    int degree() {
        return edgeList.length();
    }

    /**
     * key() returns the vertex's key value.
     *
     * @return vertexKey the value of vertex's key value.
     *
     * Running Time: O(1).
     */
    Object key() {
        return vertexKey;
    }

}
