#include <stdio.h>

//这是一个基本完成了大部分装备的版本，基本全程使用了github copilot生成，版本号v1.234.0
//prompt基于之前写的最基础版本和试题文件中的装备属性

// 定义装备结构体
typedef struct {
    int attack_bonus;           // 攻击加成
    float life_steal;           // 生命偷取
    int armor_bonus;            // 护甲加成
    int health_bonus;           // 生命值加成
    float attack_speed_bonus;   // 攻击速度加成
    float critical_chance;      // 暴击几率
    float armor_penetration;    // 护甲穿透
    float dodge_chance;         // 闪避几率
    float critical_damage;      // 暴击伤害
    float extra_damage;         // 额外伤害
} Equipment;

// 定义角色结构体
typedef struct {
    int health;                 // 生命值
    int attack_damage;          // 基础攻击力
    float attack_speed;         // 攻击速度
    int armor;                  // 护甲值
    float critical_chance;      // 暴击几率
    float dodge_chance;         // 闪避几率
    Equipment equipment;        // 装备属性加成
} Character;

// 初始化装备
Equipment blood_thirster = {80, 0.15, 0, 0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};             // 饮血剑
Equipment phantom_dancer = {0, 0.0, 0, 0, 0.6, 0.25, 0.0, 0.0, 0.0, 0.0};              // 幻影之舞
Equipment infinity_edge = {80, 0.0, 0, 0, 0.0, 0.25, 0.0, 0.0, 0.4, 0.0};              // 无尽之刃
Equipment ninja_tabi = {0, 0.0, 30, 0, 0.0, 0.0, 0.0, 0.12, 0.0, 0.0};                 // 忍者之靴
Equipment lord_dominiks_regards = {35, 0.0, 0, 0, 0.0, 0.25, 0.35, 0.0, 0.0, 0.0};     // 多米尼克领主的致意
Equipment blade_of_the_ruined_king = {50, 0.0, 0, 0, 0.25, 0.0, 0.0, 0.0, 0.0, 0.01};  // 破败王者之刃

// 计算伤害
int calculate_damage(int attack_damage, int armor, float critical_chance, float armor_penetration, float extra_damage) {
    // 计算护甲穿透后的护甲值
    int effective_armor = armor * (1 - armor_penetration);
    // 计算基础伤害
    int damage = attack_damage / (1 + effective_armor / 100.0);
    // 计算暴击
    if (critical_chance > 0.5) { // 假设暴击几率大于50%时触发暴击
        damage *= 1.75; // 暴击伤害为攻击力的 175%
    }
    // 加上额外伤害
    damage += attack_damage * extra_damage;
    return damage;
}

// 模拟战斗
void fight(Character *xiaoming, Character *BOSShuaixuezhang) {
    while (xiaoming->health > 0 && BOSShuaixuezhang->health > 0) {
        // 小明攻击怪物
        if (xiaoming->equipment.dodge_chance <= 0.5) { // 假设闪避几率小于等于50%时不闪避
            int damage = calculate_damage(
                xiaoming->attack_damage + xiaoming->equipment.attack_bonus,
                BOSShuaixuezhang->armor,
                xiaoming->critical_chance + xiaoming->equipment.critical_chance,
                xiaoming->equipment.armor_penetration,
                xiaoming->equipment.extra_damage
            );
            BOSShuaixuezhang->health -= damage;
            xiaoming->health += damage * xiaoming->equipment.life_steal; // 生命偷取
            printf("小明 对 BOSS 发起了攻击，造成了 %d 点伤害，小明 回复了 %d 点生命值\n", damage, (int)(damage * xiaoming->equipment.life_steal));
        } else {
            printf("BOSS 闪避了小明的攻击\n");
        }
        printf("小明 生命值：%d，BOSS 生命值：%d\n", xiaoming->health, BOSShuaixuezhang->health);
        if (BOSShuaixuezhang->health <= 0) {
            printf("小明胜利\n");
            return;
        }
        // 怪物攻击小明
        if (BOSShuaixuezhang->equipment.dodge_chance <= 0.5) { // 假设闪避几率小于等于50%时不闪避
            int damage = calculate_damage(
                BOSShuaixuezhang->attack_damage,
                xiaoming->armor + xiaoming->equipment.armor_bonus,
                BOSShuaixuezhang->critical_chance,
                BOSShuaixuezhang->equipment.armor_penetration,
                BOSShuaixuezhang->equipment.extra_damage
            );
            xiaoming->health -= damage;
            printf("BOSS 对 小明 发起了攻击，造成了 %d 点伤害\n", damage);
        } else {
            printf("小明 闪避了 BOSS 的攻击\n");
        }
        printf("小明 生命值：%d，BOSS 生命值：%d\n", xiaoming->health, BOSShuaixuezhang->health);
        if (xiaoming->health <= 0) {
            printf("怪物胜利\n");
            return;
        }
    }
}

