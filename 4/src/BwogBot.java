import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class BwogBot {
  
  /*
   *  SeparateChainingMap is faster. For example, if we start at table size N and 
   *  insert N items till the size reach 2N and we have to rehash. If we use AvlTree
   *  the total time would be O(NlogN). While for SeparateChainingMap is N for inserting
   *  and 2N for rehash, so the total time for separate Chaining is 3N. O(N) < O(Nlog(N))
   *  So SeparateChainingMap is better.
   */
  public SeparateChainingMap<String, Integer> map;
  
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
    return 0;
  }

  public List<String> getNMostPopularWords(int n) {
    return null;
  }

  public Map<String, Integer> getMap() {
    return null;
  }

  public static void main(String[] args) throws IOException {
    BwogBot bot = new BwogBot();
    bot.readFile("comments.txt");
    System.out.println(bot.getCount("hamdel")); // because linan's hungry now
    System.out.println(bot.getNMostPopularWords(100));
  }
}
