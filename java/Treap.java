import java.util.Random;

public class Treap<T extends comparable<T>>
  {
    private Node root;
    private Random random = new Random();

    private class Node
      {
        T key;
        int priority;
        Node left, right;

        public Node(T key) {
          this.key = key;
          this.priority = random.nextInt();
        }
      }
    public void insert(T key)
    {
      root = insert(root, key);
    }
    private Node insert(Node node, T key)
    {
      if(node == null)
      {
        return new Node(key);
      }
      if(key.compareTo(node.key) < 0) {
        node.left = insert(node.left, key);
        if(node.left.priority > node.priority) {
          node = rightRotate(node);
        }
        else
        {
          node.right = insert(node.right, key);
          if(node.right.priority > node.priority)
          {
            node = leftRotate(node);
          }
        }
        return node;
      }
    }
      private Node rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }

    private Node leftRotate(Node x)
    {
      Node y = x.right;
      x.right = y.left;
      y.left = x;
      return y;
    }
  }