int main(void) {
    // 初始化角色并装备六件装备
    Equipment combined_equipment = {
        blood_thirster.attack_bonus + phantom_dancer.attack_bonus + infinity_edge.attack_bonus + ninja_tabi.attack_bonus + lord_dominiks_regards.attack_bonus + blade_of_the_ruined_king.attack_bonus,
        blood_thirster.life_steal + phantom_dancer.life_steal + infinity_edge.life_steal + ninja_tabi.life_steal + lord_dominiks_regards.life_steal + blade_of_the_ruined_king.life_steal,
        blood_thirster.armor_bonus + phantom_dancer.armor_bonus + infinity_edge.armor_bonus + ninja_tabi.armor_bonus + lord_dominiks_regards.armor_bonus + blade_of_the_ruined_king.armor_bonus,
        blood_thirster.health_bonus + phantom_dancer.health_bonus + infinity_edge.health_bonus + ninja_tabi.health_bonus + lord_dominiks_regards.health_bonus + blade_of_the_ruined_king.health_bonus,
        blood_thirster.attack_speed_bonus + phantom_dancer.attack_speed_bonus + infinity_edge.attack_speed_bonus + ninja_tabi.attack_speed_bonus + lord_dominiks_regards.attack_speed_bonus + blade_of_the_ruined_king.attack_speed_bonus,
        blood_thirster.critical_chance + phantom_dancer.critical_chance + infinity_edge.critical_chance + ninja_tabi.critical_chance + lord_dominiks_regards.critical_chance + blade_of_the_ruined_king.critical_chance,
        blood_thirster.armor_penetration + phantom_dancer.armor_penetration + infinity_edge.armor_penetration + ninja_tabi.armor_penetration + lord_dominiks_regards.armor_penetration + blade_of_the_ruined_king.armor_penetration,
        blood_thirster.dodge_chance + phantom_dancer.dodge_chance + infinity_edge.dodge_chance + ninja_tabi.dodge_chance + lord_dominiks_regards.dodge_chance + blade_of_the_ruined_king.dodge_chance,
        blood_thirster.critical_damage + phantom_dancer.critical_damage + infinity_edge.critical_damage + ninja_tabi.critical_damage + lord_dominiks_regards.critical_damage + blade_of_the_ruined_king.critical_damage,
        blood_thirster.extra_damage + phantom_dancer.extra_damage + infinity_edge.extra_damage + ninja_tabi.extra_damage + lord_dominiks_regards.extra_damage + blade_of_the_ruined_king.extra_damage
    };

    Character xiaoming = {2000, 100, 1.00, 50, 0.0, 0.0, combined_equipment};
    Character BOSShuaixuezhang = {20000, 200, 1.00, 200, 0.0, 0.0, {0, 0.0, 0, 0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}};
    fight(&xiaoming, &BOSShuaixuezhang);
    return 0;
}