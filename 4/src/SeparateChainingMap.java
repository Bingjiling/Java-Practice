import java.util.LinkedList;

public class SeparateChainingMap<K extends Comparable<? super K>, V> implements Map<K, V> {

  public static final int SCALE_FACTOR = 2;
  public static final int INITIAL_TABLE_SIZE = 8;
  public static final double MAX_LOAD_FACTOR = 1.0;
  public LinkedList<LinkedList<Pair<K,V>>> mList;
  int size;
  int tableSize;
  
  public SeparateChainingMap() {
	  mList = new LinkedList<LinkedList<Pair<K,V>>>();
	  size = 0;
	  tableSize = INITIAL_TABLE_SIZE;
  }

  public int getSize() {
      return size;
  }

  public int getTableSize() {
      return tableSize;
  }

  public void put(K key, V value) {
      Pair<K,V> pair = new Pair<>(key, value);
      int hashCode = key.hashCode();
      hashCode = hashCode % tableSize + tableSize;
      LinkedList<Pair<K,V>> cList = mList.get(hashCode);
      cList.addLast(pair);
      size++;
      if (size/tableSize > (MAX_LOAD_FACTOR-1))
    	  upsize();
  }

  public V get(K key) {
	  int hashCode = key.hashCode();
      hashCode = hashCode % tableSize + tableSize;
      LinkedList<Pair<K,V>> cList = mList.get(hashCode);
      for (Pair<K,V> pair : cList){
    	  if (pair.key == key)
    		  return pair.value;    			  
      }
      return null;
  }

  public void upsize() {
	  LinkedList<LinkedList<Pair<K,V>>> oList = mList;
      mList = new LinkedList<LinkedList<Pair<K,V>>>();
      tableSize *= 2;
      for(LinkedList<Pair<K,V>> cList : oList){
    	  for (Pair<K,V> pair : cList){
    		  this.put(pair.key, pair.value);
    	  }
      }
  }
}
