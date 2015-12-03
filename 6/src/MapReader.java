import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MapReader {

  public static Graph readGraph(String vertexfile, String edgefile) {
	  Graph g = new Graph();
	  try(BufferedReader br = new BufferedReader(new FileReader(vertexfile))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       String arr[] = line.split(",");
		       g.addVertex(new Vertex(arr[0],Integer.parseInt(arr[1]),Integer.parseInt(arr[2])));
		    }
		}catch(IOException e){
			e.printStackTrace();
		}
	  try(BufferedReader br = new BufferedReader(new FileReader(edgefile))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       String arr[] = line.split(",", 2);
		       g.addUndirectedEdge(arr[0], arr[1], 1.0);
		    }
		}catch(IOException e){
			e.printStackTrace();
		}
	  return g;
  }

  public static void main(String[] args) {
      String vertex = args[0];
      String edge = args[1];
      Graph g = readGraph(vertex, edge); 

      DisplayGraph display = new DisplayGraph(g);
      display.setVisible(true);
  }
}
