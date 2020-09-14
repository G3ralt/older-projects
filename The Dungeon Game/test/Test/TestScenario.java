package Test;

import the.dungeon.game.*;
import Items.*;

public class TestScenario {
    public static void main(String[] args){
        Item keeper;
        Weapon weapon;
        Monster Monster1;
        Armor Armor1;
        Item trap1;
        
        Room start = new Room("starting room");
        Room r2 = new Room("Second Room");
        Room r3 = new Room("Third Room");
        Room exit = new Room("Exit Room");
    
        start.connectNorth(r2);
        r2.connectEast(r3);
        r3.connectSouth(exit);
        
        start.setGold(1000);
        
        start.setTrap(trap1 = new Item("Fire Trap", "DESC TRAP", "trap"));
        
        r2.setKeeper(keeper = new Item("dungeon keeper", "DESC", "keeper"));
        r2.getItemList().add(weapon = new Weapon(1, "Rusty knife", "Does basic dmg.", "knife"));
        
        r3.setMonster(Monster1 = new Monster(1,15, 0, 50, "Hillary Clinton", "MON DESC", "Banshee"));
        r3.setArmor(Armor1 = new Armor(10, "Boots of the Dungeon Lord", "armor Desc", "boots"));
        
        Player player = new Player(start, exit);
        
        player.explore();
        System.out.println(player.getGold());
        player.addGold();
        System.out.println(player.getGold());
        player.goNorth();
        player.explore();
        player.showInventory();
        player.pickup("knife");
        player.buy("healthpotion");
        player.showInventory();
        player.drop("healthpotion");
        player.buy("energypotion");
        player.goEast();
        while (player.getCurrentRoom().getMonster().getHitPoints()>0) {
            player.attackWith("knife");
        }
        System.out.println(player.getHealth());
        System.out.println(player.getEnergy());
        player.usePotion("energypotion");
        System.out.println(player.getEnergy());
        player.showInventory();
        player.goSouth();
        player.explore();
        
    }
}
