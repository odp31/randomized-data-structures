// self balancing tree data structure that maintains sorted data and allows for 
// efficient insertions, deletions and searches
// particularly useful for disk-based storage systems due to its ability to reduce
// number of disk I/O operations 

public class BTree
  {
    private int t;     // minimum degree(number of children)
    private Node root;

    class Node
      {
        int n;       // num keys
        int key[];
        Node child[];
        boolean leaf;

        Node(int t1, boolean leaf1)
        {
          t = t1;
          leaf = leaf1; 
          key = new int[2 * t - 1];
          child = new Node[2 * t];
          n = 0;
        }
      }
    // search for key in subtree rooted with this node
    Node search(Node x, int key) {
      // find first key >= k
      int i = 0;
      while(i < x.n && key > x.key[i])
        i++;
      // if found directly
      if(i < x.n && key == x.key[i])
        return x;
      // if key is not found here and its a non leaf node, recurse appropriate child
      if(x.leaf)
        return null;
      else
        return search(x.child[i], key);
    }
    // split child y of x into two at index i
    void splitChild(Node x, int i, Node y)
    {
      // create a new node z
      Node z = new Node(y.t, y.leaf);
      z.n = t - 1;
      // copy last t-1 keys and t children of y to z
      for(int j = 0; j < t - 1; j++) {
        z.key[j] = y.key[j+t];
        y.key[j+t] = 0;
      }
      if(!y.leaf) {
        for(int j = 0; j < t; j++) {
          z.child[j] = y.child[j + t];
          y.child[j+t] = null;
        }
      }
      y.n = t - 1;
      // create space for new child in x 
      for(int j = x.n; j >= i + 1; j--)
        x.child[j + 1] = x.child[j];
      // link z as child of x
      x.child[i+1] = z;
      // shift key and chld pointers and insert new key
      for(int j = x.n - 1; j >= i; j--)
        x.key[j+1] = x.key[j];
      x.key[i] = y.key[t - 1];
      y.key[t - 1] = 0;
      x.n = x.n + 1;
    }
  }
