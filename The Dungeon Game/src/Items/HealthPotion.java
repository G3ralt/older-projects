package Items;

public class HealthPotion extends Item {
    final int hpBoost = 50, cost = 200;
    
    public HealthPotion(String name, String description, String type) {
        super(name, description, type);
    }

    public int getHpBoost() {
        return hpBoost;
    }

    public int getCost() {
        return cost;
    }
}
