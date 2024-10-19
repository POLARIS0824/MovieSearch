#include "jump.h"

//使用了GitHub copilot，版本号为v1.234.0，prompt包括解读REDEME文档，如何实现父进程和子进程之间的通信
//prompt还包括提供预期输入与输出，提供已经写好的代码寻求注释

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
    pid_t pid;  // 声明一个变量，用于存储子进程的进程id
    char input_msg[MSG_SIZE];  // 声明一个字符数组，用于存储从控制台读取的输入信息
    char read_msg[MSG_SIZE];  // 声明一个字符数组，用于存储从管道读取的信息
    // 声明一个整型数组，用于存储管道的文件描述符
    // pipefd[0] 是管道的读端，用于从管道读取数据
    // pipefd[1] 是管道的写端，用于向管道写入数据
    int pipefd[2];  
    
    // TODO: Create pipe and process
    // Your code here
    // 创建管道
    // 创建一个管道，并将文件描述符存储在 pipefd 数组中
    // pipefd[0] 是管道的读端，pipefd[1] 是管道的写端
    // 如果 pipe() 函数返回 -1，表示创建管道失败
    if (pipe(pipefd) == -1)
    {
        perror("pipe");  //"pipe" 是错误前缀，用于说明出错的上下文
        return;  //可以使用exit直接终止整段程序
    }

    // 创建子进程，并将子进程的进程ID存储在pid变量中
    pid = fork();
    // 检查fork()函数的返回值是否为-1
    // 如果返回值为-1，表示创建子进程失败
    if (pid == -1)
    {
        // 输出错误信息 "fork" 到标准错误输出，并描述错误原因
        perror("fork");
        return;
    }

    if (pid > 0)
    {
        // TODO: Read input from console using scanf and store it in input_msg
        // TODO: Write the input_msg to the pipe
        // Your code here
        // 父进程执行的代码块
        // 关闭管道的读端，因为父进程只需要写入数据
        close(pipefd[0]); //关闭读端
        // 从控制台读取输入信息，并存储在 input_msg 数组中
        printf("Enter your answer: ");
        // 将输入的信息写入管道
        scanf("%s", input_msg);
        // strlen(input_msg) + 1 用于包括字符串末尾的空字符 '\0'
        write(pipefd[1], input_msg, strlen(input_msg) + 1);
        // 关闭管道的写端，表示写入操作完成
        close(pipefd[1]); //关闭写端
    }
    else
    {
        // TODO: Read input_msg from the pipe and store it in read_msg
        // TODO: Print the read_msg and colorful UNIQUE_STUDIO
        // Your code here
        // 子进程执行的代码块
        // 关闭管道的写端，因为子进程只需要读取数据
        close(pipefd[1]); // 关闭写端
        // 从管道中读取信息，并存储在 read_msg 数组中
        read(pipefd[0], read_msg, MSG_SIZE);
        // 打印从管道中读取的信息和图案
        printf("\n%s\n%s\n", read_msg, UNIQUE_STUDIO);
        // 关闭管道的读端，表示读取操作完成
        close(pipefd[0]); // 关闭读端
    }
}