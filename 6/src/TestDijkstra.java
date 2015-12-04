public class TestDijkstra {

  public static void main(String[] args) {
	  String vertex = args[0];
      String edge = args[1];
      Graph g = MapReader.readGraph(vertex, edge); 
      
      g.computeAllEuclideanCosts();
      g = g.getWeightedShortestPath(args[2], args[3]);
      DisplayGraph display = new DisplayGraph(g);
      display.setVisible(true);
  }

}
