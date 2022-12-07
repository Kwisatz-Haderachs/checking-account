import AcctMgmt.Account;
import AcctMgmt.AccountManagement;
import AcctMgmt.LineReader;
import AcctMgmt.Menu;

import java.io.PrintStream;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		PrintStream printStream = new PrintStream(System.out);
		LineReader reader = new LineReader();
		HashMap<String, Account> hashMap = new HashMap<>();
		AccountManagement accountManagement = new AccountManagement(printStream, reader, hashMap);
		Menu menu = new Menu(accountManagement, printStream, reader);
		menu.handleSelection();
	}
}