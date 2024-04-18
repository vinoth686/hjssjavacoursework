import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CoachReport {
    private static List<ReviewDetails> reviews = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void addReview(ReviewDetails review) {
        reviews.add(review);
    }

    public void printCoachReports() {
        System.out.println("Coach Reports:");
        for (ReviewDetails review : reviews) {
            System.out.println("Coach: " + review.getCoachName() + ", User: " + review.getUserName() +
                    ", Rating: " + review.getRating() + ", Comment: " + review.getComment());
        }

        listCoachesAndAverageRatings();
    }

    private void returnToMainMenu() {
        MainScreen timetableBookingInstance = new MainScreen();
        timetableBookingInstance.showMenu();
    }

    public static void printAllReviews() {
        for (ReviewDetails review : reviews) {
            System.out.println("User: " + review.getUserName() + ", Coach: " + review.getCoachName() +
                    ", Rating: " + review.getRating() + ", Comment: " + review.getComment());
        }
    }

    public void listCoachesAndAverageRatings() {
        System.out.println("Available coaches:");
        List<String> coaches = List.of("Raj", "Ashwath", "Akash", "Vinodh");
        coaches.forEach(System.out::println);

        System.out.println("Enter the coach name to view their average rating:");
        String chosenCoach = scanner.nextLine().trim();

        List<ReviewDetails> filteredReviews = reviews.stream()
                .filter(r -> r.getCoachName().equalsIgnoreCase(chosenCoach))
                .collect(Collectors.toList());

        if (!filteredReviews.isEmpty()) {
            double averageRating = filteredReviews.stream()
                    .mapToInt(ReviewDetails::getRating)
                    .average()
                    .getAsDouble();
            System.out.printf("The average rating for coach %s is %.2f%n", chosenCoach, averageRating);
        } else {
            System.out.println("No ratings found for coach " + chosenCoach);
        }

        System.out.println("Do you want to go to the main menu or exit? (Enter 'menu' to go to the main menu, anything else to exit)");
        String userChoice = scanner.nextLine().trim();
        if ("menu".equalsIgnoreCase(userChoice)) {
            returnToMainMenu();
        } else {
            System.out.println("Exiting...");
            System.exit(0);
        }
    }
}
