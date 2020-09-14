package Items;

import the.dungeon.game.Player;
import the.dungeon.game.Room;
import Items.Item;
import Items.Key;

public class TestMS3 {
    
    public static void main(String[] args) {
        Item trap;
        
        Room r1 = new Room("Room1");
        r1.setGold(10);
        Room r2 = new Room("Room2");
        r2.setGold(20);
        Room r3 = new Room("Room3");
        r3.setGold(30);
        r3.getItemList().add(new Key("Key 1", "KeyDesc", "key1"));
        Room r4 = new Room("Room4");
        r4.setGold(40);
        r4.setTrap(trap = new Item("Trap1", "DescTrap", "bla"));
        Room end = new Room("Endroom");
        
        r1.connectNorth(r2);
        r2.connectEast(r3);
        r3.connectSouth(r4);
        r4.connectSouth(end);
        
        r3.getItemList().add(new Key("Key 1", "KeyDesc", "key1"));
        end.setKeyType("key1");
        
        
        Player player = new Player(r1, end);
        
        System.out.println(player.getCurrentRoom().getDescription());
        player.addGold();
        player.goNorth();
        System.out.println(player.getCurrentRoom().getDescription());
        player.addGold();
        player.goEast();
        System.out.println(player.getCurrentRoom().getDescription());
        player.addGold();
        player.pickup("Key 1");
        player.showInventory();
        player.goSouth();
        System.out.println(player.getCurrentRoom().getDescription());
        player.addGold();
        player.goSouth();
        System.out.println(player.getCurrentRoom().getDescription());
        
        
        
        
        
    }
    
}
