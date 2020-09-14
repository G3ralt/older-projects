package Items;

public class EnergyPotion extends Item {
    final int energyBoost = 50, cost = 200;
    
    public EnergyPotion(String name, String description, String type) {
        super(name, description, type);
    }

    public int getEnergyBoost() {
        return energyBoost;
    }

    public int getCost() {
        return cost;
    }
}
