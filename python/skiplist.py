import random

class Node:
  def __init__(self, key, level):
    self.key = key
    self.forward = [None] * level

class SkipList:
  def __init__(self):
    self.header = Node(-1, 32)
    self.level = 0

  def randomLevel(self):
    level = 0
    while random.random() < 0.5 and level < 31:
      level += 1
    return level

  def insert(self, key):
    update = [None] * (self.level + 1)
    current = self.header

    for i in range(self.level, -1, -1):
      while current.forward[i] and current.forward[i].key < key:
        current = current.forward[i]
      update[i] = current 

    level = self.randomLevel()
    if level > self.level:
      for i in range(self.level + 1, level + 1):
        update[i] = self.header

    x = Node(key, level)
    for i in range(level + 1):
      x.forward[i] = update[i].forward[i]
      update[i].forward[i] = x

    self.level = max(self.level, level)

def search(self, key):
  current = self.header
  for i in range(self.level, -1, -1):
    while current.forward[i] and current.forward[i].key < key:
      current = current.forward[i]
  current = current.forward[0]
  return current.key == key if current else False 
