import AcctMgmt.Account;
import AcctMgmt.LineReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class AccountTests {

    private PrintStream printStream;
    private LineReader reader;
    private Account testAccount;


    @BeforeEach
    void setUp() {
        printStream = mock(PrintStream.class);
        reader = mock(LineReader.class);
        System.setOut(printStream);
        testAccount = new Account(0, "Kaoshi", 1);
    }

    @Test
    public void whenCreateNewAccountShouldCreateWith0BalanceAndName() {
        Account account = new Account(0, "William", 2);
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
