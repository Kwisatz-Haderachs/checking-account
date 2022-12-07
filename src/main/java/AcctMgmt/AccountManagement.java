package AcctMgmt;

import java.io.PrintStream;
import java.util.HashMap;

public class AccountManagement {

    private String name;

    private HashMap<String, Account> accounts;
    private PrintStream printStream;
    private LineReader reader;

    public AccountManagement(PrintStream printStream, LineReader reader) {
        this.printStream = printStream;
        this.reader = reader;
        this.accounts = new HashMap<>();
    }

    public void createAccount() {
        printStream.println("Create account ...");
        printStream.println("Please enter account holder name: ");
        name = reader.readLine();
        int accountNumber = accounts.size() + 1;
        Account account = new Account(0.0, name, accountNumber);
        accounts.put(account.getAccountNumber(), account);
        printStream.println("Account created for " + name);
        printStream.println(account.getAccountInfo());
    }

    public String getName() {
        return this.name;
    }

    public Account getIndividualAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void displayAccountBalance(){
        printStream.println("Please enter account number: ");
        int number = reader.readInt();
        Account account = getIndividualAccount(convertIntegerToAccountNumber(number));
        printStream.println(account.getAccountInfo());
    }

    public void makeDepoist(){
        printStream.println("Please enter account number: ");
        int number = reader.readInt();
        Account account = getIndividualAccount(convertIntegerToAccountNumber(number));
        printStream.println("Please enter amount: ");
        double amount = reader.readDouble();
        account.deposit(amount);
        printStream.println("Deposit made");
    }

    public void makeWithdrawal(){
        printStream.println("Please enter account number: ");
        int number = reader.readInt();
        Account account = getIndividualAccount(convertIntegerToAccountNumber(number));
        printStream.println("Please enter amount: ");
        double amount = reader.readDouble();
        String result = account.withdraw(amount);
        printStream.println(result);
    }


    public String convertIntegerToAccountNumber(int num){
        return String.format("#%08d", num);
    }

}
