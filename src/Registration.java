import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.time.DateTimeException;

public class Registration {
    private int uniqueIdCounter = 1;
    private final Map<Integer, Learner> learners = new HashMap<>();

    public Registration() {
        addStaticLearner("Stephen", 8, 'M', 3,123456789);
        addStaticLearner("Benny", 7, 'F', 2,987654321);
        addStaticLearner("Arshitha", 5, 'F', 1,98876767);
    }

    private void addStaticLearner(String name, int age, char gender, int grade, int phoneNumber) {
        int learnerId = uniqueIdCounter++;
        Learner staticLearner = new Learner(learnerId, name, age, gender, grade, phoneNumber);
        learners.put(learnerId, staticLearner);
    }

    private static boolean isValidPhoneNumber(int phoneNumber) {
        return String.valueOf(phoneNumber).length() == 10;
    }

    private static int calculateAge(LocalDate birthdate, LocalDate currentDate) {
        int learnerAge = currentDate.getYear() - birthdate.getYear();
        if (birthdate.getMonthValue() > currentDate.getMonthValue() ||
                (birthdate.getMonthValue() == currentDate.getMonthValue() && birthdate.getDayOfMonth() > currentDate.getDayOfMonth())) {
            learnerAge--;
        }
        return learnerAge;
    }

    private static LocalDate getValidBirthdate(Scanner scanner) {
        LocalDate birthdate = null;
        boolean isValidInput = false;

        while (!isValidInput) {
            try {
                System.out.println("Enter your birthdate (yyyy-MM-dd):");
                String birthdateInput = scanner.nextLine();
                birthdate = LocalDate.parse(birthdateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                isValidInput = true;
            } catch (DateTimeException e) {
                System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid date in the format yyyy-MM-dd.");
                scanner.nextLine();
            }
        }
        return birthdate;
    }

    public void registerNewLearner() {
        Scanner scanner = new Scanner(System.in);
        String learnerName;
        while (true) {
            System.out.println("Enter the name of the learner:");
            learnerName = scanner.nextLine();

            if (learnerName.matches("^[a-zA-Z]+$")) {
                break;
            } else {
                System.out.println("Only alphabets are allowed. Please try again.");
            }
        }
        LocalDate currentDate = LocalDate.now();
        LocalDate birthdate = getValidBirthdate(scanner);
        int learnerAge = calculateAge(birthdate, currentDate);

        while (learnerAge < 4 || learnerAge > 11) {
            System.out.println("Invalid age. The proper age to enroll is 4 to 11, Sorry we can't proceed.");
            System.exit(0);
             MainScreen timetableBookingInstance = new MainScreen();
             timetableBookingInstance.showMenu();
//            birthdate = getValidBirthdate(scanner);
//            age = calculateAge(birthdate, currentDate);
        }

        System.out.println("Enter the gender of the learner (M/F):");
        char learnerGender = scanner.nextLine().charAt(0);

        System.out.println("Enter the grade");
        int learnerGrade = -1;
        boolean isValidInput = false;

        while (!isValidInput) {
            if (scanner.hasNextInt()) {
                learnerGrade = scanner.nextInt();
                if (learnerGrade >= 0 && learnerGrade <= 5) {
                    isValidInput = true;
                } else {
                    System.out.println("Invalid grade. Please enter a grade between 0 and 4.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer grade between 0 and 4.");
                scanner.next();
            }
        }
//        System.out.println("Enter your mobile phone number");
//        int learnerPhone = scanner.nextInt();
        int learnerPhone;
        do {
            System.out.println("Enter your mobile phone number");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid mobile phone number:");
                scanner.next();
            }
            learnerPhone = scanner.nextInt();
        } while (isValidPhoneNumber(learnerPhone));


        int learnerId = uniqueIdCounter++;

        Learner newLearner = new Learner(learnerId, learnerName, learnerAge, learnerGender, learnerGrade, learnerPhone);

        learners.put(learnerId, newLearner);

        System.out.println("Learner registered successfully with ID: " + newLearner);
    }

    public void printLearners() {
        System.out.println("List of Registered Learners:");
        for (Learner learner : learners.values()) {
            System.out.println(learner);
        }
    }

    public static void main(String[] args) {
        Registration registration = new Registration();
        registration.registerNewLearner();
//        registration.printLearners();
    }
}
