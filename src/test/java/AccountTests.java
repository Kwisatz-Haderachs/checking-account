import Account.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountTests {

    private Account testAccount;

    @BeforeEach
    void setUp() {
        testAccount = new Account("Kaoshi", 0.0,1);
    }

    @Test
    public void whenCreateNewAccountShouldCreateWith0BalanceAndName() {
        Account account = new Account("William", 0.0, 2);
        assertEquals("William", account.getName());
        assertTrue(account.getBalance().contains("$0.00"));
        assertTrue(account.getAccountNumber().contains("#00000002"));
    }

    @Test
    public void whenAccountBalanceShouldPrintAccountBalanceOnly2SigFigs(){
        testAccount.deposit(19102.21);
        testAccount.deposit(1212.1121211);
        assertEquals(testAccount.getBalance(), "$20314.32");
    }

    @Test
    public void whenAmountToWithdrawExceedsBalanceShouldPrintErrorMessage(){
        String result = testAccount.withdraw(10.0);
        assertTrue(testAccount.getBalance().contains("$0.00"));
        assertTrue(result.contains("Invalid transaction"));
    }
}
