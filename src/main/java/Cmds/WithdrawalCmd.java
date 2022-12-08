package Cmds;

import AcctMgmt.AccountManagement;

public class WithdrawalCmd implements Cmd{
    AccountManagement accountManagement;

    public WithdrawalCmd(AccountManagement accountManagement) {
        this.accountManagement = accountManagement;
    }

    @Override
    public void execute() {
        accountManagement.makeWithdrawal();
    }
}
