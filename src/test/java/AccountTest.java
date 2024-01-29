import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class AccountTest {
    private Account account;
    private ByteArrayOutputStream outContent;

    @Before
    public void setUp() {
        account = new Account();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testAccount() {
        // Given
        account.deposit(1000, LocalDate.of(2012, 1, 10));
        account.deposit(2000, LocalDate.of(2012, 1, 13));
        account.withdraw(500, LocalDate.of(2012, 1, 14));

        // When
        String statement = account.printStatement();

        // Then
        String lineSeparator = "\n";
        String expectedOutput = "Date       || Amount || Balance" + lineSeparator +
                "10/01/2012 || 1000 || 1000" + lineSeparator +
                "13/01/2012 || 2000 || 3000" + lineSeparator +
                "14/01/2012 || -500 || 2500" + lineSeparator;
        assertEquals(expectedOutput, statement);
    }


}
