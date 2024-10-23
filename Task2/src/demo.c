#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <string.h>

#define FIFO_NAME "my_fifo"
#define MSG_SIZE 100
#define NUM_CHILDREN 20

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

    partB();

    return 0;
}

void partB()
{
    printf("This is partB: \n");
    printf("--------------------\n");

    int fd;
    pid_t pid;
    char buf[MSG_SIZE];

    // 创建命名管道
    if (mkfifo(FIFO_NAME, 0666) == -1) {
        perror("mkfifo");
        exit(EXIT_FAILURE);
    }

    for (int i = 0; i < NUM_CHILDREN; i++) {
        pid = fork();
        if (pid == -1) {
            perror("fork");
            exit(EXIT_FAILURE);
        }

        if (pid == 0) { // 子进程
            fd = open(FIFO_NAME, O_RDONLY);
            if (fd == -1) {
                perror("open");
                exit(EXIT_FAILURE);
            }
            read(fd, buf, sizeof(buf));
            printf("child [%d] receive msg from parent: %s\n", i, buf);
            close(fd);
            exit(0); // 子进程结束
        } else { // 父进程
            fd = open(FIFO_NAME, O_WRONLY);
            if (fd == -1) {
                perror("open");
                exit(EXIT_FAILURE);
            }
            snprintf(buf, sizeof(buf), "Hello from parent to child [%d]", i);
            write(fd, buf, strlen(buf) + 1);
            close(fd);
            wait(NULL); // 等待子进程结束
        }
    }

    unlink(FIFO_NAME); // 删除命名管道
}