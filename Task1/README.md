# 鸡尾酒排队

午夜，酒吧，联创团队，鸡尾酒派对，Mobile 组的一位同学似乎有些微醉，看着桌上五颜六色的鸡尾酒，他突发奇想，想给它们排个队。

已知鸡尾酒的度数 = 基酒的度数 * 基酒的体积 / 鸡尾酒总体积。假设鸡尾酒仅由基酒(可选)与无酒精饮料调制而成，请你发挥你扎实的编程语言基本功与精湛的文档阅读能力，**完善已给出的代码**，让排队后的鸡尾酒列表满足以下要求：

- 鸡尾酒按酒精度倒序排列，即度数从高到低排列
- 为了照顾早已酩酊大醉不省人事的选手，请将酒精度为 0 的无酒精饮料排在 **列表最前**

可选语言的基础代码已给出，请观察现有代码的编译时报错或运行时报错，阅读相关文档，使 程序中的鸡尾酒排序可以正常运行并输出预期结果。

预期结果（不必格式完全一致）：

```plaintext
Cocktail{name='Soda', baseStrength=0.0, baseVolume=0, nonAlcoholicBeveragesVolume=330}
Cocktail{name='Cocktail4', baseStrength=43.2, baseVolume=80, nonAlcoholicBeveragesVolume=120}
Cocktail{name='Cocktail1', baseStrength=30.0, baseVolume=100, nonAlcoholicBeveragesVolume=300}
Cocktail{name='Cocktail2', baseStrength=12.8, baseVolume=50, nonAlcoholicBeveragesVolume=150}
Cocktail{name='Cocktail0', baseStrength=10.0, baseVolume=50, nonAlcoholicBeveragesVolume=120}
```

**注意：选手只需选择任意一门语言进行作答**

提示：
1. 针对 C 语言，你需要详细了解 `qsort()` 的用法，并实现相关函数的定义
2. 针对 Java 语言，请仔细查看目前代码的编译错误
3. 针对 ObjectiveC 语言，请仔细查看代码运行时的错误
4. 针对 Swift 语言，请仔细查看代码的编译错误
5. 请记住，将 Cocktail 对象格式化输出