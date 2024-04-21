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
        System.out.println("Test is starting.");
        try {
            Registration registration = new Registration();
            String input = "John\n2000-01-01\nM\n3\n9876543210\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            registration.registerNewLearner();

            assertTrue("Learner map should contain key 1", Learner.getLearnerMap().containsKey(1));
            assertEquals("Learner name should be John", "John", Learner.getLearnerMap().get(1).getName());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception was thrown during the test: " + e.getMessage());
        } finally {
            System.setIn(System.in);  // Reset System.in to its original state
            System.out.println("Test is ending.");
        }
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }
}
