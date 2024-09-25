#include "jump.h"

void partC();
int fibonacci(int n);

int main(int argc, char *argv[])
{
    printf("\n\n");
    printf("*****************************************************************\n");
    printf("*                                                               *\n");
    printf("*\033[31m  _   _       _                  ____  _             _ _       \033[0m*\n");
    printf("*\033[32m | | | |_ __ (_) __ _ _   _  ___/ ___|| |_ _   _  __| (_) ___  \033[0m*\n");
    printf("*\033[33m | | | | '_ \\| |/ _` | | | |/ _ \\___ \\| __| | | |/ _` | |/ _ \\ \033[0m*\n");
    printf("*\033[34m | |_| | | | | | (_| | |_| |  __/___) | |_| |_| | (_| | | (_) |\033[0m*\n");
    printf("*\033[35m  \\___/|_| |_|_|\\__, |\\__,_|\\___|____/ \\__|\\__,_|\\__,_|_|\\___/ \033[0m*\n");
    printf("*\033[36m                   |_|                                         \033[0m*\n");
    printf("*                                                               *\n");
    printf("*****************************************************************\n");
    printf("\n\n");
    
    // You can use this main function to test your code
    partC();
    return 0;
}

void partC()
{
    printf("This is partC: \n");
    printf("--------------------\n");

    int n;
    printf("Please input n: ");
    scanf("%d", &n);
    if (n < 0 || n > MAX_INPUT)
    {
        printf("Invalid input n\n");
        return;
    }

    // TODO: Print the final Fibonacci number
    // Your code here

    if (n == 0 || n == 1)
    {
        printf("\nFinal Fibonacci(%d) = %d\n", n, n);
    }
    else
    {
    }

    return;
}

int fibonacci(int n)
{
    // TODO: Use recursion and processes to calculate the Fibonacci number
    // Your code here

    return 0;
}