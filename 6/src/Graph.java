import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Graph {

  // Keep a fast index to nodes in the map
  protected Map<String, Vertex> vertices;

  /**
   * Construct an empty Graph.
   */
  public Graph() {
    vertices = new HashMap<String, Vertex>();
  }

  public void addVertex(String name) {
    Vertex v = new Vertex(name);
    addVertex(v);
  }

  public void addVertex(Vertex v) {
    if (vertices.containsKey(v.name))
      throw new IllegalArgumentException(
          "Cannot create new vertex with existing name.");
    vertices.put(v.name, v);
  }

  public Collection<Vertex> getVertices() {
    return vertices.values();
  }

  public Vertex getVertex(String s) {
    return vertices.get(s);
  }

  /**
   * Add a new edge from u to v. Create new nodes if these nodes don't exist
   * yet. This method permits adding multiple edges between the same nodes.
   * 
   * @param u
   *          the source vertex.
   * @param w
   *          the target vertex.
   */
  public void addEdge(String nameU, String nameV, Double cost) {
    if (!vertices.containsKey(nameU))
      addVertex(nameU);
    if (!vertices.containsKey(nameV))
      addVertex(nameV);
    Vertex sourceVertex = vertices.get(nameU);
    Vertex targetVertex = vertices.get(nameV);
    Edge newEdge = new Edge(sourceVertex, targetVertex, cost);
    sourceVertex.addEdge(newEdge);
  }

  /**
   * Add a new edge from u to v. Create new nodes if these nodes don't exist
   * yet. This method permits adding multiple edges between the same nodes.
   * 
   * @param u
   *          unique name of the first vertex.
   * @param w
   *          unique name of the second vertex.
   */
  public void addEdge(String nameU, String nameV) {
    addEdge(nameU, nameV, 1.0);
  }


  /****************************
   * Your code follow here.   *
   ****************************/ 

  public void addUndirectedEdge(String s, String t, double cost) {
    addEdge(s, t, cost);
    addEdge(t, s, cost);
  }

  public double computeEuclideanCost(double ux, double uy, double vx, double vy) {
    return Math.sqrt(Math.pow((ux-vx), 2)+Math.pow((uy-vy), 2));
  }

  public void computeAllEuclideanCosts() {
    for (Vertex v : getVertices()){
    	for (Edge adj : v.getEdges()){
    		double ux = adj.sourceVertex.posX;
    		double uy = adj.sourceVertex.posY;
    		double vx = adj.targetVertex.posX;
    		double vy = adj.targetVertex.posY;
    		adj.cost = computeEuclideanCost(ux,uy,vx,vy);
    	}	
    }
  }

  /** BFS */
  public void doBfs(String s) {
      for(Vertex v : getVertices()){
    	  v.visited = false;
      }
      Queue<String> q = new LinkedList<>();
      getVertex(s).cost = 0;
      getVertex(s).backpointer = null;
      q.add(s);
      while(!q.isEmpty()){
    	  Vertex v = getVertex(q.poll());
    	  v.visited = true;
    	  for(Edge e : v.getEdges()){
    		  Vertex v1 = e.targetVertex;
    		  if(v1.visited == false && (!q.contains(v1))){
    			  v1.backpointer = v;
//    			  System.out.println(v1.name + v1.backpointer.name);
    			  v1.cost = v.cost + 1;
                  q.add(v1.name);
    		  }
    	  }
      }
  }
  
  public Graph getUnweightedShortestPath(String s, String t) {
	doBfs(s);
    Graph g = new Graph();
    for(Vertex v : getVertices()){
    	Vertex w = new Vertex(v.name);
    	w.posX = v.posX;
    	w.posY = v.posY;
    	g.addVertex(w);
    }
//    System.out.println(g.getVertex("v0").getEdges());
    
    Vertex v = this.getVertex(t);
//    System.out.println(v.name);
//    System.out.println(v.backpointer.name);
    while(v.backpointer != null){
//    	System.out.println(v.backpointer);
    	g.addUndirectedEdge(v.backpointer.name, v.name, 1.0);
    	v = v.backpointer;
//    	System.out.println(v.name);
    }
//    System.out.println(g.getVertex("v0").getEdges());
    return g;
  }

  /** Dijkstra's */
  public void doDijkstra(String s) {
    return; // TODO
  }

  public Graph getWeightedShortestPath(String s, String t) {
    return null; // TODO
  }

  /** Prim's */
  public void doPrim(String s) {
    return; // TODO
  }

  public Graph getMinimumSpanningTree(String s) {
    return null; // TODO
  }

  /*************************/

  public void printAdjacencyList() {
    for (String u : vertices.keySet()) {
      StringBuilder sb = new StringBuilder();
      sb.append(u);
      sb.append(" -> [ ");
      for (Edge e : vertices.get(u).getEdges()) {
        sb.append(e.targetVertex.name);
        sb.append("(");
        sb.append(e.cost);
        sb.append(") ");
      }
      sb.append("]");
      System.out.println(sb.toString());
    }
  }

  public static void main(String[] args) {
    Graph g = new Graph();
    g.addVertex(new Vertex("v0", 0, 0));
    g.addVertex(new Vertex("v1", 0, 1));
    g.addVertex(new Vertex("v2", 1, 0));
    g.addVertex(new Vertex("v3", 1, 1));

    g.addEdge("v0", "v1");
    g.addEdge("v1", "v2");
    g.addEdge("v2", "v3");
    g.addEdge("v3", "v0");
    g.addEdge("v0", "v2");
    g.addEdge("v1", "v3");
//    System.out.println(g.getVertex("v0").getEdges());

//    g.printAdjacencyList();
    
    g = g.getUnweightedShortestPath("v0", "v3");
//    System.out.println(g.getVertex("v0").getEdges());
//    g.printAdjacencyList();
    DisplayGraph display = new DisplayGraph(g);
    display.setVisible(true);
    
  }

}
