package cards;

public class Card {
    // Face Ranks
    public static final int ACE = 1;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;

    // Data
    public Suite suite;
    public int rank;


        // Constructors //

    public Card() {
        this(Suite.getRandomSuite(), genRandomRank());
    }

    public Card(Suite suite) {
        this(suite, genRandomRank());
    }

    public Card(int rank) {
        this(Suite.getRandomSuite(), rank);
    }

    public Card(Suite suite, int rank) {
        this.suite = suite;

        // Decide color based on suite (Kinda pointless data, but could be useful?)
        boolean black = suite == Suite.SPADES || suite == Suite.CLUBS;

        this.rank = rank;
    }


        // Generation //

    public static int genRandomRank() {
        // Random int 1 - 13
        return (int)Math.round(Math.random() * 12 + 1);
    }



    // Display //

    public String getName() {
        String rank = this.getRankName();
        String suite = this.suite.getName();
        return rank + " of " + suite;
    }

    public String getRankName() {
        int n = this.rank;
        // No change for number cards
        if (n >= 2 && n <= 10) return String.valueOf(n);

        switch (n) {
            case 1:
                return "Ace";
            case 11:
                return "Jack";
            case 12:
                return "Queen";
            case 13:
                return "King";
        }
        // Catch for invalid card
        return "Invalid Card Rank";
    }

    public String getSymbol() {
        // Get 5 digit Unicode
        String suite = this.suite.getHex();
        String rank = Integer.toHexString(this.rank);
        String code = suite + rank;

        //Convert unicode to hex
        int hex = Integer.parseInt(code, 16);

        // Convert hex to a string
        return new String(Character.toChars(hex));
    }
}
