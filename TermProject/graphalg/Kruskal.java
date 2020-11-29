/* Kruskal.java */

package TermProject.graphalg;

import TermProject.graph.*;
import TermProject.set.DisjointSets;

/**
 * The Kruskal class contains the method minSpanTree(), which implements
 * Kruskal's algorithm for computing a minimum spanning tree of a graph.
 */

public class Kruskal {

  /**
   * minSpanTree() returns a WUGraph that represents the minimum spanning tree
   * of the WUGraph g.  The original WUGraph g is NOT changed.
   *
   * @param g The weighted, undirected graph whose MST we want to compute.
   * @return A newly constructed WUGraph representing the MST of g.
   */
  public static WUGraph minSpanTree(WUGraph g) { ///////////////////////CHANGED FROM WUGRAPH, CHANGE BACK AFTER TESTING///////////////////////
    WUGraph newGraph = new WUGraph();                               // Create a new graph with same vertices as graph g
    Object[] vertices = g.getVertices();
    for (int i = 0; i < vertices.length; i++) {
      newGraph.addVertex(vertices[i]);
    }

    Object[] edges = new Object[g.edgeCount()];
    for (int i = 0; i < vertices.length; i++) {                         // For each vertex, add edges and weights if they exist
      graph.Neighbors neighbors = g.getNeighbors(vertices[i]);
      if (neighbors != null) {
        for (int j = 0; j < neighbors.neighborList.length; j++) {
          Object[] edge = new Object[3];
          edge[0] = vertices[i];
          edge[1] = neighbors.neighborList[j];
          edge[2] = neighbors.weightList[j];
          /////////////////// Code to prevent duplicate edges, negatively affects running time, consider removing ///////////////////////////////////////////////////
          int duplicateCount = 0;
          int edgeCount = 0;
          Object[] edgeObject = (Object[]) edges[0];
          while (edgeObject != null && edgeCount!=edges.length) {
            if ((edge[0] == edgeObject[0] && edge[1] == edgeObject[1]) || (edge[0] == edgeObject[1] && edge[1] == edgeObject[0])) {
              duplicateCount++;
            }
            edgeCount++;
            if (edgeCount<edges.length) {
              edgeObject = (Object[]) edges[edgeCount];
            }
          }
          if (duplicateCount == 0) {            // If there are no duplicates of this edge, insert it
            edges[edgeCount] = edge;
          }
        }
      }
    }
    QuickSort.quicksort(edges);                   // Sort the edges array

    DisjointSets edgeSet = new DisjointSets(edges.length*20);               // Initialize new disjoint set that will represent connected vertices in the graph
    for (int i=0; i<edges.length; i++) {                                    // Loop through sorted edges array and reconcile against disjoint set
      Object[] edge = (Object[])edges[i];
      Object vertex1 = edge[0];
      Object vertex2 = edge[1];
      int weight = (int)edge[2];
      int root1 = edgeSet.find((compFunction(vertex1, edges.length*20)));
      int root2 = edgeSet.find((compFunction(vertex2, edges.length*20)));
      if (root1!=root2) {
        edgeSet.union(root1, root2);
        newGraph.addEdge(vertex1, vertex2, weight);
      }
    }

    return newGraph;
  }

  /**
   * compFunction() takes an object and an array size and uses its hashcode to converts
   * it to a unique number representing the index in an array of the specified size.
   * @param object is the object to be hashed and assigned
   * @param arraySize is the size of the array in which this new hashed index must fit
   * @return integer representing the index position for "object" given the array size
   */
  protected static int compFunction(Object object, int arraySize) {
    return Math.abs(((2*object.hashCode()+14) % 999999937) % (arraySize));
  }

