package AcctMgmt;

import Cmds.*;

import java.io.PrintStream;
import java.util.HashMap;

public class Menu {
    AccountManagement accountManagement;
    PrintStream printStream;
    LineReader reader;
    HashMap<Integer, Cmd> cmdHashMap;


    private final String displayMenu = ("""

                1. Create Account
                2. Account Balance
                3. Deposit
                4. Withdraw
                0. Quit

                Option: """);

    public Menu(AccountManagement accountManagement, PrintStream printStream, LineReader reader, HashMap<Integer,Cmd> hashMap){
        this.accountManagement = accountManagement;
        this.printStream = printStream;
        this.reader = reader;
        this.cmdHashMap = hashMap;
    }

    public void handleSelection() {
        int selection = 10;
        while (selection != 0) {
            printStream.println(displayMenu);
            selection = reader.readInt();
            if(cmdHashMap.containsKey(selection)){
                cmdHashMap.get(selection).execute();
            }
            if (selection == 0) printStream.println("Sayonara");
        }
    }

}
