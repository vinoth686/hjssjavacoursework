import java.util.InputMismatchException;
import java.util.Scanner;

public class MainScreen {
    private SwimmingClassManager classManager;

    public MainScreen() {
        this.classManager = SwimmingClassManager.getInstance();
    }
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int mainOptionChoice = 0;
        boolean validChoice = false;

        while (!validChoice) {
            try {
                System.out.println("Welcome to HJSS, Select an option to proceed:");
                System.out.println("1. Book a swimming lesson");
                System.out.println("2. Attend a swimming lesson");
                System.out.println("3. Monthly Learner Report");
                System.out.println("4. Monthly coach report");
                System.out.println("5. Register a new learner");
                System.out.println("6. Change/Cancel a booking");
                System.out.println("7. Exit");
                mainOptionChoice = scanner.nextInt();
                scanner.nextLine();

                if (mainOptionChoice < 1 || mainOptionChoice > 7) {
                    System.out.println("Invalid choice. Please select a valid option between 1 and 7.");
                } else {
                    validChoice = true;
                }
            } catch (InputMismatchException ime) {
                System.out.println("Invalid input type. Please enter a valid number.");
                scanner.nextLine();
            }
        }

        switch (mainOptionChoice) {
            case 1:
                TimetableBooking timetableBookingInstance = new TimetableBooking();
                timetableBookingInstance.showTimeTable();
                break;
            case 2:
                AttendClass attendClassInstance = new AttendClass(classManager);
                attendClassInstance.attendSwimmingLesson();
                break;
            case 3:
                Learner.addStaticPreValues();
                LearnerReport showLearner = new LearnerReport();
                showLearner.showLearner();
                break;
            case 4:
                CoachReport coachReportInstance = new CoachReport();
                coachReportInstance.printCoachReports();
                // coachReportInstance.printAllReviews();
                break;
            case 5:
                Registration registrationInstance = new Registration();
                registrationInstance.registerNewLearner();
                registrationInstance.printLearners();
                break;
            case 6:
                ChangeOrCancelBooking changeOrCancelBookingInstance = new ChangeOrCancelBooking(classManager);
                changeOrCancelBookingInstance.modifyBooking();
                break;
            case 7:
                System.out.println("Exiting Program, Bubyee");
                System.exit(0);
            default:
                System.out.println("Unexpected error.");
        }
    }
}
