package gameplay;

import cards.Card;
import cards.Suite;
import gameplay.players.Dealer;
import gameplay.players.Player;
import gameplay.players.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Table {
    // Scanner
    Scanner scn = new Scanner(System.in);

    // Players
    public List<Player> players = new ArrayList<Player>();
    public User player;
    public Dealer dealer;

    // Deck
    public static final int MAX_SUITE = Suite.values().length;
    public static final int MAX_RANK = Card.KING;
    public List<Card> deck;

    public Table() {
        System.out.println("Welcome to Blackjack");

        this.player = new User(this);
        this.dealer = new Dealer(this);

        // Set opponents
        player.opponent = dealer;
        dealer.opponent = player;

        this.setup();
    }


        // Game Start //

    public void setup() {
        askToPlay();

        player.hand.clear();
        dealer.hand.clear();

        deck = generateDeck();

        player.setup();
        dealer.setup();

        player.turn();
    }

    public void askToPlay() {
        System.out.print("\nWould you like to play Blackjack y/n >> ");
        String response = scn.next();

        if (response.equalsIgnoreCase("y")) {
            clearConsole();
        } else if (response.equalsIgnoreCase("n")) {
            System.exit(0);
        } else {
            System.out.println("Response not recognized, please try again.\n");
            setup();
        }
    }

    public List<Card> generateDeck() {
        List<Card> cards = new ArrayList<Card>();

        // Loop through 6 decks
        for (int decks = 0; decks < 6; decks++) {

            // Loop through Suites
            for (int suite = 0; suite < MAX_SUITE; suite++) {
                Suite currSuite = Suite.getSuiteFromNum(suite);

                // Loop through ranks
                for (int rank = 1; rank <= MAX_RANK; rank++) {

                    // Card index is decks * (suite * MAX_VALUE + rank)
                    cards.add(new Card(currSuite, rank));
                }
            }
        }
        // 312 cards
        return cards;
    }


        // Game Actions //

    public Card dealCard(Player player) {
        // Take a random card from the deck
        int dealIndex = (int)Math.floor(Math.random() * deck.size());
        Card card = deck.get(dealIndex);

        // Move it from the deck to the player
        deck.remove(card);
        player.hand.add(card);
        return card;
    }

    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }


        // Game End //

    public void compareHands() {
        if (player.score > dealer.score) {
            player.win();
        } else if (player.score == dealer.score) {
            player.tie();
        } else {
            dealer.win();
        }
    }

    public void die() {
        player = null;
        dealer = null;
    }
}
