# treap; binary search tree where each node has a random priority 
# maintained in a heap order based on these priorities 

import random

class Node:
  def __init__(self, key):
    self.key = key
    self.priority = random.randin(0, 1000)
    self.left = None
    self.right = None

class Treap:
  def __init__(self):
    self.root = None

  def insert(self, key):
    self.root = self._insert(self.root, key)
  def _insert(self, node, key):
    if node is None:
      return Node(key)
    if key < node.key:
      node.left = self._insert(node.left, key)
      if node.left.priority > node.priority:
        node = self.right_rotate(node)
    else:
      node.right = self._insert(node.right, key)
      if node.right.priority > node.priority:
        node = self.left_rotate(node)
    return node

  def right_rotate(self, x):
    y = x.left
    x.left = y.right
    y.right = x
    return y

  def left_rotate(self, x):
    y = x.right
    x.right = y.left
    y.left = x
    return y 
