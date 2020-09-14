package GameLogic;

public class Game {
    private final Deck deck;
    private final Player player, dealer;

    
    
    
    public Game(final Deck deck, final Player player, final Player dealer) {
        this.deck = deck;
        this.player = player;
        this.dealer = dealer;
    }

    public Deck getDeck() {
        return deck;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getDealer() {
        return dealer;
    }    
}
