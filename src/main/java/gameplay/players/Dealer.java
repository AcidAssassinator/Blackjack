package gameplay.players;

import cards.Card;
import gameplay.Table;

public class Dealer extends Player{
    public Card hiddenCard;

    public Dealer(Table table) {
        super(table);
    }


        // Round Progress //

    @Override
    public void setup() {
        System.out.println("\n\t<< DEALER >>");
        System.out.println("The dealer draws two cards, placing one face down.");
        receiveCard();
        hiddenCard = receiveCard();
        displayHand();

        super.setup();
    }

    @Override
    public void turn() {
        System.out.println("\n\t<< DEALER >>");

        // Reveal hidden card
        if (hiddenCard != null) {
            revealCard();
            checkScore();
            System.out.println();
        }

        // The dealer "AI"
        if (score >= 17) stand();
        if (score <= 16) hit();

        super.turn();
    }

    @Override
    public void win() {
        System.out.println("\nThe Dealer Wins");
        opponent.lose();
        super.win();
    }


        // Game Actions //

    @Override
    public void hit() {
        System.out.println("The dealer draws one card.");
        super.hit();
    }

    @Override
    public void stand() {
        System.out.println("The dealer stands.");
        super.stand();
    }

    @Override
    public void displayHand() {
        for (Card card : hand) {
            // Show hidden card flipped
            if (card == hiddenCard) {
                System.out.println("Hidden Card");

            } else {
                System.out.println(card.getName());
            }
        }

        System.out.println("Score >> " + computeScore());
    }

    public void revealCard() {
        System.out.println("The dealer reveals their hidden card.");
        hiddenCard = null;
        displayHand();
    }


        // Scoring //

    @Override
    public int getCardValue(Card card) {
        // Don't count hidden card to score
        if (card == hiddenCard) return 0;

        return super.getCardValue(card);
    }
}
