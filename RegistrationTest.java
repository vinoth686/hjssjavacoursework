import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;

public class RegistrationTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @Test
    public void testRegisterNewLearner() {
        try {
            Registration registration = new Registration();
            // Over-provide input to cover unexpected additional input requests
            String input = "John\n2015-01-01\nM\n3\n9876543210\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n";
            provideInput(input);

            registration.registerNewLearner();

            assertTrue("Learner map should contain at least one entry", !registration.getLearners().isEmpty());
            assertTrue("Output should confirm successful registration", getOutput().contains("Learner registered successfully"));
            Learner registered = registration.getLearners().get(1);  // Assuming ID starts at 1
            assertEquals("Check registered name", "John", registered.getName());
            System.out.println("Test completed successfully without exceptions.");
        } catch (Throwable e) {
            e.printStackTrace(System.out);
            fail("Exception was thrown during the test: " + e.toString());
        } finally {
            restoreSystemInputOutput();
            System.out.println("Test is ending. Output was: " + getOutput());
        }
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }
}
