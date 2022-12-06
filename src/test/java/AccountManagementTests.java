import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;

import java.io.PrintStream;

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
        accountManagement = new AccountManagement(printStream, reader);
        genericAccount = new Account(0, "Bill", 1);
    }

    private void mockAccountCreation(){
        when(reader.readLine()).thenReturn("Bill");
        accountManagement.submit();
    }

    @Test
    public void testAccountIsCreatedWhenUserNameIsGiven() {
        mockAccountCreation();
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
        mockAccountCreation();
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
        mockAccountCreation();
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
        mockAccountCreation();
        when(reader.readInt()).thenReturn(2).thenReturn(1).thenReturn(0);
        accountManagement.menu();
        verify(printStream, times(2)).println(contains("$0.00"));
    }

    @Test
    public void makingDepositIntoAnIndividualAccountUpdatesTheBalance(){
        assertEquals("Bill", genericAccount.getName());
        assertEquals(0.00, genericAccount.getBalance());
        assertEquals(1, genericAccount.getAccountNumber());
        genericAccount.deposit(2.0);
        assertEquals(2.0, genericAccount.getBalance());
    }

    @Test
    public void menuOptionSelectDepositAndDepositsMoney(){
        mockAccountCreation();
        when(reader.readInt()).thenReturn(3).thenReturn(1).thenReturn(2).thenReturn(1).thenReturn(0);
        when(reader.readDouble()).thenReturn(5.0);
        accountManagement.menu();
        verify(printStream).println(contains("$5.00"));
    }

    @Test
    public void makingWithdrawalDeductsFromBalanceOfAccount() {
        genericAccount.deposit(10.0);
        assertEquals(10.0, genericAccount.getBalance());
        genericAccount.withdraw(8.0);
        assertEquals(2.0, genericAccount.getBalance());
    }

    @Test
    public void menuOptionSelectWithdrawAndWithdrawalsMoney(){
        mockAccountCreation();
        when(reader.readInt()).thenReturn(4).thenReturn(1).thenReturn(0);
        when(reader.readDouble()).thenReturn(5.0);
        accountManagement.menu();
        verify(printStream).println(contains("Withdrawal made"));
    }
}

