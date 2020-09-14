package GameLogic;

public class Player {
    private String username, password;
    private Card[] hand = new Card[10]; //the players cards
    private int numCards, balance, lastBet;
    
    public Player(String name) {
        this.username = name;
        this.emptyHand();
    }

    public Player(String name, String password, int balance) {
        this.username = name;
        this.password = password;
        this.balance = balance;
        this.emptyHand();
    }
    //initialize the player`s hand with null values
    private void emptyHand() {
        for (int i = 0; i < 10; i++) {
            this.hand[i] = null;
        }
        this.numCards = 0;
    }
    
    public void addCard(Card card) {
        this.hand[this.numCards] = card;
        this.numCards++;
    }
    //calculates the players score
    public int handSum() {
        int handSum = 0, cardNum, numAces = 0;
        
        for (int i = 0; i < this.numCards; i++) { //for each card in the hand
            cardNum = this.hand[i].getMyNumber();
            if (cardNum == 1) { //if ACE
                numAces++;
                handSum += 11;                
            } else if (cardNum > 10) { //if J-K
                handSum += 10;
            } else { //if 2-10
                handSum += cardNum;
            }
        }
        
        while (handSum > 21 && numAces > 0) { //while the sum>21 and we have ACEs
            handSum -= 10;
            numAces--;
        }       
        return handSum;
    }
    
    public String printHand(boolean showFirstCard) {
        String output;
        //System.out.printf("%s's cards:\n", this.username);
        output = this.username + "`s cards:";
        for (int i = 0; i < this.numCards; i++) {
            if (i == 0 && !showFirstCard) {
                output += ("\nHidden!");
            } else {
                output +="\n" + this.hand[i].toString();
            }
        }
        return output;
    }
    
    public void initialBet (int amount) {
        lastBet = amount;
        balance -= amount;
    }
    
    public void bet (int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public int getLastBet() {
        return lastBet;
    }

    public Card[] getHand() {
        return hand;
    }
    
    
    
    
}
