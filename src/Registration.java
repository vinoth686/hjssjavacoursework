import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Registration {
    private int uniqueIdCounter = 1;
    private final Map<Integer, Learner> learners = new HashMap<>();

    public Registration() {
        addStaticLearner("Stephen", 8, 'M', 123456789);
        addStaticLearner("Benny", 7, 'F', 987654321);
    }

    private void addStaticLearner(String name, int age, char gender, int phoneNumber) {
        int learnerId = uniqueIdCounter++;
        Learner staticLearner = new Learner(learnerId, name, age, gender, phoneNumber);
        learners.put(learnerId, staticLearner);
    }

    private static boolean isValidPhoneNumber(int phoneNumber) {
        return String.valueOf(phoneNumber).length() == 10;
    }

    public void registerNewLearner() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the learner:");
        String learnerName = scanner.nextLine();

        int learnerAge;
        do {
            System.out.println("Enter the age of the learner:");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid age (between 4 and 11):");
                scanner.next();
            }
            learnerAge = scanner.nextInt();
            if (learnerAge < 4) {
                System.out.println("The entered age is below 4. The proper age to enroll is 4 to 11.");
            } else if (learnerAge > 11) {
                System.out.println("The entered age is above 11. The proper age to enroll is 4 to 11.");
            }
        } while (learnerAge < 4 || learnerAge > 11);
        scanner.nextLine();

        System.out.println("Enter the gender of the learner (M/F):");
        char learnerGender = scanner.nextLine().charAt(0);

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

        Learner newLearner = new Learner(learnerId, learnerName, learnerAge, learnerGender, learnerPhone);

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
        registration.printLearners();
    }
}
