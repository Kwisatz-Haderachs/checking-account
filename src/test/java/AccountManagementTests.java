import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;

import java.io.PrintStream;
import java.util.HashMap;

import Account.Account;
import AcctMgmt.AccountManagement;
import AcctMgmt.LineReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountManagementTests {
    
    private PrintStream printStream;
    private LineReader reader;
    private AccountManagement accountManagement;
    private Account genericAccount;

    @BeforeEach
    void setUp() {
        printStream = mock(PrintStream.class);
        reader = mock(LineReader.class);
        System.setOut(printStream);
        HashMap<String, Account> accounts = new HashMap<>();
        accountManagement = new AccountManagement(printStream, reader, accounts);
        when(reader.readLine()).thenReturn("Bill");
        accountManagement.createAccount();
        genericAccount = accountManagement.getIndividualAccount(1);

    }

    @Test
    public void whenEnteringAccountHolderNumberReturnsAccount() {
        Account account = accountManagement.getIndividualAccount(1);
        assertEquals("Bill", account.getName());
    }

    @Test
    public void whenAccountIsCreatedAccountShouldHaveUniqueIdBasedOnSize() {
        when(reader.readLine()).thenReturn("Paul");
        accountManagement.createAccount();
        assertEquals("Paul", accountManagement.getIndividualAccount(2).getName());
    }


    @Test
    public void whenMakingDepositShouldTriggerRequestForIndividualAccountAndUpdateTheBalance(){
        when(reader.readLine()).thenReturn("1", "2.00");
        accountManagement.makeDeposit();
        assertTrue(genericAccount.getBalance().contains("$2.00"));
    }

    @Test
    public void whenMakingWithdrawalShouldDeductsFromBalanceAndPrint() {
        genericAccount.deposit(10.0);
        when(reader.readLine()).thenReturn("1", "2.00");
        accountManagement.makeWithdrawal();
        verify(printStream).println(contains("withdrawal of $2.00"));
    }


    @Test
    public void whenMakingWithdrawalShouldReturnsErrorMessageWhenItExceedsBalance() {
        when(reader.readLine()).thenReturn("1", "2.00");
        accountManagement.makeWithdrawal();
        verify(printStream).println(contains("Invalid transaction"));
        assertTrue(genericAccount.getBalance().contains("$0.00"));
    }

    @Test
    public void whenEnteringAccountNumberShouldCheckAccountNumberIsValid(){
        when(reader.readLine()).thenReturn("Ok", "1", "1.00");
        accountManagement.makeWithdrawal();
        verify(printStream).println(contains("Invalid account number"));
    }
}

