import java.util.HashMap;
import java.util.Map;

public class Learner {
    private final int id;
    private final String name;
    private final int age;
    private final char gender;
    private int grade;
    final int emergencyContact;
    public static Map<Integer, Learner> learnermap = new HashMap<Integer, Learner>();

    static {
        addStaticPreValues();
    }

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

    public int getEmergencyContact() {
        return emergencyContact;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Gender: " + gender + ", Grade: " + grade
                + ", emergencyContact: " + emergencyContact;
    }

    public static void addStaticPreValues() {
        Learner.addStaticLearner(1, "Stephen", 8, 'M', 3, 988425678);
        Learner.addStaticLearner(2, "Benny", 7, 'F', 2, 987654321);
        Learner.addStaticLearner(3, "Arshitha", 5, 'F', 1, 98876767);
        Learner.addStaticLearner(4, "Plavio", 6, 'M', 3, 878729873);
        Learner.addStaticLearner(5, "Rachel", 7, 'F', 2, 867839033);
        Learner.addStaticLearner(6, "Abdullah", 9, 'M', 1, 988098763);
        Learner.addStaticLearner(7, "Girish", 10, 'M', 4, 988435635);
        Learner.addStaticLearner(8, "Raghul", 5, 'M', 5, 987654321);
        Learner.addStaticLearner(9, "Tamil", 7, 'F', 5, 98876767);
        Learner.addStaticLearner(10, "Indu", 8, 'F', 4, 988674567);
        Learner.addStaticLearner(11, "Kirana", 12, 'F', 4, 988765678);
    }

    public static void addStaticLearner(int id, String name, int age, char gender, int grade, int phoneNumber) {
        Learner staticLearner = new Learner(id, name, age, gender, grade, phoneNumber);
        learnermap.put(id, staticLearner);
    }

    public static void addDynamicLearner(Learner newLearner) {
        int nextId = learnermap.size() + 1;
        learnermap.put(nextId, newLearner);
    }

    public void setGrade(int grade) {
        if (grade > 5) {
            this.grade = 5;
        } else {
            this.grade = grade;
        }
    }

    public static Map<Integer, Learner> getLearnerMap() {
        return learnermap;
    }

    public static Learner getLearnerById(int id) {
        return learnermap.get(id);
    }

    public int getId() {
        return id;
    }

}
