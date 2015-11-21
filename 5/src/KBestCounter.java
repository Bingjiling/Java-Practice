import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.List;

public class KBestCounter<T extends Comparable<T>> {
   
    PriorityQueue<T> heap;
    int k;
    int i = 0;

    public KBestCounter(int k) {
        heap = new PriorityQueue<T>(k);
        this.k = k;
    }

    public void count(T x) {
        if(i<k){
        	heap.add(x);
        	i++;
        }else{
        	if(x.compareTo(heap.peek())>0){
        		heap.poll();
        		heap.add(x);
        	}
        }
    }

    public List<T> kbest() {
        List<T> output = new LinkedList<T>();
        while(heap.peek()!=null){
        	output.add(0, heap.poll());
        }
        for(T x : output)
        	heap.add(x);
        return output;
    } 
    
    public static void main(String [ ] args){
    	int k = 10;
    	KBestCounter <Integer> counter = new KBestCounter<>( k );
    	for ( int i = 0; i < 100; i ++) 
    		  counter.count(i);
    	System.out.println(counter.heap);
    	List<Integer> kbest = counter.kbest();
    	System.out.println(kbest);   	
    }
}


