import java.util.Scanner;

public class AttendClass {
    private SwimmingClassManager manager;

    public AttendClass(SwimmingClassManager manager) {
        this.manager = manager;
    }

    public void attendSwimmingLesson() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Booking ID:");
        String bookingId = scanner.nextLine();
        BookingDetails booking = manager.getBookingById(bookingId);

        if (booking != null) {
            System.out.println("Learner " + booking.getUserName() + " with ID " + booking.getBookingId() +
                    " is now attending the swimming class for Grade " + booking.getUserGrade() +
                    " on " + booking.getDay() + " at " + booking.getTimeSlot());

            System.out.println("Please add your review for the class:");
            String review = scanner.nextLine();

            System.out.println("Please rate the class out of 5:");
            int rating = Integer.parseInt(scanner.nextLine());

            System.out.println("Thank you for attending. Your attendance has been successfully recorded.");
            System.out.println("Your review: " + review);
            System.out.println("Your rating: " + rating + "/5");

            booking.setStatus("attended");

            System.out.println("Status updated to 'attended'.");

        } else {
            System.out.println("No booking found with ID " + bookingId);
        }
        scanner.close();
    }
}
