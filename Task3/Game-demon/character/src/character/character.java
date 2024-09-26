package character;

public class character {
    private String name;
    private int health;
    private int attack;
    private double attackSpeed;
    private int armor;

    public character(String name, int health, int attack, double attackSpeed, int armor) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.attackSpeed = attackSpeed;
        this.armor = armor;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
}