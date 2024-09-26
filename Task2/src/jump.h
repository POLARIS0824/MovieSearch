//头文件保护符，确保头文件只被包含一次
#ifndef JUMP_H
#define JUMP_H

// Your code here
#include <unistd.h>
#include <sys/types.h>
#include <stdio.h>

#define MSG_SIZE 100 // message size
#define NUM_PROCESS 20 // number of child processes in partB
#define MAX_INPUT 45 // maximum input number in partC
#define MIN_N 100 // minimum number of child processes in partD, you can change it


#define UNIQUE_STUDIO "\
\033[31m  _   _       _                  ____  _             _ _       \n\
\033[32m | | | |_ __ (_) __ _ _   _  ___/ ___|| |_ _   _  __| (_) ___  \n\
\033[33m | | | | '_ \\| |/ _` | | | |/ _ \\___ \\| __| | | |/ _` | |/ _ \\ \n\
\033[34m | |_| | | | | | (_| | |_| |  __/___) | |_| |_| | (_| | | (_) |\n\
\033[35m  \\___/|_| |_|_|\\__, |\\__,_|\\___|____/ \\__|\\__,_|\\__,_|_|\\___/ \n\
\033[36m                   |_|                                          \n\
\033[0m"

// TODO: Define your Process structure here
// Your code here
typedef struct
{
    int pipefd[2]; //一个整型数组，用于存储管道的文件描述符。
    //pipefd[0] 是管道的读端，pipefd[1] 是管道的写端
    pid_t pid;  //一个进程ID，用于存储子进程的PID
} Process;

void partA();
void partB();

#endif // JUMP_H
