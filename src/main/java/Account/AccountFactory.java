package Account;

public class AccountFactory {

    public Account createAccount(String name, Double balance, Integer accountNumber) {
        return new Account(name, balance, accountNumber);
    }
}
