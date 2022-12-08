import Account.Account;
import AcctMgmt.AccountManagement;
import AcctMgmt.LineReader;
import AcctMgmt.Menu;
import Cmds.*;

import java.io.PrintStream;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		PrintStream printStream = new PrintStream(System.out);
		LineReader reader = new LineReader();
		HashMap<String, Account> accountHashMap = new HashMap<>();
		AccountManagement accountManagement = new AccountManagement(printStream, reader, accountHashMap);
		HashMap<Integer, Cmd> cmdHashMap = new HashMap<>();
		cmdHashMap.put(1, new CreateAccountCmd(accountManagement));
		cmdHashMap.put(2,  new ViewBalanceCmd(accountManagement));
		cmdHashMap.put(3, new DepositCmd(accountManagement));
		cmdHashMap.put(4, new WithdrawalCmd(accountManagement));
		Menu menu = new Menu(accountManagement, printStream, reader, cmdHashMap);
		menu.handleSelection();
	}
}