package GameLogic;

import java.util.Scanner;

/**
 *
 * @author Alex
 */
public class BettingStars {
    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        Deck deck = new Deck();
        
        Player player = new Player("Player");
        Player dealer = new Player("Dealer");
        
        player.addCard(deck.deal());
        dealer.addCard(deck.deal());
        player.addCard(deck.deal());
        dealer.addCard(deck.deal());
        
        System.out.println("Cards dealt:");
        player.printHand(true);
        dealer.printHand(false);
        System.out.println("\n");
        
        boolean playerDone = false, dealerDone = false;
        String str;
        
        while(!playerDone || !dealerDone) {
            if (!playerDone) {
                System.out.println("Hit or Stay? H/S");
                str = scan.next();
                System.out.println("");
                
                if(str.compareToIgnoreCase("h") == 0) {
//                    playerDone = !player.addCard(deck.deal());
                    player.printHand(true);
                } else {
                    playerDone = true;
                }
            }
            
            if (!dealerDone) {
                if (dealer.handSum() < 17) {
                    System.out.println("The Dealer Hits");
//                    dealerDone = !dealer.addCard(deck.deal());
                    dealer.printHand(false);
                } else {
                    System.out.println("The Dealer stays\n");
                    dealerDone = true;
                }
            }
            System.out.println("");
        }
        scan.close();
        
        player.printHand(true);
        dealer.printHand(true);
        
        int playerSum = player.handSum();
        int dealerSum = dealer.handSum();
        
        if (playerSum > dealerSum && playerSum <= 21 || dealerSum > 21) {
            System.out.println("You win!");
        } else {
            System.out.println("Dealer wins!");
        }
    }
    
}
