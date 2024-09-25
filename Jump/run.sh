#!/bin/bash

if [ ! -d "build" ]; then
    mkdir build
    cd build
    cmake ..
    make
    cd ..
else
    cd build
fi

make
../output/PartA
../output/PartB
../output/PartC
../output/PartD