package the.dungeon.game;

import Highscore.IHighscore;
import Highscore.FileHighscore;
import Items.*;

public class Main
{
    public static void main(String[] args){
        Descriptions desc = new Descriptions();
        Item trap1, trap2, trap3, trap4, trap5, dungeonKeeper;
        Monster Monster1, Monster2, Monster3, Monster4, Monster5, Monster6, Monster7;
        Armor Armor1, Armor2, Armor3, Armor4, Armor5, Armor6;
        Weapon knife,staff, sword, axes, spear, hammer, pike;
               
        Room start = new Room(desc.Start());
        start.setGold(100);
        
        Room r3 = new Room(desc.desc3());
        r3.setGold(500);
        r3.setMonster(Monster2 = new Monster(2, 30, 15, 60, "Grumpy Drumpf, The President", desc.monster2(), "Troll"));
        r3.setArmor(Armor2 = new Armor(10, "Helmet of the Dungeon Lord", desc.helmet(), "helmet"));
        
        Room r4 = new Room(desc.desc4());
        r4.setGold(200);
        r4.setTrap(trap1 = new Item("Fire Trap", desc.trap1(), "trap"));
        
        Room r5 = new Room(desc.desc5());
        r5.setGold(500);
        r5.setMonster(Monster1 = new Monster(1,15, 0, 50, "Hillary Clinton", desc.monster1(), "Banshee"));
        r5.setArmor(Armor1 = new Armor(10, "Boots of the Dungeon Lord", desc.boots(), "boots"));
        
        Room r6 = new Room(desc.desc6());
        r6.setGold(200);
        r6.setTrap(trap2 = new Item("Poison Trap", desc.trap2(), "trap"));
        
        Room r7 = new Room(desc.desc7());
        r7.setGold(300);
        
        Room r8 = new Room(desc.desc8());
        r8.setGold(500);
        r8.setMonster(Monster3 = new Monster(3,50,20,100, "Ethe, Spirit of the Dead.", desc.monster3(), "Ghost"));
        r8.setArmor(Armor3 = new Armor(10, "Trousers of the Dungeon Lord", desc.pants(), "trousers"));
        
        Room r9 = new Room(desc.desc9());
        r9.setGold(100);
        r9.setKeeper(dungeonKeeper = new Item("The Dungeon Keeper", desc.dungeonKeeper(), "keeper"));
        
        Room r10 = new Room(desc.desc10());
        r10.setGold(200);
        r10.setTrap(trap3 = new Item("Spike Trap", desc.trap3(), "trap"));
        
        Room r11 = new Room(desc.desc11());
        r11.setGold(200);
        r11.setTrap(trap4 = new Item("Draining Trap", desc.trap4(), "trap"));
        
        Room r12 = new Room(desc.desc12());
        r12.setGold(200);
        r12.setTrap(trap5 = new Item("Water Trap", desc.trap5(), "trap"));
        
        Room r13 = new Room(desc.desc13());
        r13.setGold(500);
        r13.setMonster(Monster5 = new Monster(5,85,40,180, "Olius, Ruler of Darkness.", desc.monster5(), "Void"));
        r13.setArmor(Armor5 = new Armor(10, "Buckler of the Dungeon Lord", desc.shield(), "buckler"));
        Monster5.setImmunities(true, false, true, false);
        
        Room r14 = new Room(desc.desc14());
        r14.setGold(300);
        
        Room r15 = new Room(desc.desc15());
        r15.setGold(400);
        
        Room r16 = new Room(desc.desc16());
        r16.setGold(500);
        r16.setMonster(Monster4 = new Monster(4,70,30,125, "Billog, Left Hand of Jikaos.", desc.monster4(), "Cyclops"));
        r16.setArmor(Armor4 = new Armor(10, "Cape of the Console Peasant", desc.cape(), "cape"));
        Monster4.setImmunities(true, true, false, false);
        
        Room r17 = new Room(desc.desc17());
        r17.setGold(300);
        
        Room r18 = new Room(desc.desc18());
        r18.setGold(400);
        
        Room r19 = new Room(desc.desc9());
        r19.setGold(500);
        r19.setMonster(Monster6 = new Monster(6,105,50,210, "Al’Akir, Lord of the Wind", desc.monster6(), "Djinn"));
        r19.setArmor(Armor6 = new Armor(50, "Breastplate of the Dungeon Lord", desc.bodyarmor(), "bodyarmor"));
        Monster6.setImmunities(true, false, false, true);
        
        Room r20 = new Room(desc.desc2());
        r20.setMonster(Monster7 = new Monster(7,150,100,400, "Jikaos, the Brutal.", desc.monster7(), "Giant"));
        Monster7.setImmunities(false, true, true, true);
        
        Room endGame = new Room(desc.endGame());
        
        start.connectNorth(r6);
        start.connectWest(r4);
        r4.connectWest(r5);
        r6.connectNorth(r9);
        r6.connectEast(r7);
        r3.connectNorth(r7);
        r7.connectEast(r8);
        r9.connectEast(r10);
        r10.connectNorth(r14);
        r10.connectSouth(r7);
        r11.connectNorth(r17);
        r9.connectNorth(r12);
        r12.connectEast(r14);
        r14.connectEast(r15);
        r9.connectWest(r11);
        r11.connectWest(r19);
        r14.connectEast(r15);
        r15.connectNorth(r16);
        r12.connectNorth(r13);
        r12.connectWest(r17);
        r17.connectWest(r18);
        r18.connectNorth(r20);
        r20.connectNorth(endGame);
        
        r17.getItemList().add(new Key("Bronze Key", desc.key1(), "bronzekey"));
        r18.getItemList().add(new Key("Silver Key", desc.key2(), "silverkey"));
        r3.getItemList().add(new Key("Gold Key", desc.key3(), "goldkey"));
        r14.getItemList().add(new Key("Platinum Key", desc.key4(), "platinumkey"));
        r15.getItemList().add(new Key("Diamond Key", desc.key5(), "diamondkey"));
        r7.getItemList().add(new Key("Master Key", desc.key6(), "masterkey"));
        
        r5.setKeyType("bronzekey");
        r3.setKeyType("silverkey");
        r16.setKeyType("goldkey");
        r8.setKeyType("platinumkey");
        r19.setKeyType("diamondkey");
        r13.setKeyType("masterkey");
        
        start.getItemList().add(knife = new Weapon(1, "Rusty knife", "Does basic dmg.", "knife"));
        r5.getItemList().add(staff = new Weapon(10, "Staff of Butthurt", desc.staff(), "staff"));
        r3.getItemList().add(sword = new Weapon(20, "Zweihander", desc.sword(), "sword"));
        r8.getItemList().add(axes = new Weapon(30, "Last Wish & Dying Breath", desc.axe(), "axes"));
        r16.getItemList().add(hammer = new Weapon(40, "Core of the Earth", desc.hammer(), "hammer"));
        r13.getItemList().add(spear = new Weapon(50, "Javelin of the Thousand Tales", desc.spear(), "spear"));
        r19.getItemList().add(pike = new Weapon(100, "Poseidon’s Pike", desc.pike(), "pike"));
        
        IHighscore highscore = new FileHighscore("highscore.txt");

        Game game = new Game(start, highscore, endGame);
        game.start();
        game.run();
        
        System.out.println(desc.exitMessage());
    }
}
