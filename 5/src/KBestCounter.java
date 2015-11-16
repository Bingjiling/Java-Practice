import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.List;

public class KBestCounter<T extends Comparable<T>> {
   
    PriorityQueue<T> heap;

    public KBestCounter(int k) {
        heap = new PriorityQueue<T>(k);        
    }

    public void count(T x) {
        heap.offer(x);
    }

    public List<T> kbest() {
        List<T> output = new LinkedList<T>(); 
        while(heap.poll()!=null){
        	output.add(heap.poll());       	
        }
        return output;
    } 
}
