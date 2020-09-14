package Items;

public class Weapon extends Item {
    final int attackDmg;

    public Weapon(int attackDmg, String name, String description, String type) {
        super(name, description, type);
        this.attackDmg = attackDmg;
    }
    
    public int getDmg() {
        return attackDmg;
    }
}
