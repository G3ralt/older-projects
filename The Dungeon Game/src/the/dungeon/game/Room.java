package the.dungeon.game;

import Items.Item;
import java.util.ArrayList;
import java.util.List;
import Items.*;

public class Room
{
    private final String description; 
    private Room north, east, south, west;
    private Armor armor;
    private Monster monster;
    private int gold;
    private String keyType;
    private List<Item> itemList;
    public boolean sawDesc, sawMess, sawItemList;
    private Item trap, dungeonKeeper;
    
    public Room(String description) {
        if(description == null) {
            throw new NullPointerException("description may not be null");
        }
        this.description = description;
        itemList = new ArrayList<>();
    }
    
    public void roomItemDesc() {
        if(getItemList().size() > 0 && !sawItemList)
        {
            System.out.println("\nAfter searching the room you found a treasure chest with the following items:");
                    
        for(Item item : getItemList())
            {
                System.out.println("\n* " + item.getName() + " *\t -> " + item.getDescription());
            }
        sawItemList = true;
        }
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public void clearKey() {
        this.keyType = null;
    }
    
    public void clearMonster() {
        this.monster=null;
    }
    
    public void setKeeper(Item keeper) {
        this.dungeonKeeper = keeper;
    }

    public void setTrap(Item trap) {
        this.trap = trap;
    }

    public void disableTrap() {
        this.trap = null;
    }
    
    public void setGold(int gold) {
        this.gold = gold;
    }
    
    public void connectNorth(Room other) {
        other.south = this;
        this.north = other;
    }
    
    public void connectEast(Room other) {
        other.west = this;
        this.east = other;
    }
    
    public void connectSouth(Room other) {
        other.north = this;
        this.south = other;
    }
    
    public void connectWest(Room other) {
        other.east = this;
        this.west = other;
    }
    
    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }
    
    public String getKeyType() {
        return keyType;
    }   

    public List<Item> getItemList() {
        return itemList;
    }
    
    public Monster getMonster() {
        return monster;
    }
    
    public Armor getArmor() {
        return armor;
    }
    
    public Item getDungeonKeeper() {
        return dungeonKeeper;
    }
    
    public Item getTrap() {
        return trap;
    }
    
    public String getDescription() {
        return description;
    }

    public Room getNorth() {
        return north;
    }

    public Room getEast() {
        return east;
    }

    public Room getSouth() {
        return south;
    }

    public Room getWest() {
        return west;
    }

    public int getGold() {
        return gold;
    }
}
