# hashing technique that uses multiple hash functions to place elements into a hash table
# particularly efficient for handling collisions, offering constant time average lookup, insertion and deletion 

class CuckooHashTable:
  def __init__(self, capacity):
    self.capcaity = capacity
    self.table = [None] * capacity
    self.hash_functions = [self.hash1, self.hash2]

  def hash1(self, key):
    return key % self.capacity

  def hash2(self, key):
    return (key * 31) & self.capacity 

  def insert(self, key):
    pos1 = self.hash1(key)
    pos2 = self.hash2(key)

    if self.table[pos1] is None:
      self.table[pos1] = key
    elif self.table[pos2] is None:
      self.table[pos2] = key
    else:
      self.relocate(key, pos1, pos2) #handle collision 


  def relocate(self, key, pos1, pos2):
    victim = self.table[pos1]
    self.table[pos1] = key

    new_pos1 = self.hash1(victim)
    new_pos2 = self.hash2(victim)

    if new_pos1 == pos1 or new_pos2 == pos2:
      # rehashing might be necessary if loop continues
      raise ValueError("Hash table overflow")
    self.relocate(victim, new_pos1, new_pos2)


  def search(self, key):
    pos1 = self.hash1(key)
    pos2 = self.hash2(key)

    if self.table[pos1] == key or self.table[pos2] == key:
      return True
    return False

  def delete(self, key):
    pos1 = self.hash1(key)
    pos2 = self.hash2(key)

    if self.table[pos1] == key:
      self.table[pos1] = None
    elif self.table[pos2] == key:
      self.table[pos2] = None
