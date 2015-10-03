import java.util.LinkedList;

// This class should countain your implementation of the Buffer interface.
public class FastBuffer implements Buffer{
	
	LinkedList<Character> left = new LinkedList<>();
	LinkedList<Character> right = new LinkedList<>();
	int cursorPosition;
	int size;
	
	/** Get the current character count of the buffer  */
	public int size(){
		return size;
	}
	
	/** load the buffer from an char array and position the cursor. after load
    size() == initial.length */
    public void load(char initial[], int cursorPosition){
    	this.cursorPosition = cursorPosition;
    	for (int i=0 ; i<cursorPosition; i++)
    		left.addFirst(initial[i]);
    	for (int i=initial.length-1; i>cursorPosition-1; i--)
    		right.addFirst(initial[i]);
    	size = initial.length;
    }
    
    /** convert the current buffer contents to an array
    returnArray.length == size()
   */
   public char[] toArray(){
	   char[] outPut;
	   outPut = new char[this.size];
	   for(int i=cursorPosition-1; i>=0; i--){
		   outPut[i] = left.removeFirst();
	   }
	   for(int i=cursorPosition; i<size; i++){
		   outPut[i] = right.removeFirst();
	   }
	   return outPut; 
   }
   /** get the cursor position, in characters from start */
   public int getCursor(){
	   return cursorPosition;
   }

   /** set the cursor position, in characters from start */
   public void setCursor(int j){
	   this.cursorPosition = j;
	   if (j>=left.size()){
		   int move = j-left.size();
		   for (int i=0; i<move; i++){
			   left.addFirst(right.removeFirst());
		   }		
	   }else{
		   int move = left.size()-j;
		   for (int i=0; i<move; i++){
			   right.addFirst(left.removeFirst());
		   }	
	   }
   }

   /** move cursor one to the right
       "abc|def" => "abcd|ef"
   */
   public void moveRight(){
	   cursorPosition++;
	   right.addFirst(left.removeFirst());
   }

   /** move cursor one to the left
       "abc|def" => "ab|cdef"
    */
   public void moveLeft(){
	   cursorPosition--;
	   left.addFirst(right.removeFirst());
   }

   /** insert a new char to the left of the cursor
    if the buffer is "abc|def", insertLeft('X')
    would change the buffer to 'abcX|def'
    */
   public void insertLeft(char c){
	   size++;
	   cursorPosition++;
	   left.addFirst(c);
   }

   /** delete and return the character to the right of the cursor
    given "abc|def", deleteRight() => "abc|ef"
    */
   public char deleteRight(){
	   size--;
	   return right.removeFirst();
   }

   /** delete and return the character to the left of the cursor
    given "abc|def", deleteLeft() => "ab|def"
    */
   public char deleteLeft(){
	   size--;
	   cursorPosition--;
	   return left.removeFirst();
   }
   
//   public static void main(String[] args){
//	   char[] test;
//	   test = new char[5];
//	   test[0] = 'a';
//	   test[1] = 'b';
//	   test[2] = 'c';
//	   test[3] = 'd';
//	   test[4] = 'e';
//	   FastBuffer fastBuffer = new FastBuffer();
//	   fastBuffer.load(test, 3);
//	   System.out.println(fastBuffer.left);
////	   System.out.println(fastBuffer.toArray());
//	   fastBuffer.setCursor(4);
//	   System.out.println(fastBuffer.left);
//	   System.out.println(fastBuffer.toArray());
////	   System.out.println(fastBuffer.toArray());
//   }
}