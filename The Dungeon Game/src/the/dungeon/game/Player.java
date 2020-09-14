package the.dungeon.game;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import Items.*;

public class Player {
    Descriptions rul = new Descriptions();
    Scanner scan = new Scanner(System.in);
    private int lvl = 0;
    private Room currentRoom, tempRoom;
    private final Room goalRoom;
    private Weapon currentWeapon;
    private boolean hasStaff;
    private int gold, energy = 100, health = 100, attackDmg = 10, armor = 10;
    private String name;
    private final List<Item> inventory;
    private final List<Armor> armorList;
    private final int MAX_HEALTH = 100, MAX_ENERGY = 100, MANA_REQ = 50;
    HealthPotion HPotion = new HealthPotion("", "", "");
    EnergyPotion EPotion = new EnergyPotion("", "", "");
    private final Commands fightInt;
    
    
    public Player(Room startRoom, Room goalroom) {
        this.currentRoom = startRoom;
        this.gold = 0;
        this.inventory = new ArrayList<>();
        this.goalRoom = goalroom;
        fightInt = new Commands(this);
        this.armorList = new ArrayList<>();
    }
    
    public void rules() {
        System.out.println("\nDo you want to see the rules? (y)");
        String temp = scan.next();
        if (temp.equalsIgnoreCase("y")) {
            System.out.println(rul.rules());
        }
    }

    void energyHeal() {
        energy += EPotion.getEnergyBoost();
        if (energy>MAX_ENERGY) {
            energy = MAX_ENERGY;
            System.out.println("\nYou used an Energy Potion.");
        }
    }
    
    void heal() {
        health += HPotion.getHpBoost();
        if (health>MAX_HEALTH) {
            health = MAX_HEALTH;
            System.out.println("\nYou used a Health Potion.");
        }
    }

    void takeEnergy(int take) {
        energy -= take;
        if (energy<=0) {
            System.out.println(rul.outOfEnergy());
            System.out.println(rul.exitMessage());
            System.exit(0);
        }
    }
    
    void takeHealth(int take) {
        if (take<0) {take=0;}
        health-=take;
        System.out.println("\n" + currentRoom.getMonster().getName() + " did " + take + " damage to " + name + ".");
        if (health<=0) {
            System.out.println("\nUnfortunately you lost all your health. Game Over!");
            rul.exitMessage();
            System.exit(0);
        }
    }

    public void getDescription() {
        if (!currentRoom.sawDesc) {
            System.out.println(currentRoom.getDescription());
            currentRoom.sawDesc = true;
            explore();
        }
        if (currentRoom.getItemList().isEmpty() && currentRoom.getGold()==0 && !currentRoom.sawMess) {
            System.out.println("\nYou have already been here and there is nothing interesting in the room.");
            currentRoom.sawMess = true;
        }
    }
    
    public void explore() {
        if(currentRoom.getTrap()!=null || currentRoom.getDungeonKeeper()!=null || currentRoom.getMonster()!=null || !currentRoom.getItemList().isEmpty()) {
            System.out.println("\nPress enter or any key and enter to explore the room.");
            String temp1 = scan.nextLine();
        }
        if (currentRoom.getTrap()!=null) {
            System.out.println("\nOH NO! IT`s A TRAP:\n\n --- " + currentRoom.getTrap().getName() + " ---" + "\n" + currentRoom.getTrap().getDescription());
            System.out.println("\nYou disabled the trap but it took you 10 energy. Press enter or any key and enter to continue.");
            String temp = scan.nextLine();
            takeEnergy(10);
            currentRoom.disableTrap();
        }
        if (currentRoom.getDungeonKeeper()!=null) {
            System.out.println("\nYou found " + currentRoom.getDungeonKeeper().getName() + " in this room." + currentRoom.getDungeonKeeper().getDescription());
            currentRoom.sawMess = true;
        }
        if (currentRoom.getMonster()!=null) {
            System.out.println("\nA monster occupies this Room:\n"
                                + "\n+++ " + currentRoom.getMonster().getName().toUpperCase() + " " + currentRoom.getMonster().getType() + " +++"
                                + "\n" + currentRoom.getMonster().getDescription());
            fight();
        }
    }
    
