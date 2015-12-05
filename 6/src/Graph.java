import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
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
    		  if(v1.visited == false){
    			  v1.visited = true;
    			  v1.backpointer = v;
//    			  System.out.println(v1.name + " back to " + v1.backpointer.name);
    			  v1.cost = v.cost + 1;
                  q.add(v1.name);
    		  }
//    		  Vertex v2 = e.sourceVertex;
//    		  if(v2.visited == false && (!q.contains(v2))){
//    			  v2.backpointer = v;
////    			  System.out.println(v1.name + v1.backpointer.name);
//    			  v2.cost = v.cost + 1;
//                  q.add(v2.name);
//    		  }
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
    Vertex v = this.getVertex(t);
    while(v.backpointer != null){
    	g.addEdge(v.backpointer.name, v.name);
    	v = v.backpointer;
    }
    return g;
  }

  /** Dijkstra's */
  
  private class Pair implements Comparable<Pair>{
	  
	  public double cost;
	  public String vertexName;
	  
	  public Pair(double cost, String vertexName){
		  this.cost = cost;
		  this.vertexName = vertexName;
	  }
	  
	  
	  public Vertex getV(){
		  return getVertex(vertexName);
	  }
	  
	  public int compareTo(Pair p){
		  int i = 0;
		  if(this.cost > p.cost){
			  i = 1;
		  }else if(this.cost < p.cost){
			  i = -1;
		  }
		  return i;
	  }
//	  public String getName(){
//		  return vertexName;
//	  }
  }
  public void doDijkstra(String s) {
       PriorityQueue<Pair> q = new PriorityQueue<>();
       for (Vertex v : getVertices()){
    	   v.cost = Double.POSITIVE_INFINITY;
    	   v.visited = false;
       }
       Pair p = new Pair(0,s);
       q.add(p);
       while(!q.isEmpty()){
    	   Pair near = q.poll();
    	   Vertex v = near.getV();
    	   v.cost = near.cost;
		   v.visited = true;
		   for(Edge e : v.getEdges()){
			   Vertex v1 = e.targetVertex;
			   if(v1.visited == false){
				   if(v.cost + e.cost < v1.cost){
					   v1.cost = v.cost + e.cost;
					   v1.backpointer = v;
					   q.add(new Pair(v1.cost, v1.name));
				   }
			   }
	   }
   }
  }

	  

  public Graph getWeightedShortestPath(String s, String t) {
	  doDijkstra(s);
	    Graph g = new Graph();
	    for(Vertex v : getVertices()){
	    	Vertex w = new Vertex(v.name);
	    	w.posX = v.posX;
	    	w.posY = v.posY;
	    	g.addVertex(w);
	    }
	    Vertex v = this.getVertex(t);
	    while(v.backpointer != null){
	    	g.addEdge(v.backpointer.name, v.name);
	    	v = v.backpointer;
	    }
	    return g;
  }

  /** Prim's */
  public void doPrim(String s) {
	  PriorityQueue<Pair> q = new PriorityQueue<>();
      for (Vertex v : getVertices()){
   	   v.cost = Double.POSITIVE_INFINITY;
   	   v.visited = false;
      }
      Pair p = new Pair(0,s);
      q.add(p);
      while(!q.isEmpty()){
   	   Pair near = q.poll();
   	   Vertex v = near.getV();
   	   v.cost = near.cost;
		   v.visited = true;
		   for(Edge e : v.getEdges()){
			   Vertex v1 = e.targetVertex;
			   if(v1.visited == false){
				   if(e.cost < v1.cost){
					   v1.cost = e.cost;
					   v1.backpointer = v;
					   q.add(new Pair(v1.cost, v1.name));
				   }
			   }
	      }
      }
  }

  public Graph getMinimumSpanningTree(String s) {
	    doPrim(s);
	    Graph g = new Graph();
	    for(Vertex v : getVertices()){
	    	Vertex w = new Vertex(v.name);
	    	w.posX = v.posX;
	    	w.posY = v.posY;
	    	g.addVertex(w);
	    }
	    for(Vertex v : getVertices()){
	    	if(v.backpointer != null){
	    		g.addEdge(v.name, v.backpointer.name);
	    	}
	    }
	    return g;
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

    g.addUndirectedEdge("v0", "v1",1.0);
    g.addUndirectedEdge("v1", "v2",1.0);
    g.addUndirectedEdge("v2", "v3",1.0);
    g.addUndirectedEdge("v3", "v0",1.0);
    g.addUndirectedEdge("v0", "v2",1.0);
    g.addUndirectedEdge("v1", "v3",1.0);
//    System.out.println(g.getVertex("v0").getEdges());

//    g.printAdjacencyList();
//    DisplayGraph display = new DisplayGraph(g);
//    display.setVisible(true);
    g = g.getMinimumSpanningTree("v3");
//    System.out.println("----------");
//    System.out.println(g.getVertex("v3").backpointer);
//    g.printAdjacencyList();
//    System.out.println(g.getVertex("v0").getEdges());
//    g.printAdjacencyList();
    DisplayGraph display = new DisplayGraph(g);
    display.setVisible(true);
    
  }

}
