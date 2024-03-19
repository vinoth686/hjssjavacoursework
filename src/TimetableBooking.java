import java.util.HashMap;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Map;

public class TimetableBooking {
    private SwimmingClassManager classManager;
    private Map<Integer, Learner> learners;

    public TimetableBooking() {
        this.classManager = new SwimmingClassManager(); // Initialize the SwimmingClassManager
        this.learners = new HashMap<>();
    }
    public void showTimeTable() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            try {
                System.out.println("Select an option to proceed with your timesheet booking:");
                System.out.println("1. Book the time table by day");
                System.out.println("2. Book the time table by grade level");
                System.out.println("3. Book the time table by coach");
                int userChoice = scanner.nextInt();
                scanner.nextLine();

                if (userChoice >= 1 && userChoice <= 3) {
                    choice = userChoice;
                    break;
                } else {
                    System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }

        switch (choice) {
            case 1:
                showByDay();
                break;
            case 2:
                showByGradeLevel();
                break;
            case 3:
                showByCoach();
                break;
        }
    }

    private void showByDay() {
//        System.out.println("Displaying time table by day...");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the day to display timetable:");
        String day = scanner.nextLine();
        classManager.showByDay(day, learners);
    }

    private void showByGradeLevel() {
        System.out.println("Displaying time table by grade level...");
    }

    private void showByCoach() {
        System.out.println("Displaying time table by coach...");
    }
}
