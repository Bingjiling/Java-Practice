/**
 * Simulates python's range function
 */
public class Range implements Iterable<Integer> {
	// you probably need some variables here
    int min;
    int max;
    int increment;
    SimpleLinkedList<Integer> lst = new SimpleLinkedList<>();
    /**
     * build the range linkedlist
     * @param min first index produced
     * @param max iteration stops before reaching stop
     * @param increment distance between each integer
     */
	public Range(int min, int max, int increment) {
		this.min = min;
		this.max = max;
		this.increment = increment;
		for (int i = min; i < max; i = i+increment){
			//System.out.println(i);
			this.lst.add(i);
		}
		this.lst.iterator();
	}

	public Range(int min, int max) {
		this.min = min;
		this.max = max;
		for (int i = min; i < max; i++){
			this.lst.add(i);
		}
		this.lst.iterator();
	}

	public java.util.Iterator<Integer> iterator() {
		// change this
		// also understand what this actually does and the easiest way to do this
		// should be a one liner
		return this.lst.iterator();
	}
	
	public static void main(String[] args) {
		for(Integer j : new Range(1,8)) {
			  System.out.print(j);
			}
	}
	
}
