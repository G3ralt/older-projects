package Items;

public class Armor extends Item {
    final int armor;

    public Armor(int armor, String name, String description, String type) {
        super(name, description, type);
        this.armor = armor;
    }

    public int getArmor() {
        return armor;
    }
}
