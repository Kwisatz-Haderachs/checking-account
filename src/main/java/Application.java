import AcctMgmt.AccountManagement;
import AcctMgmt.LineReader;
import AcctMgmt.Menu;

import java.io.PrintStream;

public class Application {

    private PrintStream printStream;
    private LineReader reader;
    AccountManagement accountManagement;
    Menu menu;
    Application(PrintStream printStream, LineReader lineReader) {
        this.printStream = printStream;
        reader = lineReader;
        accountManagement = new AccountManagement(printStream, reader);
        menu = new Menu(accountManagement, printStream, reader);
    }

    public void injectDependencies(AccountManagement accountManagement, Menu menu){
        this.accountManagement = accountManagement;
        this.menu = menu;
    }

    public void run(){
        menu.handleSelection();
    }
}
