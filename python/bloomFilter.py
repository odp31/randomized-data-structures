class BloomFilter:
  def __init__(self, m, k):
    self.bits = [0] * m
    self.k = k

  def add(self, item):
    for i in range(self.k):
      index = hash(item + str(i)) % len(self.bits)
      self.bits[index] = 1

  def might_contain(self, item):
    for i in range(self.k):
      index = hash(item + str(i)) % len(self.bits)
      if self.bits[index] == 0:
        return False
    return True 
