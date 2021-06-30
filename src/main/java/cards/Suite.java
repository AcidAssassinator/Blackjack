package cards;

public enum Suite {
    SPADES,
    CLUBS,
    HEARTS,
    DIAMONDS;


        // Generation //

    public static Suite getRandomSuite()  {
        // Random int 0-3
        int i = (int)Math.floor(Math.random() * values().length);
        return values()[i];
    }


        // Display //

    public static Suite getSuiteFromNum(int n) {
        return values()[n];
    }

    public String getName() {
        String name = this.toString().toLowerCase();

        // Capitalize first letter
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public String getHex() {
        // Hex codes taken from https://en.wikipedia.org/wiki/Playing_cards_in_Unicode
        switch (this) {
            case SPADES:
                return "1F0A";
            case CLUBS:
                return "1F0B";
            case HEARTS:
                return "1F0C";
            case DIAMONDS:
                return "1F0D";
        }
        // This hopefully won't ever be returned
        return null;
    }
}
