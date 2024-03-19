import java.util.Map;
public class learnerreport {
//    public void showlearner() {
//        Map<Integer, Learner> learnerMap = Learner.getLearnerMap();
//        System.out.println("List of Learners:");
//        for (Learner learner : learnerMap.values()) {
//            System.out.println(learner);
//        }
//    }

    public void showlearner() {
        System.out.println("List of Learners:");
        for (Map.Entry<Integer, Learner> entry : Learner.learnermap.entrySet()) {
            Learner learner = entry.getValue();
            System.out.println(learner);
        }
    }

//    public static void main(String[] args) {
//        Learner.addStaticPreValues();
//        learnerreport anotherClass = new learnerreport();
//        anotherClass.showlearner();
//    }
}
