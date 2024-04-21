import java.util.HashMap;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Map;

public class TimetableBooking {
    private SwimmingClassManager classManager;
    private Map<Integer, Learner> learners;

    public TimetableBooking() {
        this.classManager = SwimmingClassManager.getInstance();
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
        Scanner scanner = new Scanner(System.in);
        classManager.showByDay();
    }

    private void showByGradeLevel() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the grade level to display timetable:");
        int grade = scanner.nextInt();
        scanner.nextLine();
        classManager.showByGrade(grade);
    }


    private void showByCoach() {
        Scanner scanner = new Scanner(System.in);
        classManager.showByCoach();
    }
}
