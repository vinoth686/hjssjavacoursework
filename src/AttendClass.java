import java.util.Scanner;
import java.util.Calendar;

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
            return;
        }

        BookingDetails booking = manager.getBookingById(bookingId);

        if (booking != null) {
            if ("attended".equalsIgnoreCase(booking.getStatus()) || "cancelled".equalsIgnoreCase(booking.getStatus())) {
                System.out.println("You have already " + booking.getStatus() + " this class. Please choose another action.");
                offerNextActions(scanner);
                return;
            }

            Learner learner = Learner.getLearnerMap().get(booking.getLearnerId());
            if (learner != null && booking.getUserGrade() > learner.getGrade()) {
                if (booking.getUserGrade() <= 5) {
                    System.out.println("Learner " + learner.getName() + " is being promoted from Grade " +
                            learner.getGrade() + " to Grade " + booking.getUserGrade() + ".");
                    learner.setGrade(booking.getUserGrade());
                }
            }

            System.out.println("Learner " + booking.getUserName() + " with Booking ID " + booking.getBookingId() +
                    " is now attending the swimming class for Grade " + booking.getUserGrade() +
                    " on " + booking.getDay() + " at " + booking.getTimeSlot() + " with coach " + booking.getCoachName());

            System.out.println("Please add your review for the class:");
            String review = scanner.nextLine();

            int rating = -1;
            while (rating == -1) {
                System.out.println("Please rate the class out of 5:");
                try {
                    rating = Integer.parseInt(scanner.nextLine());
                    if (rating < 1 || rating > 5) {
                        System.out.println("Invalid rating. Ratings must be numeric and between 1 to 5.");
                        rating = -1;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid rating. Ratings must be numeric.");
                }
            }

            Calendar now = Calendar.getInstance();
            int currentMonth = now.get(Calendar.MONTH) + 1;

            CoachReport.addReview(new ReviewDetails(booking.getUserName(), booking.getCoachName(), rating, review, currentMonth));

            System.out.println("Thank you for attending. Your attendance has been successfully recorded.");
            System.out.println("Your review: " + review);
            System.out.println("Your rating: " + rating + "/5");
            System.out.println("Review month: " + currentMonth);

            booking.setStatus("attended");
            System.out.println("Status updated to 'attended'.");
            offerNextActions(scanner);
        } else {
            System.out.println("No booking found with ID " + bookingId);
            offerNextActions(scanner);
        }
    }

    private void offerNextActions(Scanner scanner) {
        System.out.println("Enter 'main' to go back to the main menu or 'retry' to enter a new booking ID:");
        String action = scanner.nextLine().trim().toLowerCase();
        if ("main".equals(action)) {
            MainScreen mainScreen = new MainScreen();
            mainScreen.showMenu();
        } else if ("retry".equals(action)) {
            attendSwimmingLesson();
        } else {
            System.out.println("Invalid input. Returning to main menu.");
            MainScreen mainScreen = new MainScreen();
            mainScreen.showMenu();
        }
    }
}
