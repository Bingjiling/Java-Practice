/**
 * Simulates python's range function
 */
public class Range implements Iterable<Integer> {
	// you probably need some variables here
    SimpleLinkedList<Integer> lst;
    
	public Range(int min, int max, int increment) {
		for (int i = min; i < max; i=i+increment){
			lst.add(i);
		}
	}

	public Range(int min, int max) {
		for (int i = min; i < max; i++){
			lst.add(i);
		}
	}

	public java.util.Iterator<Integer> iterator() {
		// change this
		// also understand what this actually does and the easiest way to do this
		// should be a one liner
		return lst.iterator();
	}
}
