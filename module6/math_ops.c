#include <stdio.h>

void print_math(int a, int b) {
    printf("Sum:     %d\n", a + b);
    printf("Product: %d\n", a * b);
}

int main(void) {
    int x, y;

    printf("Enter first number:  ");
    scanf("%d", &x);
    printf("Enter second number: ");
    scanf("%d", &y);

    print_math(x, y);

    return 0;
}
