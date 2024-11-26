// probabilistic data structure that tells you whether an element is probably in a set 
// space efficient but has a small false positive rate

import java.util.BitSet;

public class BloomFilter
  {
    private BitSet bitset;
    private int numHashes;

    public BloomFilter(int size, int numHashes)
    {
      this.bitset = new BitSet(size);
      this.numHashes = numHashes;
    }
    public void add(String value)
    {
      for(int i = 0; i < numHashes; i++) {
        int hash = hashFunction(value, i) % bitset.size();
        bitset.set(hash);
      }
    }
    public boolean mightContain(String value) {
      for(int i = 0; i < numHashes; i++) {
        int hash = hashFunction(value, i) % bitset.size();
        if (!bitset.get(hash)) {
          return false;
        }
      }
      return true;
    }
    private int hashFunction(String value, int seed)
    {
      int hash = 0;
      for(int i = 0; i < value.length(); i++) {
        hash = 31 * hash + value.charAt(i);
      }
      return hash + seed;
    }
  }
