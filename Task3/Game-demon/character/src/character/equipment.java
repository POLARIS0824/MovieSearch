package character;

public class Equipment {
    private String name;
    private int attackBoost;
    private double attackSpeedBoost;

    public Equipment(String name, int attackBoost, double attackSpeedBoost) {
        this.name = name;
        this.attackBoost = attackBoost;
        this.attackSpeedBoost = attackSpeedBoost;
    }

    public String getName() {
        return name;
    }

    public int getAttackBoost() {
        return attackBoost;
    }

    public double getAttackSpeedBoost() {
        return attackSpeedBoost;
    }

    public static void applyEquipment(Character character, Equipment equipment) {
        character.setAttack(character.getAttack() + equipment.getAttackBoost());
        character.setAttackSpeed(character.getAttackSpeed() + equipment.getAttackSpeedBoost());
    }
}
