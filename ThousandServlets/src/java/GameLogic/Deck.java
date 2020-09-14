package GameLogic;

import java.util.Random;

public class Deck {
   
    private Card[] cards; //Array of cards
    private int numCards;
    
    
    
    public Deck() {
        this(1, true);
    }
 
    public Deck(int numDecks, boolean shuffle) {
        this.numCards = numDecks * 52;
        this.cards = new Card[this.numCards];
        //Populating the array of cards with cards
        int index = 0;
        for (int i = 0; i < numDecks; i++) { //for each deck
            for (int j = 0; j < 4; j++) { //for each suit
                for (int k = 1; k < 14; k++) { //for each card
                    this.cards[index] = new Card(Suit.values()[j], k);
                    index++;
                }
            }
        }
        //shuffle the cards
        if (shuffle) {
            this.shuffle();
        }
    }

    public void shuffle() {
        Random rnd = new Random();
        Card temp;
        int j;
        //Shuffle trough swaping each card with a random card
        for (int i = 0; i < this.numCards; i++) {
            j = rnd.nextInt(this.numCards);
            temp = this.cards[i];
            this.cards[i] = this.cards[j];
            this.cards[j] = temp;
            
        }
    }
    //deals the last card in the deck
    public Card deal() {
        Card top = this.cards[numCards-1];
        this.cards[numCards-1] = null;
        this.numCards--;
        return top;
    }
    
    
    
    
}
