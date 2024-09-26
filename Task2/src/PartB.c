#include "jump.h"

//使用了GitHub copilot，版本号为v1.234.0，prompt包括解读REDEME文档，如何实现父进程和子进程之间的通信
//prompt还包括提供预期输入与输出，提供已经写好的代码寻求注释
//没有实现有序的输出:(

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
    Process processes[NUM_PROCESS]; // process pool进程池，用于存储每个子进程的信息
    char buffer[MSG_SIZE]; // 缓冲区，用于存储从管道读取的信息
    // TODO: Create pipes and processes
    for (int i = 0; i < NUM_PROCESS; i++)
    {
        // 创建管道，并将文件描述符存储在 processes[i].pipefd 数组中    
        if (pipe(processes[i].pipefd) == -1) 
        {
            perror("pipe");
            exit(1);
        }

        // 创建子进程，并将子进程的进程ID存储在 processes[i].pid 变量中
        processes[i].pid = fork();
        if (processes[i].pid == -1) {
            perror("fork");
            exit(1);
        }

        if (processes[i].pid == 0) {
            // 子进程
            close(processes[i].pipefd[1]); // 关闭写端，因为子进程只需要读取数据
            read(processes[i].pipefd[0], buffer, MSG_SIZE); // 从管道中读取消息，并存储在buffer数组中
            printf("child [%d] receive msg from parent: %s\n", i, buffer); // 打印从管道中读取的消息
            close(processes[i].pipefd[0]); // 关闭读端
            exit(0);
        } else {
            // 父进程
            close(processes[i].pipefd[0]); // 关闭读端，因为父进程只需要写入数据
        }
    }
    // TODO: Parent process should write messages to pipe
    // TODO: Child processes should read from pipe and print the message
    // TODO: Messages should be printed in order
    // Your code here
    // 父进程向每个子进程发送消息
    for (int i = 0; i < NUM_PROCESS; i++) 
    {
        char message[MSG_SIZE]; // 缓冲区，用于存储要发送的消息
        snprintf(message, sizeof(message), "Hello from parent to child [%d]", i); // 格式化消息，将其存储在 message 数组中
        write(processes[i].pipefd[1], message, strlen(message) + 1); // 将消息写入管道
        close(processes[i].pipefd[1]); // 关闭写端
    }

    // 等待所有子进程结束
    for (int i = 0; i < NUM_PROCESS; i++) 
    {
        waitpid(processes[i].pid, NULL, 0); //等待子进程结束
    }
}
