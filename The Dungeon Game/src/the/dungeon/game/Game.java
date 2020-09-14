package the.dungeon.game;

import java.util.Scanner;
import Highscore.IHighscore;
import Highscore.HighscoreEntry;

public class Game {
    Descriptions desc = new Descriptions();
    private final Player player;
    private final Commands cmdInt;
    private final Scanner scanner;
    private final IHighscore highscore;
    
    public Game(Room startRoom, IHighscore highscore, Room goalroom) {
        player = new Player(startRoom, goalroom);
        cmdInt = new Commands(player);
        scanner = new Scanner(System.in);
        this.highscore = highscore;
    }
    
    public void start() {
        System.out.println("Welcome to The Witchers`s Dungeon!");
        player.setName();
        player.rules();
        System.out.println("\nAre you ready to enter the dungeon? (press enter or any key and enter)");
        scanner.nextLine();
    }
    
    public void run() {
        while(player.getHealth() > 0 || player.getEnergy() > 0) {                
            if (player.hasWon()) {
                player.getDescription();
                System.out.println("\nYou look into your pockets and you find " + player.getGold() + " gold.\nCongratulations! You managed to escape The Witcher`s Dungeon!");
                highscore.addScore(player.getName(), player.getGold());
                System.out.println("\nHere is the highscore:\n");
               
                for(HighscoreEntry entry : highscore.getTopTen())
                {
                    System.out.println(entry.getName() + " --> " + entry.getScore());
                }   
                return;
            }
            
            player.getDescription();
            player.roomGold();
            player.getCurrentRoom().roomItemDesc();
            player.chooseAction();    
            cmdInt.interpretDir(scanner.nextLine().trim());
        }
    }    
}
