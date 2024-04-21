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

        String input = "1\nGood class\n5";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        attend.attendSwimmingLesson();
        assertEquals("attended", manager.getBookingById(1).getStatus());
        System.setIn(System.in);
    }
}
