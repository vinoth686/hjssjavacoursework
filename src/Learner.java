public class Learner {
    private final int id;
    private final String name;
    private final int age;
    private final char gender;
    private final int emergencyContact;

    public Learner(int id, String name, int age, char gender, int learnerPhone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.emergencyContact = learnerPhone;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Gender: " + gender + ", emergencyContact: " + emergencyContact;
    }
}