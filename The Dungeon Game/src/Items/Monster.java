package Items;

public class Monster extends Item {
    final int attack, armor, MAX_HITPOINTS;
    private int hitPoints, lvl;
    private boolean fireIm, waterIm, earthIm, windIm, wasCasted;
    
    public Monster(int lvl ,int attack, int armor, int hitPoints, String name, String description, String type) {
        super(name, description, type);
        this.attack = attack;
        this.armor = armor;
        this.hitPoints = hitPoints;
        this.MAX_HITPOINTS = hitPoints;
        this.lvl = lvl;
    }

    public void setImmunities(Boolean fire, Boolean water, Boolean earth, Boolean wind) {
        this.fireIm = fire;
        this.waterIm = water;
        this.earthIm = earth;
        this.windIm = wind;
    }
    
    public void heal() {
        hitPoints=MAX_HITPOINTS;
    }   
    
    public void takeFromHealth(int take) {
        if (take<0) {take=0;}
        hitPoints -= take;
        if (hitPoints > 0) {
            System.out.println("\nYou did " + take + " damage with your last attack. "
                    + getName() + " now has " + hitPoints + " hit points.");
        } else {
            System.out.println("\nYou killed the monster. Congratulations!");
        }
    }
    
    public void setWasCasted(boolean wasCasted) {
        this.wasCasted = wasCasted;
    }
    
    public int castMagic () {
        int temp = (int) (this.hitPoints/2);
        this.hitPoints -= temp;
        return temp;
    }
    
    public int getAttack() {
        return attack;
    }

    public int getLvl() {
        return lvl;
    }
    
    public int getArmor() {
        return armor;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public boolean isFireIm() {
        return fireIm;
    }

    public boolean isWaterIm() {
        return waterIm;
    }

    public boolean isEarthIm() {
        return earthIm;
    }
    
    public boolean isWindIm() {
        return windIm;
    }

    public boolean wasCasted() {
        return wasCasted;
    }
}
