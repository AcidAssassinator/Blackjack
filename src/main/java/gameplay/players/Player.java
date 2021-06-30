package gameplay.players;

import cards.Card;
import gameplay.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    Scanner scn = new Scanner(System.in);

    // Objects
    public List<Card> hand = new ArrayList<>();
    public Table table;
    public Player opponent;

    public boolean over = false;

    // Scoring
    public int score = 0;
    public int aces = 0;


        // Constructor //

    public Player(Table table) {
        this.table = table;
    }


        // Round Progress //

    public void setup() {
        over = false;
    }

    public void displayHand() {
        for (Card card : hand) {
            System.out.println(card.getName());
        }

        System.out.println("Score >> " + computeScore());
        System.out.println();
    }

    public void turn() {
        opponent.turn();
    }

    public void win() {
        table.setup();
    }

    public void lose() {}


        // Turn Actions //

    public void hit() {
        receiveCard();
        displayHand();
        checkScore();
    }

    public void stand() {
        displayHand();
        over = true;
    }

    public Card receiveCard() {
        return table.dealCard(this);
    }


        // Scoring //

    public void checkScore() {
        if (score > 21) {
            System.out.println("Bust!");
            opponent.win();
        }
    }

    public String computeScore() {
        score = 0;
        aces = 0;

        // Base values
        for (Card card : hand) {
            score += getCardValue(card);
        }

        // Increase ace value if possible
        for (int i = 0; i < aces; i++) {
            if (score + 10 <= 21) score += 10;
        }

        return String.valueOf(score);
    }

    public int getCardValue(Card card) {
        // Ace
        if (card.rank == 1) {
            aces++;
            return 1;
        }

        // 2 - 10
        if (card.rank >= 2 && card.rank <= 10) return card.rank;

        // Face cards
        return 10;
    }
}
