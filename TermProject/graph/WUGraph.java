/* WUGraph.java */

package TermProject.graph;
import TermProject.dict.*;
import TermProject.list.*;

/**
 * The WUGraph class represents a weighted, undirected graph.  Self-edges are
 * permitted.
 */

public class WUGraph {

  private int vertexCount;
  private int edgeCount;
  private HashTableChained vertices;
  private HashTableChained edges;

  /**
   * WUGraph() constructs a graph having no vertices or edges.
   *
   * Running time:  O(1).
   */
  public WUGraph() {
    vertexCount = 0;
    edgeCount = 0;
    vertices = new HashTableChained(0);
    edges = new HashTableChained(0);
    DList vertexList = new DList();
    DList edgeList = new DList();
  };

  /**
   * vertexCount() returns the number of vertices in the graph.
   *
   * Running time:  O(1).
   */
  public int vertexCount() {
    return vertexCount;
  };

  /**
   * edgeCount() returns the total number of edges in the graph.
   *
   * Running time:  O(1).
   */
  public int edgeCount() {
    return edgeCount;
  };

  /**
   * getVertices() returns an array containing all the objects that serve
   * as vertices of the graph.  The array's length is exactly equal to the
   * number of vertices.  If the graph has no vertices, the array has length
   * zero.
   *
   * (NOTE:  Do not return any internal data structure you use to represent
   * vertices!  Return only the same objects that were provided by the
   * calling application in calls to addVertex().)
   *
   * Running time:  O(|V|).
   */
  public Object[] getVertices() {
    return vertices.getKeys();
  };

  /**
   * addVertex() adds a vertex (with no incident edges) to the graph.
   * The vertex's "name" is the object provided as the parameter "vertex".
   * If this object is already a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(1).
   */
  public void addVertex(Object vertex) {  // Creates new DList to store edges as value for hashtable entry
    DList adjacencyList = new DList();
    vertices.insert(vertex, adjacencyList);
    vertexCount++;
  };

  /**
   * removeVertex() removes a vertex from the graph.  All edges incident on the
   * deleted vertex are removed as well.  If the parameter "vertex" does not
   * represent a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */

  // Adjacency list will automatically be deleted when vertex is removed from vertices hashtable, but steps must be taken to remove from other adjacency lists first
  // 1. To remove edges associated with a vertex, iterate through adjacency list and for each VertexPair ****** AND CALL REMOVEEDGE() ON EACH ONE
  // 2. Remove vertex from vertices
  public void removeVertex(Object vertex){
    if (vertices.find(vertex)!=null) {
      DList list = (DList)vertices.find(vertex).value();
      DListNode node = list.front();
      while (node!=null) {
        VertexPair edge = (VertexPair)node.item;
        Object object1 = edge.object1;
        Object object2 = edge.object2;
        removeEdge(object1, object2);
        node = list.next(node);
      }
      vertexCount--;
    }
  };


  /**
   * isVertex() returns true if the parameter "vertex" represents a vertex of
   * the graph.
   *
   * Running time:  O(1).
   */
  public boolean isVertex(Object vertex) {
    if (vertices.find(vertex) != null) {
      return true;
    }
    return false;
  };

  /**
   * degree() returns the degree of a vertex.  Self-edges add only one to the
   * degree of a vertex.  If the parameter "vertex" doesn't represent a vertex
   * of the graph, zero is returned.
   *
   * Running time:  O(1).
   */
  public int degree(Object vertex) {
    if (vertices.find(vertex) != null) {
      DList adjacencyList = (DList)vertices.find(vertex).value();           // The "value" for a vertex entry in vertices is a DList
      return adjacencyList.length();                                        // Return the length of that DList
    }
    return 0;
  };

