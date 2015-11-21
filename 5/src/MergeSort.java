import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MergeSort {

        /**
         * Internal method that merges two sorted halves of a subarray (from Weiss Data Structures and Algorithm Analysis in Java)
         * @param a an array of Comparable items.
         * @param tmpArray an array to place the merged result.
         * @param leftPos the left-most index of the subarray.
         * @param rightPos the index of the start of the second half.
         * @param rightEnd the right-most index of the subarray.
         */
        private static void merge(Integer[] a, Integer[] tmpArray, int leftPos, int rightPos, int rightEnd) {
            int leftEnd = rightPos - 1;
            int tmpPos = leftPos;
            int numElements = rightEnd - leftPos + 1;

            // Main loop
            while(leftPos <= leftEnd && rightPos <= rightEnd) {
                if( a[leftPos] <= a[rightPos ]) { 
                    tmpArray[tmpPos++] = a[leftPos++];
                } else {
                    tmpArray[tmpPos++] = a[rightPos++];
                }   
            }  

            while( leftPos <= leftEnd ) {   // Copy rest of first half
//            	System.out.println("leftend"+leftEnd);
                tmpArray[tmpPos++] = a[leftPos++];
            }

            while( rightPos <= rightEnd ) { // Copy rest of right half
                tmpArray[tmpPos++] = a[rightPos++];
            }

            // Copy tmpArray back
            for( int i = 0; i < numElements; i++, rightEnd-- ) {
                a[rightEnd] = tmpArray[rightEnd];
            }
        }

        /**
         * Merge Sort algorithm.
         * This is the Merge Sort algorithm from from Weiss, Data Structures and Algorithm Analysis in Java, 
         * as presented in class. 
         * @param a an array of Comparable items.
         */
        public static void mergeSort(Integer[] a ) {
            Integer[] tmpArray = new Integer[a.length];
            mergeSort(a, tmpArray, 0, a.length - 1 );
        }

        /**
         * Internal method that makes recursive calls. 
         * This is part of the MergeSort algorithm from from Weiss, Data Structures and Algorithm Analysis in Java, 
         * as presented in class. 
         * @param a an array of Comparable items.
         * @param tmpArray an array to place the merged result.
         * @param left the left-most index of the subarray.
         * @param right the right-most index of the subarray.
         */
        private static void mergeSort(Integer[] a, Integer[] tmpArray, int left, int right) {
            if(left < right) {
                int center = (left + right) / 2;
                mergeSort(a, tmpArray, left, center);
                mergeSort( a, tmpArray, center + 1, right);
                merge(a, tmpArray, left, center + 1, right);
            }
        }


       /** 
         * Problem 5: Iterative Bottom-up Merge Sort
         */
        public static void mergeSortB(Integer[] inputArray) {
        	Integer[] temp = new Integer[inputArray.length];
//        	System.out.println("length" + inputArray.length);
        	for(int blockSize = 1; blockSize < inputArray.length; blockSize*=2){
        		for(int i = 0; i < inputArray.length; i += blockSize*2){
        			if(i+blockSize*2 < inputArray.length){
        				merge(inputArray, temp, i, i+blockSize, i+blockSize*2-1);
        			}else if(inputArray.length - i > blockSize){
        				merge(inputArray, temp, i, i+blockSize, inputArray.length-1);			
        			}   				
        		}
        	}
            return;
        }


        /** 
         * Problem 6: Merge Sort for Lists, Without Side Effects
         */
        public static List<Integer> sortList(List<Integer> inputList) {
        	List<Integer> outputList = new LinkedList<Integer>();
            return sortList(inputList, outputList);
        }
        
        private static List<Integer> sortList(List<Integer> inputList, List<Integer> outputList){
            if(inputList.size()>1){
            	int center = inputList.size()/2;
                List<Integer> leftList = sortList( inputList.subList(0, center), outputList);
                List<Integer> rightList = sortList(inputList.subList(center,inputList.size()), outputList);
                outputList = mergeLists(leftList, rightList);
            }else if(inputList.size()==1){
            	return inputList;
            }
            return outputList;
        }
        

        /**
         * New merge method that merges two lists and returns a new list.
         * Use this method to implement sortList.
         */
        public static List<Integer> mergeLists(List<Integer> left, List<Integer> right) { 
            List<Integer> l = new LinkedList<Integer>(); 
            Iterator<Integer> iterLeft = left.iterator();
            Iterator<Integer> iterRight = right.iterator();
            Integer a = null;
            Integer b = null;
            if(iterLeft.hasNext()){
            	a = iterLeft.next();
            }
            if(iterRight.hasNext()){
            	b = iterRight.next();
            }
            
            while(a!=null && b!=null){
//            	System.out.println("hey");
            	if(a > b){
            		l.add(b);
            		if(iterRight.hasNext()){
            			b = iterRight.next();
            		}else{
            			b = null;
            		}
            	}else{
            		l.add(a);
            		if(iterLeft.hasNext()){
            			a = iterLeft.next();
            		}else{
            			a = null;
            		}
            	}
            }
            
            while(a!=null){
            	l.add(a);
        		if(iterLeft.hasNext()){
        			a = iterLeft.next();
        		}else{
        			a = null;
        		}
            }
            
            while(b!=null){
            	l.add(b);
        		if(iterLeft.hasNext()){
        			b = iterLeft.next();
        		}else{
        			b = null;
        		}
            }
            return l;
        }
        
 
        public static void main(String[] args) {
            // Weiss sort
//            Integer[] a = {1,4,9,131,0,2,7,19,245,18};
//            MergeSort.mergeSortB(a);
//            System.out.println(Arrays.toString(a)); // Should be [0, 1, 2, 4, 7, 9, 18, 19, 131, 245]
            List<Integer> l1 = new LinkedList<Integer>();
            List<Integer> l2 = new LinkedList<Integer>();
            l1.add(4);
            l1.add(5);
            l1.add(3);
            l2.add(3);
            l2.add(8);
            System.out.println(sortList(l2));
            System.out.println(sortList(l1));
            Integer[] a = {1,4,9,131,0,2,7,19,245,18};
            MergeSort.mergeSort(a);
            System.out.println(Arrays.toString(a)); // Should be [0, 1, 2, 4, 7, 9, 18, 19, 131, 245]
        }
}




