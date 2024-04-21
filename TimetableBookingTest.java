import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;

public class TimetableBookingTest {

    @Test
    public void testShowTimeTable() {
        TimetableBooking timetable = new TimetableBooking();
        String input = "1\n1\nJohn\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        timetable.showTimeTable();

        System.setIn(System.in);
    }
}
