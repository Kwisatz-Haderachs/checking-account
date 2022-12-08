package Cmds;

import AcctMgmt.AccountManagement;

public class ViewBalanceCmd implements Cmd{
    AccountManagement accountManagement;

    public ViewBalanceCmd(AccountManagement accountManagement) {
        this.accountManagement = accountManagement;
    }

    @Override
    public void execute() {
        accountManagement.displayAccountBalance();
    }
}
