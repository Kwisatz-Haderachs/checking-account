package Cmds;

import AcctMgmt.AccountManagement;

public class CreateAccountCmd implements Cmd{
    AccountManagement accountManagement;

    public CreateAccountCmd(AccountManagement accountManagement) {
        this.accountManagement = accountManagement;
    }

    @Override
    public void execute() {
        accountManagement.createAccount();
    }
}
