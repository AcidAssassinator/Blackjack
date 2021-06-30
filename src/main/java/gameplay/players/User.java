package gameplay.players;

import gameplay.Table;

public class User extends Player{
    public int balance = 500;
    public int bet;

    public User(Table table) {
        super(table);
    }


        // Round Progress //

    @Override
    public void setup() {
        placeBet();

        System.out.println("\n\t<< YOU >>");
        System.out.println("The dealer deals you two cards.");
        receiveCard();
        receiveCard();
        displayHand();
        super.setup();
    }

    @Override
    public void turn() {
        // Check to skip turn or end round
        if (over) {
            if (opponent.over) {
                table.compareHands();
            }
            opponent.turn();
        }

        // Display
        System.out.println("\n\t<< YOU >>");
        System.out.println("You have:");
        displayHand();
        System.out.print("Please Enter a command: Hit | Stand | Double Down >> ");
        String command = scn.nextLine();
        System.out.println();

        // Hit
        if (command.equalsIgnoreCase("hit")) {
            hit();

        // Stand
        } else if (command.equalsIgnoreCase("stand")) {
            stand();

        // Double Down
        } else if (command.equalsIgnoreCase("double down")) {
            doubleDown();

        // Invalid Command
        } else {
            System.out.println("Command not recognized");
            turn();
        }

        super.turn();
    }

    @Override
    public void win() {
        System.out.println("\nYou Win!");
        System.out.println("You won $" + bet);
        balance += bet;

        super.win();
    }

    @Override
    public void lose() {
        System.out.println("You lost $" + bet);
        balance -= bet;
        if (balance <= 0) {
            table.die();
            System.out.println("You can no longer afford to play.\n");
            new Table();
        }
    }

    public void tie() {
        System.out.println("\nTie!");
        System.out.println("You receive you money back");
        table.setup();
    }


        // Game Actions //

    @Override
    public void hit() {
        Table.clearConsole();
        System.out.println("\n\t<< YOU >>");
        System.out.println("The dealer deals you one card.");
        super.hit();
    }

    @Override
    public void stand() {
        Table.clearConsole();
        System.out.println("\n\t<< YOU >>");
        System.out.println("You signal the dealer that you will stand.");
        super.stand();
    }

    public void doubleDown() {
        Table.clearConsole();
        // Check Balance
        if (bet * 2 > balance) {
            System.out.println("You cannot afford to do this action");
            turn();
        }

        // Apply new bet
        bet *= 2;
        System.out.println("\n\t<< YOU >>");
        System.out.println("You double your bet and the dealer deals you one card.");

        // Draw
        super.hit();
        over = true;
        System.out.print("Your new bet is $" + bet);
        System.out.println();
    }

    public void placeBet() {
        // Display
        System.out.println("\nCurrent Balance: $" + balance);
        System.out.print("Enter Bet Amount >> ");

        // Test for integer
        try {
            bet = Integer.parseInt(scn.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid Bet");
            placeBet();
        }

        // Test for valid amount
        if (bet > balance || bet < 1) {
            System.out.println("Invalid Bet");
            placeBet();
        }
    }
}
