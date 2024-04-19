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
            for (BookingDetails booking : sortedBookings) {
                System.out.println("---------------------------------");
                System.out.println("Booking ID: " + booking.getBookingId());
                System.out.println("Learner ID: " + booking.getLearnerId());
                System.out.println("User Name: " + booking.getUserName());
                System.out.println("User Grade: " + booking.getUserGrade());
                System.out.println("Day: " + booking.getDay());
                System.out.println("Time Slot: " + booking.getTimeSlot());
                System.out.println("Status: " + booking.getStatus());
                System.out.println("Coach Name: " + booking.getCoachName());
                System.out.println("---------------------------------");
            }
        }
    }
}