    public void roomGold() {        
        if (currentRoom.getGold() > 0) {
            System.out.println("\nWow " + getName() + "! You found " + currentRoom.getGold() + " gold in this room.");
            addGold();
            currentRoom.sawMess = true;
        }
    }
    
    public void addGold() {
        int roomgold = currentRoom.getGold();
        currentRoom.setGold(0);
        gold += roomgold;
    }
    
    public void go(String dir) {
        switch(dir.toLowerCase()) {
            case "north":
                goNorth();
                break;
            case "east":
                goEast();
                break;
            case "south":
                goSouth();
                break;
            case "west":
                goWest();
                break;
            default:
                System.out.println("\nThere is no such direction: " + dir + ".");
        }
    }
    
    public void goBack() {
        currentRoom.getMonster().heal();
        currentRoom = tempRoom;
        System.out.println("\nYou`re going back to the previous room.");
        String str = scan.nextLine();
    }
    
    void changeRoom() {
        tempRoom = currentRoom;
        currentRoom.sawDesc = false;
        currentRoom.sawMess = false;
        currentRoom.sawItemList = false;
    }
    
    public void goNorth() {
        if(currentRoom.getNorth() == null) {
            closedPath();
        }
        else if (currentRoom.getNorth().getKeyType() == null) {
            openPath();
            changeRoom();
            currentRoom = currentRoom.getNorth();
        }
        else if (hasKey(currentRoom.getNorth().getKeyType())) {
            unlockedRoom();
            changeRoom();
            currentRoom = currentRoom.getNorth();
            currentRoom.clearKey();
        } else {
            roomNeedsKey(currentRoom.getNorth());
        }
    }
    
    public void goEast() {
        if(currentRoom.getEast() == null) {
            closedPath();
        }
        else if (currentRoom.getEast().getKeyType() == null) {
            openPath();
            changeRoom();
            currentRoom = currentRoom.getEast();
        }
        else if (hasKey(currentRoom.getEast().getKeyType())) {
            unlockedRoom();
            changeRoom();
            currentRoom = currentRoom.getEast();
            currentRoom.clearKey();
        } else {
            roomNeedsKey(currentRoom.getEast());
        }
    }
    
    public void goSouth() {
        if(currentRoom.getSouth() == null) {
            closedPath();
        }
        else if (currentRoom.getSouth().getKeyType() == null) {
            openPath();
            changeRoom();
            currentRoom = currentRoom.getSouth();
        }
        else if (hasKey(currentRoom.getSouth().getKeyType())) {
            unlockedRoom();
            changeRoom();
            currentRoom = currentRoom.getSouth();
            currentRoom.clearKey();
        } else {
            roomNeedsKey(currentRoom.getSouth());
        }
    }
    
    public void goWest() {
        if(currentRoom.getWest() == null) {
            closedPath();
        }
        else if (currentRoom.getWest().getKeyType() == null) {
            openPath();
            changeRoom();
            currentRoom = currentRoom.getWest();
        }
        else if (hasKey(currentRoom.getWest().getKeyType())) {
            unlockedRoom();
            changeRoom();
            currentRoom = currentRoom.getWest();
            currentRoom.clearKey();
        } else {
            roomNeedsKey(currentRoom.getWest());
        }
    }
    
