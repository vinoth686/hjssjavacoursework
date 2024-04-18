import java.util.Scanner;

public class AttendClass {
    private SwimmingClassManager manager;

    public AttendClass(SwimmingClassManager manager) {
        this.manager = manager;
    }

    public void attendSwimmingLesson() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Booking ID:");
        int bookingId;

        try {
            bookingId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid booking ID. Booking IDs are numeric.");
            scanner.close();
            return;
        }

        BookingDetails booking = manager.getBookingById(bookingId);

        if (booking != null) {
            System.out.println("Learner " + booking.getUserName() + " with ID " + booking.getBookingId() +
                    " is now attending the swimming class for Grade " + booking.getUserGrade() +
                    " on " + booking.getDay() + " at " + booking.getTimeSlot() + " with coach " + booking.getCoachName());

            System.out.println("Please add your review for the class:");
            String review = scanner.nextLine();

            System.out.println("Please rate the class out of 5:");
            int rating;
            try {
                rating = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid rating. Ratings must be numeric.");
                scanner.close();
                return;
            }

            CoachReport.addReview(new ReviewDetails(booking.getUserName(), booking.getCoachName(), rating, review));

            System.out.println("Thank you for attending. Your attendance has been successfully recorded.");
            System.out.println("Your review: " + review);
            System.out.println("Your rating: " + rating + "/5");

            booking.setStatus("attended");
            System.out.println("Status updated to 'attended'.");
            MainScreen timetableBookingInstance = new MainScreen();
            timetableBookingInstance.showMenu();
        } else {
            System.out.println("No booking found with ID " + bookingId);
        }
        scanner.close();
    }
}
