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
        int count = accounts.size()+1;
        Account account = accountFactory.createAccount( name, 0.0, count);
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

    public void displayAccountBalance(){
        int accountNum = readInAccountNum();
        Account account = getIndividualAccount(accountNum);
        printStream.println(account.getAccountInfo());
    }

    public Account getIndividualAccount(int accountNum) {
        return accounts.get(convertAccountNumber(accountNum));
    }

    private String convertAccountNumber(int accountNum) {
        return String.format("#%08d", accountNum);
    }

    private Account validateAccount() {
        int accountNum = readInAccountNum();
        String accountNumKey = convertAccountNumber(accountNum);
        if(!accounts.containsKey(accountNumKey)) {
            printStream.println("Account doesn't exist");
            readInAccountNum();
        }
        return getIndividualAccount(accountNum);
    }

    public void makeDeposit() {
        Account account = validateAccount();
        double transactionAmount = returnTransactionAmount();
        account.deposit(transactionAmount);
        printStream.println("Deposit made");
    }

    public void makeWithdrawal() {
        Account account = validateAccount();
        double transactionAmount = returnTransactionAmount();
        String msg = account.withdraw(transactionAmount);
        printStream.println(msg);
    }

    private int readInAccountNum(){
        printStream.println("Please enter account number: ");
        boolean validInput = false;
        int acctNum = 0;
        while (!validInput) {
            String input = reader.readLine();
            try {
                acctNum = Integer.parseInt(input);
                validInput = true;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid account number format");
            }
        }
        return acctNum;
    }


    private double returnTransactionAmount() {
        printStream.println("Please enter amount: ");
        boolean validFormat = false;
        double transactionAmount = 0.0;
        while (!validFormat) {
            String input = reader.readLine();
            try{
                transactionAmount = Double.parseDouble(input);
                validFormat = true;
            } catch (NumberFormatException ex){
                System.out.println("Invalid number format");
            }
        }
        return transactionAmount;
    }
}
