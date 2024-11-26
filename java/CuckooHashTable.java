// probabilistic data structure that uses multiple hash functions to place elements 
// into multiple hash tables 
// reduces likelihood of collisions compared to a single hash table approach 

import java.util.Random;

public class CuckooHashTable<K, V>
  {
    private final int TABLE_SIZE = 10000;
    private final int NUM_TABLES = 2;
    private final Random random = new Random();

    private Object[][] tables;
    public CuckooHashTable()
    {
      tables = new Object[NUM_TABLES][TABLE_SIZE];
    }
    private int hash(K key, int tableIndex)
    {
      // simple hash function
      int hash = key.hashCode() % TABLE_SIZE;
      return (hash + tableIndex) % TABLE_SIZE;
    }
    public void put(K key, V Value)
    {
      int rehashCount = 0;
      while (true) {
        int tableIndex = random.nextInt(NUM_TABLES);
        int index = hash(key, tableIndex);
        if(tables[tableIndex][index] == null) {
          tables[tableIndex][index] = new Entry<>(key, value);
          return;
        }
        // rehash existing element
        K oldKey = (K) tables[tableIndex][index];
        tables[tableIndex][index] = new Entry<>(key, value);
        key = oldKey;

        if(++rehashCount > TABLE_SIZE)
        {
          rehash();
          return;
        }
      }
    }
    public V get(K key)
    {
      for(int i = 0; i < NUM_TABLES; i++) {
        int index = hash(key, i);
        if(tables[i][index] != null && ((Entry<?, ?>) tables[i][index]).key.equals(key)) {
          return (V) ((Entry<?, ?>) tables[i][index]).value;
        }
      }
      return null;
    }

    private void rehash() {
        // Implement rehashing logic: create new tables, rehash elements
    }

    private static class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
    
