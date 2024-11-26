#include <stdio.h>
#include <stdlib.h>

#define TABLE_SIZE 100

int hash1(int key)
{
  return key % TABLE_SIZE;
}

int hash2(int key)
{
  return (key * 31) % TABLE_SIZE;
}

void insert(int table[], int key)
{
  int pos1 = hash1(key);
  int pos2 = hash2(key);

  if(table[pos1] == 0) {
    table[pos1] = key;
  }
  else if(table[pos2] == 0) {
    table[pos2] = key;
  }
  else {
    // handle collision
    int victim = table[pos1];
    table[pos1] = key;

    int new_pos1 = hash1(victim);
    int new_pos2 = hash2(victim);

    if(new_pos1 == pos1 || new_pos2 == pos2) {
      printf("hash table overflow\n");
      return;
    }
    insert(table, victim);
  }
}

int search(int table[], int key)
{
  int pos1 = hash1(key);
  int pos2 = hash2(key);

  if(table[pos1] == key || table[pos2] == key) {
    return 1;
  }
  return 0;
}

int main()
{
  int table[TABLE_SIZE] = {0};
  insert(table, 10);
  insert(table, 20);
  insert(table, 30);
  printf("search for 2: %d\n", search(table,20));
  printf("search for 40: %d\n", search(table, 40));

  return 0;
}
