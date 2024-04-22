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

        String input = "1\nGood class\n5\nmain\n1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        try {
            attend.attendSwimmingLesson();
            assertEquals("attended", manager.getBookingById(1).getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.setIn(System.in);
        }
    }



}
