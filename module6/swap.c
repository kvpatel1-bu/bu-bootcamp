#include <stdio.h>

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

void broken_swap(int a, int b) {
    int temp = a;
    a = b;
    b = temp;
}

int main(void) {
    int x = 10;
    int y = 20;

    printf("Before swap: x = %d, y = %d\n", x, y);
    swap(&x, &y);
    printf("After swap:  x = %d, y = %d\n", x, y);

    int p = 10;
    int q = 20;

    printf("Before broken_swap: p = %d, q = %d\n", p, q);
    broken_swap(p, q);
    /* Values are unchanged because broken_swap receives copies of p and q,
       not their addresses, so it only swaps its own local parameters. */
    printf("After broken_swap:  p = %d, q = %d\n", p, q);

    return 0;
}
