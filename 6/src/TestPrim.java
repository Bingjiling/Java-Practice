public class TestPrim {

  public static void main(String[] args) {
	  String vertex = args[0];
      String edge = args[1];
      Graph g = MapReader.readGraph(vertex, edge); 
      
      g.computeAllEuclideanCosts();
      g = g.getMinimumSpanningTree(args[2]);
      DisplayGraph display = new DisplayGraph(g);
      display.setVisible(true);
  }

}
