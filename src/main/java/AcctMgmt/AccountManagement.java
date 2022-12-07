package AcctMgmt;

import java.io.PrintStream;
import java.util.HashMap;

public class AccountManagement {


    private HashMap<String, Account> accounts;
    private PrintStream printStream;
    private LineReader reader;

    public AccountManagement(PrintStream printStream, LineReader reader) {
        this.printStream = printStream;
        this.reader = reader;
        this.accounts = new HashMap<>();
    }

    public void createAccount() {
        String name = accountHolderName();
        Account account = new Account(0.0, name, accounts.size() + 1);
        accounts.put(account.getAccountNumber(), account);
        printCreateAccountDetails(account);
    }

    private String accountHolderName(){
        printStream.println("Please enter account holder name: ");
        return reader.readLine();
    }

    private void printCreateAccountDetails(Account account){
        printStream.println("Account created for " + account.getName());
        printStream.println(account.getAccountInfo());
    }

    public Account getIndividualAccount(int accountNumber) {
        return accounts.get(convertIntegerToAccountNumber(accountNumber));
    }

    public void displayAccountBalance(){
        printStream.println(getIndividualAccount(returnAccountNum()).getAccountInfo());
    }

    public void makeDeposit(){
        getIndividualAccount(returnAccountNum()).deposit(returnAmount());
        printStream.println("Deposit made");
    }

    public void makeWithdrawal(){
        printStream.println(getIndividualAccount(returnAccountNum()).withdraw(returnAmount()));
    }

    private double returnAmount(){
        printStream.println("Please enter amount: ");
        return reader.readDouble();
    }

    private int returnAccountNum(){
        printStream.println("Please enter account number: ");
        return reader.readInt();
    }

    private String convertIntegerToAccountNumber(int num){
        return String.format("#%08d", num);
    }

}
