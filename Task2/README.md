
# 🎏Jump

>**注意**
>1. 本次熬测推荐使用搜索引擎或借助书籍查阅资料，但请务必独立完成任务，对有借鉴的代码做出标注（并不是扣分项）；允许使用 ChatGPT 等 AI 工具，若你使用了此类工具，请务必在代码中标注你使用的**工具、版本以及提示词**，并确认你理解 AI 工具所给出的答案或输出的内容；一经发现未经说明的抄袭或借助他人帮助，会直接取消本次成绩及失去以后的报名机会！
>2. 本题限制用C语言完成，但是不允许使用C语言的```system()```直接执行命令
>3. 本题建议在 Linux / macOS 环境下完成。如果没有 Linux / macOS 环境，可以在 Github Codespace 中完成任务 (参考文末)
>4. 你可以使用基本的交互指令和 C 编译器指令，C 编译器选择没有要求，推荐使用 gcc，但使用 MSVCd、clang 等等也行；你可以使用CMake管理编译过程
>5. 文件中的代码框架和注释仅供参考，可以自行发挥，功能正常即可；如果无法完成任务，我们会根据完成情况以及代码质量、注释等酌情给分
 

**源代码目录说明**

- ```src/```：任务目录
  - ```jump.h```: PartA.c ~ PartD.c的头文件
- ```CMakeLists.txt```：CMake 配置文件
- ```run.sh```：运行脚本
- ```output```：存放编译生成的可执行文件的目录
- ```README.md```：任务说明

```shell
.
├── CMakeLists.txt
├── output
├── README.md
├── run.sh
└── src
    ├── jump.h
    ├── PartA.c
    ├── PartB.c
    ├── PartC.c
    └── PartD.c
```

---

某一天，寒子璐同学正在看《Oh My JUMP!～少年JUMP拯救地球～》，这是周刊少年Jump创刊50周年之际出品的一部电视剧。但是她忽然想起熬测题还没出好，情急之下决定用```Jump```来出题😋


## PartA (10分) 😁

**任务描述**

进程是操作系统中运行的程序实例，管道则是Linux进程间常见的通信机制。请你查阅亿点点相关知识，补充```PartA.c```，要求通过管道实现父子进程之间的通信。父进程从控制台读取输入信息```Hello```，子进程接收```Hello```并打印出来。

**预期输入与输出**
```c
This is partA: 
--------------------
// 输入
Enter your answer: Hello

// 输出
Hello
  _   _       _                  ____  _             _ _       
 | | | |_ __ (_) __ _ _   _  ___/ ___|| |_ _   _  __| (_) ___  
 | | | | '_ \| |/ _` | | | |/ _ \___ \| __| | | |/ _` | |/ _ \ 
 | |_| | | | | | (_| | |_| |  __/___) | |_| |_| | (_| | | (_) |
  \___/|_| |_|_|\__, |\__,_|\___|____/ \__|\__,_|\__,_|_|\___/ 
                   |_|                                          
```

## PartB (10分) 🤨

**任务描述**

在PartA中，我们实现了一个父子进程之间的通信。下面请你补充```PartB.c```和```jump.h```里的```Process```定义，实现父进程与多个子进程之间的管道通信，子进程的数量为20个。由父进程向子进程发送消息```Hello from parent to child [index]```，子进程接收并打印```child [index] receive msg from parent: Hello from parent to child [index]``` (子进程的打印顺序必须是有序的)

**一个错误的输出结果**
```c
This is partB: 
--------------------
child [0] receive msg from parent: Hello from parent to child [0]
child [12] receive msg from parent: Hello from parent to child [12]
child [1] receive msg from parent: Hello from parent to child [1]
child [18] receive msg from parent: Hello from parent to child [18]
child [8] receive msg from parent: Hello from parent to child [8]
child [10] receive msg from parent: Hello from parent to child [10]
child [9] receive msg from parent: Hello from parent to child [9]
child [7] receive msg from parent: Hello from parent to child [7]
child [2] receive msg from parent: Hello from parent to child [2]
child [15] receive msg from parent: Hello from parent to child [15]
child [5] receive msg from parent: Hello from parent to child [5]
child [11] receive msg from parent: Hello from parent to child [11]
child [19] receive msg from parent: Hello from parent to child [19]
child [17] receive msg from parent: Hello from parent to child [17]
child [14] receive msg from parent: Hello from parent to child [14]
child [16] receive msg from parent: Hello from parent to child [16]
child [6] receive msg from parent: Hello from parent to child [6]
child [13] receive msg from parent: Hello from parent to child [13]
child [4] receive msg from parent: Hello from parent to child [4]
child [3] receive msg from parent: Hello from parent to child [3]
```
操作系统的调度程序会在多个进程间切换，所以你可能会发现你的打印结果是交错的。
想一想，有什么方法可以解决？（想不出来也没关系，打印出类似上面的乱序结果也可得 5分😘）


