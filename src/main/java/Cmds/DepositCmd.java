package Cmds;

import AcctMgmt.AccountManagement;

public class DepositCmd implements Cmd{
    AccountManagement accountManagement;

    public DepositCmd(AccountManagement accountManagement) {
        this.accountManagement = accountManagement;
    }

    @Override
    public void execute() {
        accountManagement.makeDeposit();
    }
}
