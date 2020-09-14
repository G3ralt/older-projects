package GameLogic;

public class Card {
    private final Suit mySuit;
    private final int myNumber; // A: 1, J-K: 11-13

    public Card(Suit suit, int number) {
        this.mySuit = suit;
        this.myNumber = number;
    }

    public Suit getMySuit() {
        return mySuit;
    }

    public int getMyNumber() {
        return myNumber;
    }

    @Override
    public String toString() {
        String str = null;
        switch (this.myNumber) {
            case 1:
                str = "Ace";
                break;
            case 2:
                str = "2";
                break;
            case 3:
                str = "3";
                break;
            case 4:
                str = "4";
                break;
            case 5:
                str = "5";
                break;
            case 6:
                str = "6";
                break;
            case 7:
                str = "7";
                break;
            case 8:
                str = "8";
                break;
            case 9:
                str = "9";
                break;
            case 10:
                str = "10";
                break;
            case 11:
                str = "Jack";
                break;
            case 12:
                str = "Queen";
                break;
            case 13:
                str = "King";
                break;
        }
        return (str + "_of_" + mySuit.toString()).toLowerCase();
    }
    
    
}
