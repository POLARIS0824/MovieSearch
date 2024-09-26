#include<stdio.h>

//这是一个最为基础的版本，只有一个小明和一个怪物，他们互相攻击，直到有一方死亡。
//JAVA语言不太熟练，因此使用了c语言

//定义结构体
typedef struct 
{
    int health;
    int attack_damage;
    float attack_speed;
    int armor;
} Character;

//定义装备结构体
//定义了需要用到的装备属性，但各个装备战斗计算太过复杂，因此没有实现预期功能
typedef struct 
{
    int attack_bonus;           //攻击加成
    float life_steal;           //生命偷取
    int armor_bonus;            //护甲加成
    int health_bonus;           //生命值加成
    float attack_speed_bonus;   //攻击速度加成
    float critical_chance;      //暴击几率
} Equipment;

int calculate_damage(int atteck_damage, int armor);

void fight(Character *xiaoming, Character *BOSShuaixuezhang);

int main(void)
{
    //初始化角色
    Character xiaoming = {2000,100,1.00,50};
    Character BOSShuaixuezhang = {20000,200,1.00,200}; //BOSS是帅学长不是坏学长:)
    fight(&xiaoming, &BOSShuaixuezhang);
    return 0;
}

//计算伤害
int calculate_damage(int atteck_damage, int armor)
{
    //这个伤害计算公式使用了github copilot生成,版本号v1.234.0，prompt为：依据这个公式计算伤害
    //现在我已理解这个公式的含义
    //护甲 $R$ 、减免前伤害 $d_r$ 与减免后伤害 $d_m$ 的关系为：$`d_r = d_m\times(1+\frac{R}{100})`$
    int damage_reduction = atteck_damage * (1 + armor / 100);
    return damage_reduction;
}

//模拟两人战斗
//使用了github copilot生成,版本号v1.234.0，prompt为：模拟两人战斗
void fight(Character *xiaoming, Character *BOSShuaixuezhang)
{
    while (xiaoming->health > 0 && BOSShuaixuezhang->health > 0)
    {
        //小明攻击怪物
        int damage = calculate_damage(xiaoming->attack_damage, BOSShuaixuezhang->armor);
        BOSShuaixuezhang->health -= damage;
        printf("小明对BOSS造成了 %d 伤害。 BOSS现在的生命值是 %d.\n", damage, BOSShuaixuezhang->health);
        printf("小明的生命值是: %d, BOSS的生命值是: %d\n", xiaoming->health, BOSShuaixuezhang->health);
        if (BOSShuaixuezhang->health <= 0)
        {
            printf("小明胜利！\n");
            return;
        }
        //怪物攻击小明
        damage = calculate_damage(BOSShuaixuezhang->attack_damage, xiaoming->armor);
        xiaoming->health -= damage;
        printf("BOSS对小明造成了 %d 伤害。 小明现在的生命值是 %d.\n", damage, xiaoming->health);
        printf("小明的生命值是: %d, BOSS的生命值是: %d\n", xiaoming->health, BOSShuaixuezhang->health);
        if (xiaoming->health <= 0)
        {
            printf("BOSS赢了！\n");
            return;
        }
    }
}