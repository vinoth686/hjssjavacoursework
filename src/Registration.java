import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Registration {
    private int uniqueIdCounter = 1;
    private final Map<Integer, String> learners = new HashMap<>();
    public Map<Integer, String> getLearners() {
        return learners;
    }
    void registerNewLearner() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the learner:");
        String learnerName = scanner.nextLine();

//        System.out.println("Enter the age of the learner:");
//        int learnerAge = scanner.nextInt();
//        scanner.nextLine();
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

        System.out.println("Enter your mobile phone number");
        int learnerPhone = scanner.nextInt();

        int learnerId = uniqueIdCounter++;

        Learner newLearner = new Learner(learnerId, learnerName, learnerAge, learnerGender, learnerPhone);

        learners.put(learnerId, String.valueOf(newLearner));

        System.out.println("Learner registered successfully with ID: " + learnerId);
        System.out.println("Learner registered successfully with ID: " + newLearner);
    }
}
