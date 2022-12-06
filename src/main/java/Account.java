
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

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountInfo() {
        String accountInfo = new StringBuilder()
                .append(String.format("Account #%08d for %s has been created.%n", accountNumber, name))
                .append(String.format("Current balance is $%.2f", balance)).toString();
        return accountInfo;
    }
}
