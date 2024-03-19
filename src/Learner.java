import java.util.HashMap;
import java.util.Map;

public class Learner {
    private final int id;
    private final String name;
    private final int age;
    private final char gender;
    private final int grade;
    private final int emergencyContact;
    public static Map<Integer, Learner> learnermap = new HashMap<Integer, Learner>();

    public Learner(int id, String name, int age, char gender, int grade, int learnerPhone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.grade = grade;
        this.emergencyContact = learnerPhone;
    }

    public int getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Gender: " + gender + ", Grade: " + grade
                + ", emergencyContact: " + emergencyContact;
    }

    public static void addStaticPreValues() {
        Learner.addStaticLearner(1, "Stephen", 8, 'M', 3, 123456789);
        Learner.addStaticLearner(2, "Benny", 7, 'F', 2, 987654321);
        Learner.addStaticLearner(3, "Arshitha", 5, 'F', 1, 98876767);
    }

    public static void addStaticLearner(int id, String name, int age, char gender, int grade, int phoneNumber) {
        Learner staticLearner = new Learner(id, name, age, gender, grade, phoneNumber);
        learnermap.put(id, staticLearner);
    }

    public static void addDynamicLearner(Learner newLearner) {
        int nextId = learnermap.size() + 1;
        learnermap.put(nextId, newLearner);
    }

    public static Map<Integer, Learner> getLearnerMap() {
        return learnermap;
    }

    public static Learner getLearnerById(int id) {
        return learnermap.get(id);
    }
}
