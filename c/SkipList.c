#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAX_LEVEL 5

struct Node {
  int key;
  struct Node* forward[MAX_LEVEL];
};

struct SkipList {
  int level;
  struct Node* header;
};

// function to create new node
struct Node* createNode(int key, int level)
{
  struct Node* node = (struct Node*)malloc(sizeof(struct Node));
  node->key = key;
  for(int i = 0; i < level; i++) {
    node->forward[i] = NULL;
  }
  return node;
}

// function to create new skip list 
struct SkipList* createSkipList()
{
  struct SkipList* skipList = (struct SkipList*)malloc(sizeof(struct SkipList));
  skipList->header = createNode(INT_MIN, MAX_LEVEL);
  skipList->level = 0;
  return skipList;
}

// function to randomly determine level of a new node
int randomLevel()
{
  int level = 0;
  while(rand() % 2) {
    level++;
  }
  return level;
}

// function to insert a key into skip list
void insert(struct SkipList* skipList, int key)
{
  struct Node* current = skipList-> header;
  struct Node* update[MAX_LEVEL];

  for(int i = skipList-> level - 1; i >= 0; i--) {
    while(current->forward[i] != NULL && current->forward[i]-> key < key) {
      current = current->forard[i];
    }
    update[i] = current;
  }
  int level = randomLevel();
  if (level > skipList-> level) {
    for(int i = skipList->level; i < level; i++) {
      update[i] = skipList->header;
    }
    skipList->level = level;
  }
  struct Node* node = createNode(key, level);
  for(int i = 0; i < level; i++) {
    node->forward[i] = update[i]->forward[i];
    update[i]->forward[i] = node;
  }
}

// function to search for a key in skip list
int search(struct SkipList* skipList, int key)
{
  struct Node* current = skipList->header;
  for(int i = skipList->level - 1; i >= 0; i--) {
    while(current->forward[i] != NULL && current->forward[i]->key < key) {
      current = current->forward[i];
    }
  }
  current = current->forward[0];
  return current != NULL && current->key == key;
}
      