**预期的输出结果**
```c
This is partB: 
--------------------
child [0] receive msg from parent: Hello from parent to child [0]
child [1] receive msg from parent: Hello from parent to child [1]
child [2] receive msg from parent: Hello from parent to child [2]
child [3] receive msg from parent: Hello from parent to child [3]
child [4] receive msg from parent: Hello from parent to child [4]
child [5] receive msg from parent: Hello from parent to child [5]
child [6] receive msg from parent: Hello from parent to child [6]
child [7] receive msg from parent: Hello from parent to child [7]
child [8] receive msg from parent: Hello from parent to child [8]
child [9] receive msg from parent: Hello from parent to child [9]
child [10] receive msg from parent: Hello from parent to child [10]
child [11] receive msg from parent: Hello from parent to child [11]
child [12] receive msg from parent: Hello from parent to child [12]
child [13] receive msg from parent: Hello from parent to child [13]
child [14] receive msg from parent: Hello from parent to child [14]
child [15] receive msg from parent: Hello from parent to child [15]
child [16] receive msg from parent: Hello from parent to child [16]
child [17] receive msg from parent: Hello from parent to child [17]
child [18] receive msg from parent: Hello from parent to child [18]
child [19] receive msg from parent: Hello from parent to child [19]
```

## PartC (60分) 😖

接下来我们看点有意思的东西吧。

一个经典跳台阶问题概括如下：
 - ⼀个⼈站在楼梯的底部，他可以⼀次跳⼀步或两步。求他跳到第 n 级台阶有多少种不同的⽅法?

这个问题可以用状态方程解决```f(i) = f(i-1) + f(i-2)``` ，该方程表示跳到第i个台阶的⽅法数是前⼀个和前两个台阶的⽅法数之和。通过这个方程，我们可以计算任意台阶的⽅法数。 

**任务描述**

fibonacci数列也可以用```f(i) = f(i-1) + f(i-2)```解决。请你补充```PartC.c```，计算任意项的fibonacci数(不超过fibonacci数列的第45项)。```f(i) = f(i-1) + f(i-2)```既可以正向迭代计算，也可以反向递归计算。本题要求你采用**递归**的方式，**不断创建新的进程**，通过进程间的通信完成fibonacci数的计算，并将每一项的计算结果以及当前执行的进程ID打印出来 (进程ID是随机的)

>请保证你的输出顺序正确并且没有多余的输出

