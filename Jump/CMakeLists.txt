
cmake_minimum_required(VERSION 3.10)
project(JumpProject)
set(CMAKE_C_STANDARD 99)
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_SOURCE_DIR}/output)
add_executable(PartA src/PartA.c)
add_executable(PartB src/PartB.c)
add_executable(PartC src/PartC.c)
add_executable(PartD src/PartD.c)