  /**
   * getNeighbors() returns a new Neighbors object referencing two arrays.  The
   * Neighbors.neighborList array contains each object that is connected to the
   * input object by an edge.  The Neighbors.weightList array contains the
   * weights of the corresponding edges.  The length of both arrays is equal to
   * the number of edges incident on the input vertex.  If the vertex has
   * degree zero, or if the parameter "vertex" does not represent a vertex of
   * the graph, null is returned (instead of a Neighbors object).
   *
   * The returned Neighbors object, and the two arrays, are both newly created.
   * No previously existing Neighbors object or array is changed.
   *
   * (NOTE:  In the neighborList array, do not return any internal data
   * structure you use to represent vertices!  Return only the same objects
   * that were provided by the calling application in calls to addVertex().)
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public graph.Neighbors getNeighbors(Object vertex) {
    if (isVertex(vertex)==false || degree(vertex)==0) {
      return null;
    }
    graph.Neighbors neighbors = new graph.Neighbors();                          // Initialize new neighbors object
    neighbors.neighborList = new Object[degree(vertex)];                        // Initialize neighborList to # of edges
    neighbors.weightList = new int[degree(vertex)];                             // Initialize weightList to # of edges
    DList adjacencyList = (DList)vertices.find(vertex).value();                 // Assign adjacency list for vertex input
    DListNode node = adjacencyList.front();                                     // Assign node to first node for iteration
    for (int i=0; i<degree(vertex); i++) {                                      // Iterate through the nodes to assign values to arrays (TEST FOR NULLPOINTER EXCEPTION)
      VertexPair edge = (VertexPair)node.item;                                  // Create edge variable represented by the item stored in the node
      neighbors.neighborList[i] = edge.otherObject(vertex);                     // Assign this position in the neighborList array to the neighbor
      neighbors.weightList[i] = (int)edges.find(edge).value();                  // Assign this position in the weightList array to the weight (value associated with edges key)
      node = adjacencyList.next(node);                                          // Move to the next node, continue iteration
    }
    return neighbors;
  };

  /**
   * addEdge() adds an edge (u, v) to the graph.  If either of the parameters
   * u and v does not represent a vertex of the graph, the graph is unchanged.
   * The edge is assigned a weight of "weight".  If the graph already contains
   * edge (u, v), the weight is updated to reflect the new value.  Self-edges
   * (where u.equals(v)) are allowed.
   *
   * Running time:  O(1).
   */
  public void addEdge(Object u, Object v, int weight) {                      //////////////////////////////////////  CREATED REFERENCES TO BOTH NODES IN EDGE IN VERTEXPAIR; ASSIGN THOSE REFERENCES WHILE ADDING EDGES TO ADJACENCYLIST
    if (vertices.find(u)!=null && vertices.find(v)!=null) {
      TermProject.graph.VertexPair edge = new VertexPair(u, v);
      if (edges.find(edge) != null) {
        edges.find(edge).changeValue(weight);                    // If edge exists, don't perform insertions and assignments, simply change weight
      } else {                                                   // If edge does not exist, perform operations to add.
        edge.object1List = (DList) vertices.find(u).value();     // Fetch the value (Edge / VertexPair) which is stored with the Vertex key
        edge.object2List = (DList) vertices.find(v).value();
        edge.object1Node = edge.object1List.insertFront(edge);  // Adding the edge to both object adjacency lists and returning the node to be stored as the reference in VertexPair
        edge.object2Node = edge.object2List.insertFront(edge);  // BE CAREFUL WHEN ADDING SELF EDGES AS REMOVE CAN EASILY BREAK
        edges.insert(edge, weight);
        edgeCount++;
      }
    }
  };

  /**
   * removeEdge() removes an edge (u, v) from the graph.  If either of the
   * parameters u and v does not represent a vertex of the graph, the graph
   * is unchanged.  If (u, v) is not an edge of the graph, the graph is
   * unchanged.
   *
   * Running time:  O(1).
   */

  // If each vertex exists and the edge exists:
  // 1. Remove each node referenced from its respective adjacency lists (IF SELF-EDGE, REMOVE ONCE)
  // 2. Remove the edge from edges
  // 3. Reduce edge count
  public void removeEdge(Object u, Object v) {
    if (vertices.find(u)!=null && vertices.find(v)!=null && edges.find(new VertexPair(u,v))!=null) {    // Check to make sure that both vertices and edge exists
      VertexPair edge = (VertexPair)edges.find(new VertexPair(u,v)).key();                              // Assign "edge" to the VertexPair which is the key in the edges hashtable
      edge.object1List.remove(edge.object1Node);                                                        // Remove the first node from the first list
      edge.object2List.remove(edge.object2Node);                                                        // Remove the second node from the second list
      edges.remove(edge);                                                                               // Remove the edge from edges hashtable
      edgeCount--;                                                                                      // Reduce edgeCount
    }
  };

  /**
   * isEdge() returns true if (u, v) is an edge of the graph.  Returns false
   * if (u, v) is not an edge (including the case where either of the
   * parameters u and v does not represent a vertex of the graph).
   *
   * Running time:  O(1).
   */
  public boolean isEdge(Object u, Object v) {
    TermProject.graph.VertexPair edge = new VertexPair(u, v);    // DO I NEED TO CREATE A CASE FOR VERTICES EXISTING OR IS THIS IMPLIED IN THE EXISTENCE OF EDGE?
    if (edges.find(edge)!=null) {
      return true;
    }
    return false;
  };

  /**
   * weight() returns the weight of (u, v).  Returns zero if (u, v) is not
   * an edge (including the case where either of the parameters u and v does
   * not represent a vertex of the graph).
   *
   * (NOTE:  A well-behaved application should try to avoid calling this
   * method for an edge that is not in the graph, and should certainly not
   * treat the result as if it actually represents an edge with weight zero.
   * However, some sort of default response is necessary for missing edges,
   * so we return zero.  An exception would be more appropriate, but also more
   * annoying.)
   *
   * Running time:  O(1).
   */
  public int weight(Object u, Object v) {
    TermProject.graph.VertexPair edge = new VertexPair(u, v);
    Entry findEdge = edges.find(edge);
    if (findEdge!=null) {
      return (int)findEdge.value();
    }
    System.out.println("This edge does not exist in this graph.  Returning 0...");
    return 0;
  };

}
