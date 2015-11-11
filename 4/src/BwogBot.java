import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BwogBot {
  
  /*
   *  Separate chaining map is better. The average running time for put is O(1) for
   *  separate chaining map. While for AvlMap, the average running time for put
   *  is O(logN).
   */
  public SeparateChainingMap<String, Integer> map;
  public List<String> s;
  
  public BwogBot() {
	  map = new SeparateChainingMap<String, Integer>();
  }

  public void readFile(String fileName) throws IOException {
	  Scanner sc = null;
	  sc = new Scanner(new File(fileName));
	  try{
		  while(sc.hasNextLine()){
			  Scanner sc2 = new Scanner(sc.nextLine());
			  try{
				  while(sc2.hasNext()){
						String s = sc2.next();
						Integer value = map.get(s);
						if(value == null){
							map.put(s,1);
						}
						else
							map.put(s, value+1);
					}
			  }finally{
				  sc2.close();
			  }			  
		  }
	  }finally{
		  sc.close();
	  }
	  
  }

  public int getCount(String word) {
    return map.get(word);
  }

  public List<String> getNMostPopularWords(int n) {
    LinkedList<Pair<String, Integer>> l = new LinkedList<>();
    for(int i = 0; i<n; i++){
    	l.add(null);
    }
    for (LinkedList<Pair<String, Integer>> cList : map.mList){
    	for (Pair<String, Integer> pair : cList){
    		outer:
    		for (int i = 0; i < n; i++){
    			if (l.get(i) == null){
    				l.add(i, pair);
    			}else if (pair.value > l.get(i).value){
    				l.add(i, pair);
    				break outer;
    			}
    		}
    	if (l.size() > n)
    		l.removeLast();
    	}
    }
    List<String> output = new LinkedList<>();
    for (Pair<String, Integer> p : l){
    	output.add(p.key);
     }
    return output;
  }

  public Map<String, Integer> getMap() {
    return map;
  }

  public static void main(String[] args) throws IOException {
    BwogBot bot = new BwogBot();
    bot.readFile("comments.txt");
    System.out.println(bot.getCount("hamdel")); // because linan's hungry now
//    LinkedList<Integer> l = new LinkedList<>();
//    System.out.println(l.);
    System.out.println(bot.getNMostPopularWords(100));
  }
}
