import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CoachReportTest {

    public static void setUp() {
        CoachReport.addReview(new ReviewDetails("Alice", "Coach A", 5, "Great session", 3));
        CoachReport.addReview(new ReviewDetails("Bob", "Coach A", 3, "Good session", 3));
        CoachReport.addReview(new ReviewDetails("Charlie", "Coach B", 4, "Nice work", 3));
    }

    @Test
    public void printCoachReports() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        new CoachReport().printCoachReports();
        String expectedOutput = "Coach A received 5, 3 then the average is 4.\nCoach B received 4 then the average is 4.\n";
        assertTrue(outContent.toString().contains(expectedOutput));
        System.setOut(null);
    }
}