package AcctMgmt;

import java.io.PrintStream;

public class Menu {
    AccountManagement accountManagement;
    PrintStream printStream;
    LineReader reader;

    private String displayMenu = ("""

                1. Create Account
                2. Account Balance
                3. Deposit
                4. Withdraw
                0. Quit

                Option: """);

    public Menu(AccountManagement accountManagement, PrintStream printStream, LineReader reader){
        this.accountManagement = accountManagement;
        this.printStream = printStream;
        this.reader = reader;
    }

    public void handleSelection() {
        int selection = 10;
        while (selection != 0) {
            printStream.println(displayMenu);
            selection = reader.readInt();
            if (selection == 1) accountManagement.createAccount();
            if (selection == 2) accountManagement.displayAccountBalance();
            if (selection == 3) accountManagement.makeDepoist();
            if (selection == 4) accountManagement.makeWithdrawal();
            else if (selection == 0) printStream.println("Sayonara");
        }
    }
}
