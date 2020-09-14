package the.dungeon.game;

import Items.Item;

public class Commands
{
    private final Player player;
    Descriptions xyz = new Descriptions(); 

    public Commands(Player player) {
        this.player = player;
    }
    
    public void interpretDir(String cmd) {
        String[] command = cmd.split("\\s+");
                
        switch (command.length) {
            case 1:
                switch(command[0].toLowerCase()) {
                    
                    case "quit":
                        System.out.println(xyz.exitMessage());
                        System.exit(0);
                        
                    case "help":
                        System.out.println("\nAvailable commands:\n-> Go (north, south, east, west)\n-> Quit - Quits the game."
                                + "\n-> Items - shows your inventory.\n-> Stats - information about your character.\n-> Drop (item)."
                                + "\n-> Use (potion)");
                        if (player.getCurrentRoom().getItemList().size() > 0) {
                            System.out.println("-> Pickup :");
                            for(Item item : player.getCurrentRoom().getItemList()) {
                                System.out.println("\n* " + item.getName() + " *\t< " + item.getType() + " >");
                            }
                        }    
                        if (player.getCurrentRoom().getDungeonKeeper()!=null) {
                            System.out.println("-> Buy:\n\t< lifepotion >\n\t< energypotion >");                        
                        }
                        break;
                        
                    case "stats":
                        System.out.println("\nYou have " + player.getGold() + " gold!"
                                + "\nYou have " + player.getHealth() + " hit points."
                                + "\nYou have " + player.getEnergy() + " energy left."
                                + "\nAttack power: " + player.getAttackDmg() + " damage." + "\nDefensive power: " + player.getArmor() + " armor.");
                        break;
                        
                    case "items":
                        player.showInventory();
                        player.showArmor();
                        break;
                        
                    default:
                        System.out.println("\nUnknown command: " + cmd + ".");
                }
                break;
            case 2:
                switch(command[0].toLowerCase()) {
                    case "use":
                        if (!player.getInventory().isEmpty()) {
                            player.usePotion(command[1]);
                        } else {
                            System.out.println("\nYou don`t have any items in your backpack.");
                        }  
                        break;
                    
                    case "buy":
                        if (player.getCurrentRoom().getDungeonKeeper()!=null) {
                            player.buy(command[1]);
                        } else {System.out.println("\nYou can`t buy in this room.");}
                        break;
                    
                    case "go":
                        player.go(command[1]);
                        break;
                        
                    case "pickup":
                        player.pickup(command[1]);
                        break;
                        
                    case "drop":
                        if (!player.getInventory().isEmpty()) {
                            player.drop(command[1]);
                        } else {
                            System.out.println("\nYou don`t have any items to drop.");
                        }  
                        break;
                    
                    default:
                        System.out.println("\nUnknown command: " + cmd + ".");

                }
                break;
            default:
                System.out.println("\nUnknown command: " + cmd + ".");
                break;
        }
                
    }
    
    public void interpretFight(String act) {
        String[] command = act.split("\\s+");
        switch (command.length) {
            case 1:
                switch (command[0].toLowerCase()) {
                    case "help":
                        System.out.println("\nAvailable commands:\n-> Escape - go back to he previous room.\n-> Quit - Quits the game.\n"
                                + "-> Items - shows your inventory.\n-> Stats - information about your character.\n"
                                + "-> Use (potion)\n-> Attackwith (weapon)");
                        if (player.hasStaff()) {
                            System.out.println("-> Cast (fire, water, earth, wind)");
                        }
                        break;
                    
                    case "quit":
                        System.out.println(xyz.exitMessage());
                        System.exit(0);
                        break;
                    
                    case "escape":
                        player.goBack();
                        break;
                    
                    case "stats":
                        System.out.println("\nYou are level: " + player.getLvl() + "."
                                         + "\nYou have " + player.getHealth() + " hit points."
                                         + "\nYou have " + player.getEnergy() + " energy left."
                                         + "\nAttack power: " + player.getAttackDmg() + " damage." 
                                         + "\nDefensive power: " + player.getArmor() + " armor.");
                        break;
                    
                    case "items":
                        player.showInventory();
                        player.showArmor();
                        break;
                        
                    default:
                        System.out.println("\nUnknown command: " + act);
                
                } break;
            
            case 2:
                switch (command[0].toLowerCase()) {
                    case "use":
                        if (!player.getInventory().isEmpty()) {
                            player.usePotion(command[1]);
                        } else {
                            System.out.println("\nYou don`t have any items in your backpack.");
                        }
                        break;
                        
                    case "attackwith":
                        if (!player.getInventory().isEmpty()) {
                            player.attackWith(command[1]);
                        } else {
                            System.out.println("\nYou don`t have any weapons in your backpack.");
                        }
                        break;
                        
                    case "cast":
                        if (player.hasStaff()) {
                            player.cast(command[1]);
                        } else {
                            System.out.println("\nYou don`t have the staff and you cant cast any magic.");
                        }
                        break;
                        
                    default:
                        System.out.println("\nUnknown command: " + act);
                } break;
                
            default:
                System.out.println("\nUnknown command: " + act);
        }
    }
}
    

