import java.util.Scanner;
import java.util.List;

public class ChangeOrCancelBooking {
    private SwimmingClassManager classManager;

    public ChangeOrCancelBooking(SwimmingClassManager classManager) {
        this.classManager = classManager;
    }

    public void modifyBooking() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your Booking ID to change or cancel:");
        int bookingId = scanner.nextInt();
        scanner.nextLine();

        BookingDetails existingBooking = classManager.getBookingById(bookingId);

        if (existingBooking == null) {
            System.out.println("No booking found with ID: " + bookingId);
            return;
        }

        System.out.println("Booking found: " + existingBooking.getUserName() +
                " for Grade " + existingBooking.getUserGrade() +
                " on " + existingBooking.getDay() +
                " at " + existingBooking.getTimeSlot());
        System.out.println("Do you want to change (1) or cancel (2) this booking? Enter 1 or 2:");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            showAvailableSlotsForChange(existingBooking);
            String newTimeSlot = scanner.nextLine();
            if (classManager.updateBooking(existingBooking, newTimeSlot)) {
                System.out.println("Booking updated successfully.");
                displayCurrentSlotCapacities(existingBooking.getDay());
            } else {
                System.out.println("Failed to update booking. Please try another slot.");
            }
        } else if (choice == 2) {
            if (classManager.cancelBooking(existingBooking)) {
                System.out.println("Booking cancelled successfully.");
                displayCurrentSlotCapacities(existingBooking.getDay());
            } else {
                System.out.println("Cancellation failed. Please try again.");
            }
        } else {
            System.out.println("Invalid choice. Returning to main menu.");
        }

        returnToMainMenu();
    }

    private void showAvailableSlotsForChange(BookingDetails existingBooking) {
        displayCurrentSlotCapacities(existingBooking.getDay());
    }

    private void displayCurrentSlotCapacities(String day) {
        List<SwimmingLesson> dayLessons = classManager.getLessonsByDay(day);
        for (SwimmingLesson lesson : dayLessons) {
            System.out.printf("%s - Available Capacity: %d\n", lesson.getTime(), lesson.getRemainingCapacity());
        }
    }

    private void returnToMainMenu() {
        MainScreen timetableBookingInstance = new MainScreen();
        timetableBookingInstance.showMenu();
    }
}