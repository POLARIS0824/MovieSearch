#include "jump.h"

void partB();

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
    partB();
    return 0;
}

void partB()
{
    printf("This is partB: \n");
    printf("--------------------\n");

    // TODO: Declare necessary variables
    // TODO: Define your process structure
    // Your code here
    Process processes[NUM_PROCESS]; // process pool

    // TODO: Create pipes and processes
    // TODO: Parent process should write messages to pipe
    // TODO: Child processes should read from pipe and print the message
    // TODO: Messages should be printed in order
    // Your code here

}