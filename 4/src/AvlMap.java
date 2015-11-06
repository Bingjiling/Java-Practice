public class AvlMap<K extends Comparable<? super K>, V> implements Map<K, V> {

	AvlTree<Pair<K,V>> aT;
	
  public AvlMap() {
	  aT = new AvlTree<Pair<K,V>>();
  }

  @Override
  public void put(K key, V value) {
	  Pair<K,V>pair = new Pair<>(key, value);
      aT.insert(pair);
  }

  @Override
  public V get(K key) {
	  Pair<K,V>pair = new Pair<>(key, null);
      pair = aT.get(pair);
      return pair.value;
  }
}
