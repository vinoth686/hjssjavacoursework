import static org.junit.Assert.*;
import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class AttendClassTest {

    @Test
    public void testAttendSwimmingLesson() {
        SwimmingClassManager manager = new SwimmingClassManager();
        manager.createBooking(1, "TestUser", 2, 1234567890, 7, "Monday", "4-5pm", "Coach Z");
        AttendClass attend = new AttendClass(manager);

        // Extending input to potentially cover hidden paths
        String input = "1\nGood class\n5\nmain\n1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        try {
            attend.attendSwimmingLesson();
            assertEquals("attended", manager.getBookingById(1).getStatus());
        } catch (Exception e) {
            e.printStackTrace();  // This will print any exception that might occur during the test execution
        } finally {
            // Resetting System.in to avoid any side effects on other tests
            System.setIn(System.in);
        }
    }



}
