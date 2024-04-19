import java.util.Scanner;

public class MainScreen {
    private SwimmingClassManager classManager;

    public MainScreen() {
        this.classManager = SwimmingClassManager.getInstance();
    }
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to HJSS, Select an option to proceed:");
        System.out.println("1. Book a swimming lesson");
        System.out.println("2. Attend a swimming lesson");
        System.out.println("3. Monthly Learner Report");
        System.out.println("4. Monthly coach report");
        System.out.println("5. Register a new learner");
        System.out.println("6. Change/Cancel a booking");
        int mainOptionChoice = scanner.nextInt();
        scanner.nextLine();

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
                System.out.println("er");
                CoachReport coachReportInstance = new CoachReport();
                coachReportInstance.printCoachReports();
                coachReportInstance.printAllReviews();
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
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    }
}
