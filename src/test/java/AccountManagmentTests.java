import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;

import java.io.PrintStream;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountManagmentTests {
    
    private PrintStream printStream;
    private LineReader reader;
    private AccountManagement accountManagement;

    @BeforeEach
    void setUp() {
        printStream = mock(PrintStream.class);
        reader = mock(LineReader.class);
        System.setOut(printStream);
        accountManagement = new AccountManagement(printStream, reader);
    }

    @Test
    public void testAccountIsCreatedWhenUserNameIsGiven() {
        when(reader.readLine()).thenReturn("Bill");
        accountManagement.submit();
        assertEquals("Bill", accountManagement.getName());
    }

    @Test
    public void canCreateNewAccountWithBalanceAndName() {
        Account account = new Account(0, "Bill", 1);
        assertEquals("Bill", account.getName());
        assertEquals(0.00, account.getBalance());
        assertEquals(1, account.getAccountNumber());
    }

    @Test
    public void enteringAccountHolderNameReturnsAccount() {
        when(reader.readLine()).thenReturn("Bill");
        accountManagement.submit();
        Account account = accountManagement.getIndividualAccount(1);
        assertEquals("Bill", account.getName());
        assertEquals(0.00, account.getBalance());
    }

    @Test
    public void menuOptionSelectCreateAccountStartsCreateAccountProcess() {
        accountManagement.menu();
        verify(printStream).println(contains("Create Account"));
        verify(printStream).println(contains("Quit"));
    }

    @Test
    public void menuOptionSelecting1TriggersSubmitAccountPrompt() {
        when(reader.readInt()).thenReturn(1).thenReturn(0);
        accountManagement.menu();
        verify(printStream).println(contains("account holder name"));
        when(reader.readLine()).thenReturn("Bill");
    }

    @Test
    public void menuOptionSelectingQuitTriggersExitNotification() {
        when(reader.readInt()).thenReturn(0);
        accountManagement.menu();
        verify(printStream).println(contains("Sayonara"));
    }

//    @Test
//    public void menuOptionNumberFormatException(){
//        when(reader.readInt()).thenReturn(10).thenReturn(0);
//        accountManagement.menu();
//        verify(printStream).println(contains("Create Account"));
//    }

    @Test
    public void whenAccountIsCreatedAccountHasUniqueId() {
        when(reader.readLine()).thenReturn("Bill");
        accountManagement.submit();
        when(reader.readLine()).thenReturn("Paul");
        accountManagement.submit();

        assertEquals(1, accountManagement.getIndividualAccount(1).getAccountNumber());
        assertEquals(2, accountManagement.getIndividualAccount(2).getAccountNumber());
    }

    @Test
    public void menuOptionHasDisplayAccountBalance(){
        accountManagement.menu();
        verify(printStream).println(contains("Balance"));
    }

    @Test
    public void menuOptionSelectDisplayAccountBalancePrintsAccountBalance(){
        when(reader.readLine()).thenReturn("Bill");
        accountManagement.submit();
        when(reader.readInt()).thenReturn(2).thenReturn(1).thenReturn(0);
        accountManagement.menu();
        verify(printStream, times(2)).println(contains("$0.00"));
    }

    @Test
    public void makingDepositIntoAnIndividualAccountUpdatesTheBalance(){
        Account account = new Account(0, "Bill", 1);
        assertEquals("Bill", account.getName());
        assertEquals(0.00, account.getBalance());
        assertEquals(1, account.getAccountNumber());
        account.deposit(2.0);
        assertEquals(2.0, account.getBalance());
    }
}

