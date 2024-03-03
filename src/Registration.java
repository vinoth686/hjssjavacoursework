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

        System.out.println("Enter the age of the learner:");
        int learnerAge = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the gender of the learner (M/F):");
        char learnerGender = scanner.nextLine().charAt(0);

        int learnerId = uniqueIdCounter++;

        Learner newLearner = new Learner(learnerId, learnerName, learnerAge, learnerGender);

        learners.put(learnerId, String.valueOf(newLearner));

        System.out.println("Learner registered successfully with ID: " + learnerId);
        System.out.println("Learner registered successfully with ID: " + newLearner);
    }

}
