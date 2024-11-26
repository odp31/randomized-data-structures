#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define BIT_SIZE 1024

// function to hash a string
unsigned int hash(const char *str)
{
  unsigned int hash = 5381;
  int c;
  while(c = *str++) {
    hash = ((hash << 5) + hash) + c;
  }
  return hash % BIT_SIZE;
}

// function to initialize bloom filter
void initialize_bloom_filter(unsigned char *bloom_filter)
{
  memset(bloom_filter, 0, BIT_SIZE / 8);
}

// function to add element to bloom filter
void add_to_bloom_filter(unsigned char *bloom_filter, const char *str)
{
  unsigned int hash_value = hash(str);
  bloom_filter[hash_value / 8] |= 1 << (hash_value % 8);
}

// function to check if element is prob in bloom filter 
int is_in_bloom_filter(unsigned char *bloom_filter, const char *str)
{
  unsigned int hash_value = hash(str);
  return bloom_filter[hash_value / 8] & (1 << (hash_value % 8));
}

int main()
{
  unsigned char bloom_filter[BIT_SIZE / 8];
  initialize_bloom_filter(bloom_filter);

  add_to_bloom_filter(bloom_filter, "apple");
  add_to_bloom_filter(bloom_filter, "banana");
  add_to_bloom_filter(bloom_filter, "cherry");

  printf("Is 'apple' in the filter? %d\n", is_in_bloom_filter(bloom_filter, "apple"));
  printf("Is 'grape' in the filter? %d\n", is_in_bloom_filter(bloom_filter, "grape"));

  return 0;
}
