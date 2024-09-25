#include "jump.h"

void partA();

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
    partA();
    return 0;
}

void partA()
{
    printf("This is partA: \n");
    printf("--------------------\n");
    // TODO: Declare necessary variables
    // Your code here
    pid_t pid;
    char input_msg[MSG_SIZE];
    char read_msg[MSG_SIZE];

    // TODO: Create pipe and process
    // Your code here

    if (pid > 0)
    {
        // TODO: Read input from console using scanf and store it in input_msg
        // TODO: Write the input_msg to the pipe
        // Your code here
        printf("Enter your answer: ");
    }
    else
    {
        // TODO: Read input_msg from the pipe and store it in read_msg
        // TODO: Print the read_msg and colorful UNIQUE_STUDIO
        // Your code here
        printf("\n%s\n%s\n", read_msg, UNIQUE_STUDIO);
    }
}