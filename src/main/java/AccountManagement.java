import java.io.PrintStream;
import java.util.HashMap;

public class AccountManagement {

    private String name;

    private HashMap<Integer, Account> accounts;
    private PrintStream printStream;
    private LineReader reader;


    private String displayMenu = ("""

                1. Create Account
                2. Account Balance
                3. Deposit
                0. Quit

                Option: """);

    public AccountManagement(PrintStream printStream, LineReader reader) {
        this.printStream = printStream;
        this.reader = reader;
        this.accounts = new HashMap<>();
    }

    public void submit() {
        printStream.println("Please enter account holder name: ");
        name = reader.readLine();
        int accountNumber = accounts.size() + 1;
        Account account = new Account(0.0, name, accountNumber);
        accounts.put(accountNumber, account);
        printStream.println("Account created...");
        printStream.println(account.getAccountInfo());
    }

    public String getName() {
        return this.name;
    }

    public Account getIndividualAccount(int accountNumber) {
        return accounts.get(accountNumber);
    }

    public void displayAccountBalance(){
        printStream.println("Please enter account number: ");
        int number = reader.readInt();
        Account account = getIndividualAccount(number);
        printStream.println(account.getAccountInfo());
    }

    public void makeDepoist(){
        printStream.println("Please enter account number: ");
        int number = reader.readInt();
        Account account = getIndividualAccount(number);
        printStream.println("Please enter amount: ");
        int amount = reader.readInt();
        account.deposit(amount);
        printStream.println("Deposit made");
    }

    public void menu() {
        int selection = 10;
        while (selection != 0) {
            printStream.println(displayMenu);
            selection = reader.readInt();
            if (selection == 1) submit();
            if (selection == 2) displayAccountBalance();
            else if (selection == 0) printStream.println("Sayonara");
        }
    }

}
