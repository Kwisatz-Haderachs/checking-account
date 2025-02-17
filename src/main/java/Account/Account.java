package Account;

public class Account {

    private double balance;
    private String name;

    private int accountNumber;
    
    public Account(String name, double balance, int accountNumber) {
        this.balance = balance;
        this.name = name;
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getBalance() {
        return String.format("$%.2f", balance);
    }

    public String getAccountNumber() {
        return String.format("#%08d", accountNumber);
    }

    public String getAccountInfo() {
        return "Account: " + getAccountNumber() + "\n" + "Current balance is: " + getBalance();
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public String withdraw(double amount) {
        if(amount <= balance) {
            balance -= amount;
            return String.format("Successful withdrawal of $%.2f", amount);
        }
        return "Invalid transaction; amount exceeds balance";
    }
}
