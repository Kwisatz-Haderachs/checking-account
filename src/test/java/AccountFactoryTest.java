import Account.Account;
import Account.AccountFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AccountFactoryTest {

    @Test
    public void whenAccountFactoryCreateIsCalledShouldCreateAnAccount(){
        Account account = mock(Account.class);
        AccountFactory accountFactory = Mockito.spy(new AccountFactory());
        Mockito.doReturn(account).when(accountFactory).createAccount("John", 0.0d, 1);
        verify(account);
    }
}
