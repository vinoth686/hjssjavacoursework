import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Map;

public class CoachReport {
    private static List<ReviewDetails> reviews = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void addReview(ReviewDetails review) {
        reviews.add(review);
    }

    public void printCoachReports() {
        System.out.println("Please enter the month number to view reports (e.g., 03 for March):");
        int month = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Coach Reports for Month: " + month);
        Map<String, List<ReviewDetails>> reviewsByCoach = reviews.stream()
                .filter(r -> r.getReviewMonth() == month)
                .collect(Collectors.groupingBy(ReviewDetails::getCoachName));

        reviewsByCoach.forEach((coach, reviewsList) -> {
            System.out.print(coach + " received ");
            List<Integer> ratings = reviewsList.stream().map(ReviewDetails::getRating).collect(Collectors.toList());
            String ratingsText = ratings.stream().map(String::valueOf).collect(Collectors.joining(", "));
            double average = ratings.stream().mapToInt(Integer::intValue).average().orElse(0);
            System.out.println(ratingsText + " then the average is " + (int)average + ".");
        });

        finishInteraction();
    }

    private void finishInteraction() {
        System.out.println("Do you want to go to the main menu or exit? (Enter 'menu' to go to the main menu, anything else to exit)");
        String userChoice = scanner.nextLine().trim();
        if ("menu".equalsIgnoreCase(userChoice)) {
            returnToMainMenu();
        } else {
            System.out.println("Exiting...");
            System.exit(0);
        }
    }

    private void returnToMainMenu() {
        MainScreen timetableBookingInstance = new MainScreen();
        timetableBookingInstance.showMenu();
    }
}
