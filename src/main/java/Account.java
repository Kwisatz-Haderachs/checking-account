
public class Account {

    private double balance;
    private String name;

    private int accountNumber;
    
    public Account(double balance, String name, int accountNumber) {
        this.balance = balance;
        this.name = name;
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getBalance() {
        return String.format("Current balance is $%.2f", balance);
    }

    public String getAccountNumber() {
        return String.format("Account #%08d", accountNumber);
    }

    public String getAccountInfo() {
        return getAccountNumber() + "%n" + getBalance();
    }

    public void deposit(double amount) {
        // exception handling not yet implemented
        this.balance += amount;
    }

    public void withdraw(double amount) {
        this.balance -= amount;
    }
}
