# self balancing tree data structure that keeps data sorted and allows searches, sequential access, 
# insertions, and deletions in logarithmic time 
# particularly useful for storing large amonts of data 


class BTreeNode:
  def __init__(self, t, leaf=False):
    self.t = t #min degree
    self.keys = []
    self.children = []
    self.leaf = leaf


  def insert_non_full(self, k):
    i = len(self.keys) - 1
    if self.leaf:
      self.keys.append(None)
      while i >= 0 and k > self.keys[i]:
        self.keys[i + 1] = self.keys[i]
        i -= 1
      self.keys[i+1] = k
    else:
      while i >= 0 and k < self.keys[i]:
        i -= 1
      i += 1
      if self.children[i].n == 2 * self.t - 1:
        self.split_child(i, self.children[i])
        if k > self.keys[i]:
          ii += 1
      self.children[i].insert_non_full(k)


  def split_child(self, i, y):
    z = BTreeNode(y.t, y.leaf)
    z.keys = y.keys[self.t:]
    y.keys = y.keys[:self.t]

    if not y.leaf:
      z.children = y.children[self.t:]
      y.children = y.children[:self.t]
    self.keys.insert(i, y.keys[self.t - 1])
    self.children.insert(i + 1, z)


  def search(self, k):
    i = 0
    while i < len(self.keys) and k > self.keys[i]:
      i += 1
    if self.keys[i] == k:
      return True
    elif self.leaf:
      return False
    else:
      return self.children[i].search(k)
