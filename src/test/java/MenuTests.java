import AcctMgmt.AccountManagement;
import AcctMgmt.LineReader;
import AcctMgmt.Menu;
import Cmds.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;

public class MenuTests {

    private PrintStream printStream;
    private LineReader reader;
    private AccountManagement accountManagement;
    private Menu menu;

    @BeforeEach
    void setUp() {
        printStream = mock(PrintStream.class);
        reader = mock(LineReader.class);
        System.setOut(printStream);
        accountManagement = mock(AccountManagement.class);
        HashMap<Integer, Cmd> cmdHashMap = new HashMap<>();
        cmdHashMap.put(1, new CreateAccountCmd(accountManagement));
        cmdHashMap.put(2,  new ViewBalanceCmd(accountManagement));
        cmdHashMap.put(3, new DepositCmd(accountManagement));
        cmdHashMap.put(4, new WithdrawalCmd(accountManagement));
        menu = new Menu(accountManagement, printStream, reader, cmdHashMap);
    }

    @Test
    public void whenOption1CreateAccountIsSelectedShouldEnterAccountManagementCreateAccount(){
        when(reader.readInt()).thenReturn(1, 0);
        menu.handleSelection();
        verify(accountManagement, times(1)).createAccount();
    }


    @Test
    public void whenOption2AccountBalanceIsSelectedShouldEnterAccountManagementBalance(){
        when(reader.readInt()).thenReturn(2,0);
        menu.handleSelection();
        verify(accountManagement, times(1)).displayAccountBalance();
    }

    @Test
    public void whenOption0IsSelectedShouldQuitTriggersExitNotification() {
        when(reader.readInt()).thenReturn(0);
        menu.handleSelection();
        verify(printStream).println(contains("Sayonara"));
    }


    @Test
    public void whenOption3DepositIsSelectedShouldEnterAccountManagementDeposit(){
        when(reader.readInt()).thenReturn(3,0);
        menu.handleSelection();
        verify(accountManagement, times(1)).makeDeposit();
    }

    @Test
    public void whenOption4WithdrawalIsSelectedShouldEnterAccountManagementWithdrawal() {
        when(reader.readInt()).thenReturn(4,0);
        menu.handleSelection();
        verify(accountManagement, times(1)).makeWithdrawal();
    }

}
