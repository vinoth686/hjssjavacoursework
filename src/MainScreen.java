import java.util.Scanner;

public class MainScreen {
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
                System.out.println("we");
                break;
            case 3:
//                System.out.println("wee");
//                break;
                Learner.addStaticPreValues();
               learnerreport testInstance = new learnerreport();
            testInstance.showlearner();
                break;
            case 4:
                System.out.println("er");
                break;
            case 5:
                Registration registrationInstance = new Registration();
                registrationInstance.registerNewLearner();
                registrationInstance.printLearners();
                break;
            case 6:
                System.out.println("jk");
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    }
}
