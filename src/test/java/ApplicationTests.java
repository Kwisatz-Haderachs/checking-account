import AcctMgmt.AccountManagement;
import AcctMgmt.LineReader;
import AcctMgmt.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class ApplicationTests {

    private PrintStream printStream;
    private LineReader reader;
    private Menu menu;
    private Application application;

    @BeforeEach
    void setUp() {
        printStream = mock(PrintStream.class);
        reader = mock(LineReader.class);
        System.setOut(printStream);
        menu = mock(Menu.class);
        AccountManagement accountManagement = mock(AccountManagement.class);
        application = new Application(printStream, reader);
        application.injectDependencies(accountManagement, menu);
    }

    @Test
    public void whenApplicationRunIsCreatedShouldInitialize(){
        when(reader.readInt()).thenReturn(0);
        application.run();
        verify(menu, times(1)).handleSelection();
    }
}
