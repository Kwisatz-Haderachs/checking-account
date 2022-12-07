import AcctMgmt.AccountManagement;
import AcctMgmt.LineReader;
import AcctMgmt.Menu;

import java.io.PrintStream;

public class Application {

    LineReader reader;
    AccountManagement accountManagement;
    Menu menu;
    Application() {
        PrintStream printStream = new PrintStream(System.out);
        reader = new LineReader();
        accountManagement = new AccountManagement(printStream, reader );
        menu = new Menu(accountManagement, printStream, reader);
    }

    public void run(){
        menu.handleSelection();
    }
}
