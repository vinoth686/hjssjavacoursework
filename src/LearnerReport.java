import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LearnerReport {
    public static void showLearner() {
        Scanner scanner = new Scanner(System.in);
        SwimmingClassManager manager = SwimmingClassManager.getInstance();

        boolean continueReport = true;
        while (continueReport) {
            int month = 0;
            boolean validInput = false;

            while (!validInput) {
                try {
                    System.out.println("Enter the Month (1-12) you want to view the learner report:");
                    month = scanner.nextInt();

                    if (month < 1 || month > 12) {
                        System.out.println("Invalid month. Please enter a value from 1 to 12:");
                    } else {
                        validInput = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid data entered. Please enter a valid month number (1-12).");
                    scanner.nextLine();
                }
            }

            System.out.println("Bookings for Month: " + month);
            displayBookingsForMonth(month, manager);

            System.out.println("Do you want to check another month? Enter 'yes' to continue or 'no' to go back to the main menu:");
            String userResponse = scanner.next();
            if ("no".equalsIgnoreCase(userResponse)) {
                continueReport = false;
            }
        }

        // Show main menu
        MainScreen mainScreenInstance = new MainScreen();
        mainScreenInstance.showMenu();
        scanner.close();
    }

    private static void displayBookingsForMonth(int month, SwimmingClassManager manager) {
        List<BookingDetails> sortedBookings = manager.getAllBookings().stream()
                .filter(booking -> booking.getBookingMonth() == month)
                .sorted(Comparator.comparingInt(BookingDetails::getBookingId))
                .collect(Collectors.toList());

        if (sortedBookings.isEmpty()) {
            System.out.println("No bookings found for this month.");
        } else {
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-10s %-10s %-15s %-10s %-5s %-15s %-10s %-10s %-10s %-15s\n",
                    "Booking ID", "Learner ID", "User Name", "User Grade", "Age", "Emergency Contact", "Day", "Time Slot", "Status", "Coach Name");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            for (BookingDetails booking : sortedBookings) {
                System.out.printf("%-10d %-10d %-15s %-10d %-5d %-15s %-10s %-10s %-10s %-15s\n",
                        booking.getBookingId(),
                        booking.getLearnerId(),
                        booking.getUserName(),
                        booking.getUserGrade(),
                        booking.getAge(),
                        booking.getUserNumber(),
                        booking.getDay(),
                        booking.getTimeSlot(),
                        booking.getStatus(),
                        booking.getCoachName());
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
        }
    }

}