  public static void main(String[] args) {
    WUGraph graph = new WUGraph();

    System.out.println("CREATING NEW GRAPH...");
    System.out.println("VERTICES: " + graph.vertexCount() + " , EDGES: " + graph.edgeCount());
    System.out.println("");

    System.out.println("ADDING VERTICES: ONE, TWO, THREE, FOUR, FIVE...");
    graph.addVertex("One");
    graph.addVertex("Two");
    graph.addVertex("Three");
    graph.addVertex("Four");
    graph.addVertex("Five");
    graph.addVertex("Six");
    System.out.println("VERTICES (SHOULD BE 6): " + graph.vertexCount());
    System.out.println("EDGES (SHOULD BE 0): " + graph.edgeCount());
    System.out.println("");

    System.out.println("ADDING EDGES: ONETWO(1), ONETHREE(4), ONEFIVE(6), ONEFOUR(2), TWOFOUR(3), TWOTWO(1), TWOTHREE(2), THREEFOUR(9)...");
    graph.addEdge("One", "Two", 1);
    graph.addEdge("One", "Three", 4);
    graph.addEdge("One", "Five", 6);
    graph.addEdge("One", "Four", 2);
    graph.addEdge("Two", "Four", 3);
    graph.addEdge("Two", "Two", 1);
    graph.addEdge("Two", "Three", 2);
    graph.addEdge("Three", "Four", 9);
    System.out.println("VERTICES (SHOULD BE 6): " + graph.vertexCount());
    System.out.println("EDGES (SHOULD BE 8): " + graph.edgeCount());
    System.out.println("");

//    System.out.println("CHECKING vertices (SHOULD BE ONE-SIX):");  // Only works when minSpanTree returns vertices array
//    Object[] array = minSpanTree(graph);
//    String arrayString = "[ ";
//    for (int i = 0; i < array.length; i++) {
//      arrayString += array[i] + " ";
//    }
//    arrayString += "]";
//    System.out.println(arrayString);
//    System.out.println("");

//    System.out.println("SORTING EDGE ARRAY...");
//
//    System.out.println("EDGES ARE: ");  // Only works when minSpanTree returns edges array
//    Object[] array = minSpanTree(graph);
//
//    for (int i=0; i<array.length; i++) {
//      Object[] edge = (Object[])array[i];
//      System.out.println("EDGE " + (i+1) + ": " + edge[0] + "," + edge[1] + " WEIGHT: " + edge[2]);
//    }


//    System.out.println("Checking hashcodes:");  // Only works when minSpanTree returns vertices
//    for (int i=0; i<array.length; i++) {
//      System.out.println("VERTEX: " + array[i] + ", HASHCODE: " + array[i].hashCode() + ", INDEX MAPPING: " + Kruskal.compFunction(array[i], array.length*20));
//    }

    System.out.println("GENERATING MINSPANTREE...");
    WUGraph minTree = minSpanTree(graph);
    System.out.println("");
    System.out.println("CHECKING NEW GRAPH VERTICES:");
    System.out.println("VERTEX COUNT: " + minTree.vertexCount());
    Object[] vertices = minTree.getVertices();
    System.out.println("VERTICES ARE: ");
    String vertexString = "[ ";
    for (int i=0; i<vertices.length; i++) {
      vertexString += vertices[i] + " ";
    }
    vertexString += "]";
    System.out.println(vertexString);
    System.out.println(" ");

    System.out.println("CHECKING NEW GRAPH EDGES:");
    System.out.println("EDGE COUNT: " + minTree.edgeCount());

    System.out.println("EDGES ARE: ");  // Only works when minSpanTree returns edges array
    System.out.println("ONETWO: " + minTree.isEdge("One", "Two"));
    System.out.println("ONETHREE: " + minTree.isEdge("One", "Three"));
    System.out.println("ONEFIVE: " + minTree.isEdge("One", "Five"));
    System.out.println("ONEFOUR: " + minTree.isEdge("One", "Four"));
    System.out.println("TWOFOUR: " + minTree.isEdge("Two", "Four"));
    System.out.println("TWOTWO: " + minTree.isEdge("Two", "Two"));
    System.out.println("TWOTHREE: " + minTree.isEdge("Two", "Three"));
    System.out.println("THREEFOUR: " + minTree.isEdge("Three", "Four"));
    System.out.println("ONESIX: " + minTree.isEdge("One", "Six"));
    }


  };