    private boolean hasKey(String keyType) {
        for(Item item : inventory)
        {
            if(item instanceof Key)
            {
                Key key = (Key) item;
                if(key.getType().equals(keyType))
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    private void openPath() {
        System.out.println("\nThis path is open. You are proceeding to the next room. Press enter or any key and enter when you are ready to continue.");
        String temp = scan.nextLine();
        takeEnergy(1);
    }
    
    private void closedPath() {
        System.out.println("\nThis path is blocked. Please, choose another direction.");
    }
    
    private void roomNeedsKey(Room room) {
        System.out.println("\nThis path is open but you need to find the " + room.getKeyType() + " to unlock the door.");
    }
    
    private void unlockedRoom() {
        System.out.println("\nYou unlocked the door with the right key and you are now proceeding to the next room. Press enter or any key and enter when you are ready to continue.");
        String temp = scan.nextLine();
        takeEnergy(1);
    }
    
    public void showArmor() {
        if (armorList.isEmpty()) {
            System.out.println("\nYou have no equiped armor.");
        } else {
            System.out.println("\nYou have equiped:\n");
            for(Armor next : armorList) {
                System.out.println("~~~~ " + next.getName() + " ~~~~" + "\t" + next.getDescription());
            }
        }
    }
    
    public void showInventory() {
        if(inventory.isEmpty()) {
            System.out.println("\nYour backpack is empty.");
        } else {
            System.out.println("\nYou have the following items:\n");
            for(Item item : inventory) {
                System.out.println("/!\\ " + item.getName() + " /!\\" + "\t< " + item.getType() + " >");
            }
        }
    }
    
    public void pickup(String name) {
        String temp = name.toLowerCase();
        Item toPick = null;
        for(Item item : currentRoom.getItemList()) {
            if(item.getType().toLowerCase().equals(temp)) {
                toPick = item;
                break;
            }
        }
        if(toPick != null) {
            inventory.add(toPick);
            currentRoom.getItemList().remove(toPick);
            System.out.println("\nNow you have " + toPick.getName() + " in your inventory.");
        } else {
            System.out.println("\nItem \"" + name + "\" does not exist in this room.");
        }
    } 
    
    public void drop(String item) {
        String temp = item.toLowerCase();
        Item toDrop = null;
        for(Item thing : inventory) {
            if(thing.getType().toLowerCase().equals(temp)) {
                toDrop = thing;
                break;
            }
        }
        if (toDrop != null) {
            currentRoom.getItemList().add(toDrop);
            inventory.remove(toDrop);
            System.out.println("\nYou dropped " + toDrop.getName() + ".");
        } else {
            System.out.println("\nThere is no such item in your backpack.");
        }
    }
    
    public void usePotion(String item) {
        Item toUse = null;
        for(Item random : inventory) {
            if(random.getType().toLowerCase().equals(item.toLowerCase())) {
                toUse = random;
                break;
                }
        }
        if (toUse!=null) {
            switch (toUse.getType()) {
                case "healthpotion":
                    heal();
                    inventory.remove(toUse);
                    break;
                case "energypotion":
                    energyHeal();
                    inventory.remove(toUse);
                    break;
                default:
                    System.out.println("\nYou cannot use that now.");
                    break;
            }               
        } else {
            System.out.println("\nThere is no  " + item + " in your invertory.");
        }
    }
    
    public void buy(String str) {
        if (gold>=EPotion.getCost()) {
            switch (str.toLowerCase()) {
                case "healthpotion":
                    HealthPotion newHealthPotion = new HealthPotion("Health Potion", "Heals 50 hit points.", "healthpotion");
                    gold -= newHealthPotion.getCost();
                    inventory.add(newHealthPotion);
                    System.out.println("\nYou bougth a Health Potion.");
                    break;
                case "energypotion":
                    EnergyPotion newEnergyPotion = new EnergyPotion("Energy Potion", "Regenerates 50 energy.", "energypotion");
                    gold -= newEnergyPotion.getCost();
                    inventory.add(newEnergyPotion);
                    System.out.println("\nYou bougth an Energy Potion.");
                    break;            
                default:
                    System.out.println("\nOnly potions are for sale.");
                    break;
            }
        } else {
                System.out.println("\nYou can buy only potions and you don`t have enough money.");
        }
    }
    
    void fight() {
        while (currentRoom.getMonster()!=null && currentRoom.getMonster().getHitPoints()>0 && tempRoom!=currentRoom) {    
            chooseAction();
            String action = scan.nextLine();
            fightInt.interpretFight(action);
        }
        if (currentRoom.getMonster()!=null && currentRoom.getMonster().getHitPoints()<=0) {
            System.out.println("\nAs it seems " + currentRoom.getMonster().getName() + " dropped some loot."
                    + "\nYou come closer and find:" + "\n/!\\ " + currentRoom.getArmor().getName() + " /!\\"
                    + "\t" + currentRoom.getArmor().getDescription() + "\n"
                    + "\nYou equip yourself with your new shining armor and continue your journey.");
            armorList.add(currentRoom.getArmor());
            armor += currentRoom.getArmor().getArmor();
            lvl = currentRoom.getMonster().getLvl();
            armor += 5;
            attackDmg +=5;
            currentRoom.clearMonster();
            String str = scan.nextLine();
        }
    }
    
    public void attackWith(String weapon) {
        Item toAttackWith = null;
        for(Item thing : inventory) {
            if(thing.getType().toLowerCase().equals(weapon.toLowerCase())) {
                toAttackWith = thing;
                break;
            }
        }
        if (toAttackWith!=null) {
            if (toAttackWith instanceof Weapon) {
                currentWeapon = (Weapon) toAttackWith;
                attack();
            } else {
                System.out.println("\n" + toAttackWith.getName() + " is not a weapon.");
            }
        } else {
            System.out.println("\nThere is no item in your backpack.");
        }
    }
    
    void attack () {
        currentRoom.getMonster().takeFromHealth(attackDmg + currentWeapon.getDmg() - currentRoom.getMonster().getArmor());
        if (currentRoom.getMonster().getHitPoints()>0) {
            System.out.println("\nPress enter or any key and enter to defend.");
            String temp = scan.nextLine();
            takeHealth(currentRoom.getMonster().getAttack() - armor);
        }        
    }
    
    public void cast(String magic) {
        if (energy>MANA_REQ) {
            if (!currentRoom.getMonster().wasCasted()) {
                energy-=MANA_REQ;
                switch (magic.toLowerCase()) {
                    case "fire":
                        if (!currentRoom.getMonster().isFireIm()) {
                            System.out.println("\nYour Fire Inferno did " + currentRoom.getMonster().castMagic() + " damage to "
                                    + currentRoom.getMonster().getName() + ".");
                        } else {
                            System.out.println("\n" + currentRoom.getMonster().getName() + " is Fire immune and your magic did 0 dmg.");
                        } 
                        currentRoom.getMonster().setWasCasted(true);
                        break;
                    case "water":
                        if (!currentRoom.getMonster().isWaterIm()) {
                            System.out.println("\nYour Water Wave did " + currentRoom.getMonster().castMagic() + " damage to "
                                    + currentRoom.getMonster().getName() + ".");
                        } else {
                            System.out.println("\n" + currentRoom.getMonster().getName() + " is Water immune and your magic did 0 dmg.");
                        } 
                        currentRoom.getMonster().setWasCasted(true);
                        break;
                    case "earth":
                        if (!currentRoom.getMonster().isEarthIm()) {
                            System.out.println("\nYour Earthquake did " + currentRoom.getMonster().castMagic() + " damage to "
                                    + currentRoom.getMonster().getName() + ".");
                        } else {
                            System.out.println("\n" + currentRoom.getMonster().getName() + " is Earth immune and your magic did 0 dmg.");
                        } 
                        currentRoom.getMonster().setWasCasted(true);
                        break;
                    case "wind":
                        if (!currentRoom.getMonster().isWindIm()) {
                            System.out.println("\nYour Tornado did " + currentRoom.getMonster().castMagic() + " damage to "
                                    + currentRoom.getMonster().getName() + ".");
                        } else {
                            System.out.println("\n" + currentRoom.getMonster().getName() + " is Wind immune and your magic did 0 dmg.");
                        } 
                        currentRoom.getMonster().setWasCasted(true);
                        break;
                } 
            } else {
                    System.out.println("\nYou can`t cast anymore magic to this monster.");
            }   
        } else {
            System.out.println("\nYou don`t have enough energy.");
        }    
    }
    
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public List<Armor> getArmorList() {
        return armorList;
    }
    
    public boolean hasStaff() {
        for(Item item : inventory) {
            if(item.getType().toLowerCase().equals("staff")) {
                hasStaff = true;
                break;
            }
        }    
        return hasStaff;
    }

    public int getLvl() {
        return lvl;
    }
    
    public Room getTempRoom() {
        return tempRoom;
    }
    
    public int getEnergy() {
        return energy;
    }
    
    public int getHealth() {
        return health;
    }

    public int getAttackDmg() {
        return attackDmg;
    }

    public int getArmor() {
        return armor;
    }
    
    public int getGold() {
        return gold;
    }
    
    public void setName () {
        System.out.println("\nWhat is your name?");
        name = scan.next();
        System.out.println("\nHello " + name + "!\n");
    }

    public String getName() {
        return name;
    }
    
    public void chooseAction() {
        System.out.println("\nWhat is your next action?");
        System.out.print(">");
    }
    
    public boolean hasWon() {
        return (currentRoom == goalRoom);
    }
}
