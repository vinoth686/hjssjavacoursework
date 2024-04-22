import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;

public class TimetableBookingTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @Before
    public void setUp() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @Test
    public void testShowTimeTable() {
        try {
            TimetableBooking timetable = new TimetableBooking();
            // Provide a comprehensive set of inputs to cover all branches and input requests
            String input = "1\n1\nJohn\n1\n1\n1\n1\n1\n1\n1\n1\n1\nexit\nexit\n";
            provideInput(input);

            timetable.showTimeTable();

            // Verify that the output contains expected substrings indicating correct execution
            assertTrue("Expected output to contain 'Enter learner's ID'", getOutput().contains("Enter learner's ID"));
            assertTrue("Expected output to contain 'Booking successful'", getOutput().contains("Booking successful"));  // Example
        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail("Exception was thrown during the test: " + e.getMessage());
        } finally {
            restoreStreams();
        }
    }

    @After
    public void restoreStreams() {
        System.setIn(systemIn);
        System.setOut(systemOut);
        System.setErr(systemOut);  // If you redirect stderr in future modifications
    }
}
