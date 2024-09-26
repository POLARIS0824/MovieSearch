#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include "Cocktail.h"

int compareCocktails(const void *a, const void *b);

float calculateAlcohol(Cocktail cocktail);

void printCocktail(Cocktail cocktail);

int main()
{
    Cocktail cocktailList[] = {
        {"Cocktail0", 10.0f, 50, 120},
        {"Cocktail1", 30.0f, 100, 300},
        {"Cocktail2", 12.8f, 50, 150},
        {"Soda", 0.0f, 0, 330},
        {"Cocktail4", 43.2f, 80, 120}
    };

    size_t numCocktails = sizeof(cocktailList) / sizeof(cocktailList[0]);

    qsort(cocktailList, numCocktails, sizeof(Cocktail), compareCocktails);

    for (size_t i = 0; i < numCocktails; ++i) {
        printCocktail(cocktailList[i]);
    }

    return EXIT_SUCCESS;
}

float calculateAlcohol(Cocktail cocktail) //计算酒精度数
{
    int totalVolume = cocktail.baseVolume + cocktail.nonAlcoholicBeveragesVolume;
    return (cocktail.baseStrength * cocktail.baseVolume) / totalVolume;
}

//输出函数
void printCocktail(Cocktail cocktail)
{
    printf("Name='%s'\n", cocktail.name);
    printf("BaseStrength=%.1f%\n",cocktail.baseStrength);
    printf("baseVolume=%i\n",cocktail.baseVolume);
    printf("nonAlcoholicBeveragesVolume=%i\n",cocktail.nonAlcoholicBeveragesVolume);
}

//以下代码涉及qsort函数以及指针，暂未学习，通过GitHub copilot生成
//版本号v1.234.0，prompt为：使用qsort函数对Cocktail数组进行排序

int compareCocktails(const void *a, const void *b) //这是两个待比较元素的指针
{
    Cocktail cocktailA = *(Cocktail *)a; //将void指针转换为Cocktail指针
    Cocktail cocktailB = *(Cocktail *)b; //暂未理解透彻，学习中
    float alcoholContentA = calculateAlcohol(cocktailA);
    float alcoholContentB = calculateAlcohol(cocktailB);

//比较函数，返回值为负数，零或正数，分别表示第一个参数小于，等于或大于第二个参数（已经理解）

    // 如果 A 是无酒精饮料，排在前面
    if (alcoholContentA == 0) return -1;
    // 如果 B 是无酒精饮料，排在前面
    if (alcoholContentB == 0) return 1;
    // 否则按酒精度数倒序排列
    if (alcoholContentA < alcoholContentB) return 1;
    if (alcoholContentA > alcoholContentB) return -1;
    return 0;
}
