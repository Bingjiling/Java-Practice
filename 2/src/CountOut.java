import java.util.*;

public class CountOut {
/**
 * runs a simulation for a given n and a given k
 * @param n
 * @param k
 * @return a list containing player numbers in
 * the order in which they are counted out.
 */
  public static List<Integer> play(int n, int k) {

    Queue<Integer> remainingPlayers = new LinkedList<>();
    LinkedList<Integer> outPut = new LinkedList<>();  
    for(int i =0; i<n; i++ ){
    	remainingPlayers.add(i);
    }
    while (remainingPlayers.size()>0){
    	for (int i=0; i<k-1; i++){
    		remainingPlayers.add(remainingPlayers.poll());
    	}
    	outPut.add(remainingPlayers.poll());
    }
    return outPut;
}  /**
   * returns the last remaining player.
   * It's a O(n*logn) algorithm 
   * @param n
   * @param k
   * @return
   */
  public static Integer findWinner(int n, int k) {
    return play(n,k).get(n-1);
 }
/**
 * It's a O(n) algotirhm
 * @param n
 * @param k
 * @return
 */
  public static Integer findWinnerRec(int n, int k) {
    if (n==1){
    	return 0;
    }else{
    	return (k + findWinnerRec(n-1,k))%n;
    }
  }
  public static void main(String[] args){
//	  System.out.println(play(9,4));
//	  System.out.println(play(8,4));
//	  System.out.println(play(7,4));
//	  System.out.println(play(6,4));
//	  System.out.println(play(5,4));
//	  System.out.println(findWinner(8,4));
//	  System.out.println(findWinner(9,4));
//	  System.out.println(findWinnerRec(9,4));
//	  System.out.println(findWinner(8,4));
//	  System.out.println(findWinnerRec(8,4));
//	  System.out.println(findWinner(9,2));
//	  System.out.println(findWinnerRec(9,2));
  }
}
