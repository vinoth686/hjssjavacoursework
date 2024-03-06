public class Learner {
    private final int id;
    private final String name;
    private final int age;
    private final char gender;
    private final int grade;
    private final int emergencyContact;

    public Learner(int id, String name, int age, char gender, int grade, int learnerPhone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.grade = grade;
        this.emergencyContact = learnerPhone;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Gender: " + gender + ", Grade: " + grade + ", emergencyContact: " + emergencyContact;
    }
}