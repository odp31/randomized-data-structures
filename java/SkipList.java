// probabilistic data structure that offers logarithmic search, insertion and deletion time
// its a variation of a linkedlist where elements are organized into multiple levels with 
// each level having fewwer elements than level below it

import java.util.Random;

public class SkipList
  {
    private Node head;
    private int maxLevel;
    private double p;     // probability of moving to next level

    public Skiplist()
    {
      head = new Node(-1);
      maxLevel = 0;
      p = 0.5;     // adjust prob as needed
    }
    private class Node
      {
        int key;
        Node[] forward;
        public Node(int key, int level)
        {
          this.key = key;
          forward = new Node[level + 1];
        }
      }
    private int randomLevel()
    {
      int level = 0;
      while (Math.random() < p && level < maxLevel)
        {
          level++;
        }
      return level;
    }
    public void insert(int key)
    {
      Node current = head;
      Node update[] = new Node[maxLevel + 1];
      // find insertion point
      for(int i = maxLevel; i >= 0; i--) {
        while(current.forward[i] != null && cuurent.forward[i].key < key) {
          current = current.forward[i];
        }
        update[i] = current;
      }
      // create new node
      int level = randomLevel();
      if(level > maxLevel) {
        maxLevel = level;
      }
      Node x = new Node(key, level);
      // insert new node
      for(int i = 0; i <= level; i++) {
        x.forward[i] = update[i].forward[i];
        update[i].forward[i] = x;
      }
    }
  }
