import AcctMgmt.AccountManagement;
import AcctMgmt.LineReader;

import java.io.PrintStream;

public class Main {
	public static void main(String[] args) {
		Application application = new Application(new PrintStream(System.out), new LineReader());
		application.run();
	}
}
