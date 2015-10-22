import java.util.ArrayList;
import java.util.List;

public class Trie {

  private TrieNode root;
  public static final char NULL = '0';

  public Trie() {
    root = new TrieNode(NULL, false);
  }

  // implement your methods here
  // feel free (and you probably should) add helper private methods
  // problem 4a
  /*
   * Add new character to the first available place instead of the corresponding 
   * place. It's a little bit slow in add, but faster in printing. And if the node
   * can be made to suit for the size needed. it'll save memory space.
   * 
   */
  public void addWord(String word) {
	 char[] charArray = word.toCharArray();
	 for(char c:charArray){
		 if(c < 'a' || c > 'z'){
			 throw new IllegalArgumentException("Input is not lowercase letters.");
		 }
	 }
	 addWord(charArray, root, 0);
  }
  
  private void addWord(char[] charArray, TrieNode node, int k){
	  for(int i=0 ; i<26 ; i++){
		  if(node.children[i] == null){
			  node.children[i] = new TrieNode(charArray[k],false);
			  if(k == charArray.length - 1){
				  node.children[i].endOfWord = true;
//				  System.out.println(node.children[i].letter);
				  return;
			  }else{
				  addWord(charArray, node.children[i], k+1);
				  return;
			  }
		  }
		  if(charArray[k] == node.children[i].letter){
			  if(k == charArray.length - 1){
				  node.children[i].endOfWord = true;
				  return;
			  }else{
				  addWord(charArray, node.children[i], k+1);
				  return;
			  }
		  }
	  }
//	  node.children[0] = new TrieNode(charArray[k],false);
  }
  // problem 4b
  public boolean contains(String word) {
	  char[] charArray = word.toCharArray();
		 for(char c:charArray){
			 if(c < 'a' || c > 'z'){
				 throw new IllegalArgumentException("Input is not lowercase letters.");
			 }
		 }
	  return contains(charArray, root, 0);
  }
  
  private boolean contains(char[] charArray, TrieNode node, int k){
	  for(int i=0 ; i<26 ; i++){
		  if(node.children[i] == null){
			  return false;
		  }else if(charArray[k] == node.children[i].letter){
			  if(k != charArray.length - 1){
				  return contains(charArray, node.children[i], k+1);
			  }else{
				  return node.children[i].endOfWord == true;
			  }
	      }
	  }
	return false;
  }
	  

  // problem 4c
  public List<String> getStrings() {
	List<String> list = new ArrayList<>();
	StringBuilder sb = new StringBuilder();
	return getStrings(root, list, sb);    
  }
  
  private List<String> getStrings(TrieNode node, List<String> list, StringBuilder sb){
	  int j = 0;
	  for(TrieNode nodeMini : node.children){
		  if(nodeMini != null){
			  j++;
		  }
	  }
	  if(j>1){//has more than one child, deep copy to keep track of previous sb
		  for(int i=0 ; i<26 ; i++){		  
			  if(node.children[i] != null){
				  StringBuilder sbTemp = new StringBuilder();
				  for(int k=0; k<sb.length(); k++){
					  sbTemp.append(sb.charAt(k));
				  }
				  sbTemp.append(node.children[i]);			  
				  if(node.children[i].endOfWord == true){
					  list.add(sbTemp.toString());
					  getStrings(node.children[i], list, sbTemp);
				  }else{
					  getStrings(node.children[i], list, sbTemp);
				  }
			  }
		  }
	  }else if(j == 1){//has only one child, no need to keep track of prvious sb
		  for(int i=0 ; i<26 ; i++){		  
			  if(node.children[i] != null){
				  
				  sb.append(node.children[i]);			  
				  if(node.children[i].endOfWord == true){
					  list.add(sb.toString());
					  getStrings(node.children[i], list, sb);
				  }else{
					  getStrings(node.children[i], list, sb);
				  } 
			  }
	     }	  
	  
     }
	  return list;
  }

  // problem 4d
  public List<String> getStartsWith(String prefix) {
	  char[] charArray = prefix.toCharArray();
		 for(char c:charArray){
			 if(c < 'a' || c > 'z'){
				 throw new IllegalArgumentException("Input is not lowercase letters.");
			 }
		 }
	  TrieNode node = getStartsWith(charArray, root,0);
	  List<String> list = new ArrayList<>();
	  StringBuilder sb = new StringBuilder();
	  getStrings(node, list, sb);
	  for(int i = 0; i < list.size(); i++){
		 list.set(i, prefix + list.get(i));
	     if(node.endOfWord){
	    	 list.add(0, prefix);
	     }
	  }
	  return list;
   }
    
  private TrieNode getStartsWith(char[] charArray, TrieNode node, int k){
	  for(int i=0 ; i<26 ; i++){
		  if(node.children[i]==null)
			  return root;
		  if(charArray[k] == node.children[i].letter){
			  if(k != charArray.length - 1){
				  return getStartsWith(charArray, node.children[i], k+1);
			  }else{
				  node = node.children[i];
				  return node;
			  }
	      }
	  }
	return node;
  } 
	  
	  

  public String toString() {
    StringBuilder sb = new StringBuilder();
    buildString(root, sb, 0);
    return sb.toString().trim();
  }
  
  private void buildString(TrieNode node, StringBuilder sb, int layer) {
    for (int i = 0; i < layer; i++) {  // print some indentation
      sb.append(" ");
    }
    sb.append(node);    // print the node itself
    sb.append("\n");
    for (TrieNode child : node.children) {  // recursively print each subtree
      if (child != null) {
        buildString(child, sb, layer + 1);
      }
    }
  }

  private class TrieNode {
    public char letter;
    public boolean endOfWord;
    public TrieNode[] children;

    public TrieNode(char letter, boolean endOfWord) {
      this.letter = letter;
      this.endOfWord = endOfWord;
      children = new TrieNode[26]; // number of letters in English alphabet
    }
    
    public String toString() {
        return Character.toString(letter);
      }
    }

  public static void main(String[] args) {
    Trie trie = new Trie();
    
    
    trie.addWord("do");
    trie.addWord("doctor");
    trie.addWord("dock");
    trie.addWord("dog");
    trie.addWord("doll");
//    System.out.println(trie.contains("hello"));
//    System.out.println(trie.contains("hello"));
//    for(TrieNode node : trie.root.children){
//    	System.out.println(node);
//    }
//    StringBuilder sb = new StringBuilder();
//    StringBuilder sbPrime = new StringBuilder();
//    sb.append("a");
//    sbPrime = sb;
//    sbPrime.append("b");
//    System.out.println(trie.getStrings());
//    System.out.println(trie);
//    System.out.println(trie.getStrings());
    System.out.println(trie.getStrings());
    System.out.println(trie.getStartsWith("do"));
  }
}
