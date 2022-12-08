package AcctMgmt;

import Account.Account;
import Account.AccountFactory;
import java.io.PrintStream;
import java.util.HashMap;

public class AccountManagement {


    private HashMap<String, Account> accounts;
    private PrintStream printStream;
    private LineReader reader;
    private AccountFactory accountFactory;

    public AccountManagement(PrintStream printStream, LineReader reader, HashMap<String, Account> accounts) {
        this.printStream = printStream;
        this.reader = reader;
        this.accounts = accounts;
        accountFactory = new AccountFactory();
    }

    public void createAccount() {
        String name = accountHolderName();
        Account account = accountFactory.createAccount( name, 0.0, accounts.size() + 1);
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
        int accountNum = (returnAccountNum());
        Account account = getIndividualAccount(accountNum);
        account.deposit(returnTransactionAmount());
        printStream.println("Deposit made");
    }

    public void makeWithdrawal(){
        int accountNum = (returnAccountNum());
        Account account = getIndividualAccount(accountNum);
        String msg = account.withdraw(returnTransactionAmount());
        printStream.println(msg);
    }

    private double returnTransactionAmount(){
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