**可能的预期输出**
```c
// 第0项和第1项的输出已写好,不用打印进程ID
This is partC: 
--------------------
Please input n: 0

Final Fibonacci(0) = 0

// 输入10
This is partC: 
--------------------
Please input n: 10
Fibonacci(0) = 0, current process_id is 53997
Fibonacci(1) = 1, current process_id is 54003
Fibonacci(2) = 1, current process_id is 53985
Fibonacci(3) = 2, current process_id is 54021
Fibonacci(4) = 3, current process_id is 54032
Fibonacci(5) = 5, current process_id is 53999
Fibonacci(6) = 8, current process_id is 53983
Fibonacci(7) = 13, current process_id is 53977
Fibonacci(8) = 21, current process_id is 53973
Fibonacci(9) = 34, current process_id is 53974
Fibonacci(10) = 55, current process_id is 53969

Final Fibonacci(10) = 55

// 输入45
This is partC: 
--------------------
Please input n: 45
Fibonacci(0) = 0, current process_id is 58409
Fibonacci(1) = 1, current process_id is 58366
Fibonacci(2) = 1, current process_id is 58729
Fibonacci(3) = 2, current process_id is 58287
Fibonacci(4) = 3, current process_id is 58842
Fibonacci(5) = 5, current process_id is 59160
Fibonacci(6) = 8, current process_id is 60904
Fibonacci(7) = 13, current process_id is 61406
Fibonacci(8) = 21, current process_id is 61363
Fibonacci(9) = 34, current process_id is 59432
Fibonacci(10) = 55, current process_id is 61810
Fibonacci(11) = 89, current process_id is 62005
Fibonacci(12) = 144, current process_id is 62062
Fibonacci(13) = 233, current process_id is 62066
Fibonacci(14) = 377, current process_id is 62462
Fibonacci(15) = 610, current process_id is 62520
Fibonacci(16) = 987, current process_id is 62412
Fibonacci(17) = 1597, current process_id is 62702
Fibonacci(18) = 2584, current process_id is 62380
Fibonacci(19) = 4181, current process_id is 62825
Fibonacci(20) = 6765, current process_id is 62865
Fibonacci(21) = 10946, current process_id is 62836
Fibonacci(22) = 17711, current process_id is 62926
Fibonacci(23) = 28657, current process_id is 62540
Fibonacci(24) = 46368, current process_id is 62962
Fibonacci(25) = 75025, current process_id is 62961
Fibonacci(26) = 121393, current process_id is 57077
Fibonacci(27) = 196418, current process_id is 55019
Fibonacci(28) = 317811, current process_id is 63091
Fibonacci(29) = 514229, current process_id is 63100
Fibonacci(30) = 832040, current process_id is 55330
Fibonacci(31) = 1346269, current process_id is 63165
Fibonacci(32) = 2178309, current process_id is 55442
Fibonacci(33) = 3524578, current process_id is 63070
Fibonacci(34) = 5702887, current process_id is 54960
Fibonacci(35) = 9227465, current process_id is 54464
Fibonacci(36) = 14930352, current process_id is 64218
Fibonacci(37) = 24157817, current process_id is 64106
Fibonacci(38) = 39088169, current process_id is 55122
Fibonacci(39) = 63245986, current process_id is 54591
Fibonacci(40) = 102334155, current process_id is 54332
Fibonacci(41) = 165580141, current process_id is 54256
Fibonacci(42) = 267914296, current process_id is 54249
Fibonacci(43) = 433494437, current process_id is 54251
Fibonacci(44) = 701408733, current process_id is 54247
Fibonacci(45) = 1134903170, current process_id is 54238

Final Fibonacci(45) = 1134903170

// 非法输入
This is partC: 
--------------------
Please input n: 46
Invalid input n
```


## PartD (20分) 😭

**任务描述**

进程池是预先创建并维护的一组进程，可以复用这些进程来执行任务。在PartC中我们通过不断创建新的进程反向递归计算fibonacci数列。仔细想想，实际上并不需要一直新建子进程，我们可以新建一组进程池，由父进程调控，不断复用子进程进行计算。那么，理论上最少需要多少子进程，才能完成fibonacci数列的计算呢?
下面请你自行修改```PartD.c```和```jump.h```里的```MIN_N```，新建一组进程池，用**最少的子进程**，**正向迭代计算**fibonacci数。**其余要求与PartC一致**

**参考输出**
```c
// 输入10
This is partC: 
--------------------
Please input n: 10
Fibonacci(0) = 0, current process_id is 70641
Fibonacci(1) = 1, current process_id is 70648
Fibonacci(2) = 1, current process_id is 70624
Fibonacci(3) = 2, current process_id is 70646
Fibonacci(4) = 3, current process_id is 70643
Fibonacci(5) = 5, current process_id is 70658
Fibonacci(6) = 8, current process_id is 70628
Fibonacci(7) = 13, current process_id is 70618
Fibonacci(8) = 21, current process_id is 70615
Fibonacci(9) = 34, current process_id is 70616
Fibonacci(10) = 55, current process_id is 70606

Final Fibonacci(10) = 55
```

<br>

**Hint**
  
>Github Codespace 如何使用
>
>在仓库页面，点击键盘上的句号'.'键，你就会进入 web editor。然后打开页面内的终端，点击在 codespace 中继续，然后开始操作





