import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class SwimmingLesson {
    private final String name;
    private final String time;
    private final int maxCapacity;
    private final List<String> learners;

    public SwimmingLesson(String name, String time, int maxCapacity) {
        this.name = name;
        this.time = time;
        this.maxCapacity = maxCapacity;
        this.learners = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public boolean addLearner(String learnerName) {
        if (learners.size() < maxCapacity) {
            learners.add(learnerName);
            return true;
        }
        return false;
    }

    public void displayAvailableSlots() {
        System.out.println("Lesson: " + name);
        System.out.println("Time: " + time);
        System.out.println("Remaining Capacity: " + (maxCapacity - learners.size()));
    }
    public String getTime() {
        return time;
    }
}

public class SwimmingClassManager {
    private final Map<String, List<SwimmingLesson>> lessonsByDay;

    public SwimmingClassManager() {
        lessonsByDay = new HashMap<>();
        initializeLessons();
    }

//    private void initializeLessons() {
//        String[] days = {"Monday", "Wednesday", "Friday", "Saturday"};
//        String[] timesMondayWednesdayFriday = {"4-5pm", "5-6pm", "6-7pm"};
//        String[] timesSaturday = {"2-3pm", "3-4pm"};
//        for (String day : days) {
//            List<SwimmingLesson> lessons = new ArrayList<>();
//            String[] times;
//            if (day.equals("Saturday")) {
//                times = timesSaturday;
//            } else {
//                times = timesMondayWednesdayFriday;
//            }
//            for (String time : times) {
////                SwimmingLesson lesson = new SwimmingLesson("Grade " + (lessons.size() + 1), time, 4);
////                lessons.add(lesson);
//
//                for (int i = 1; i <= 5; i++) {
//                    SwimmingLesson lesson = new SwimmingLesson("Grade " + i, time, 4);
//                    lessons.add(lesson);
//                }
//            }
//            lessonsByDay.put(day, lessons);
//        }
//    }

    private void initializeLessons() {
        String[] days = {"Monday", "Wednesday", "Friday", "Saturday"};
        String[] timesMondayWednesdayFriday = {"4-5pm", "5-6pm", "6-7pm"};
        String[] timesSaturday = {"2-3pm", "3-4pm"};

        for (String day : days) {
            List<SwimmingLesson> lessons = new ArrayList<>();

            String[] times;
            if (day.equals("Saturday")) {
                times = timesSaturday;
            } else {
                times = timesMondayWednesdayFriday;
            }

            for (String time : times) {
                for (int i = 1; i <= 5; i++) {
                    SwimmingLesson lesson = new SwimmingLesson("Grade " + i, time, 4);
                    lessons.add(lesson);
                }
            }

            lessonsByDay.put(day, lessons);
        }
    }

    public void bookLesson(String day, String time, String learnerName) {
        List<SwimmingLesson> lessons = lessonsByDay.get(day);
        if (lessons != null) {
            for (SwimmingLesson lesson : lessons) {
                if (lesson.getTime().equals(time)) {

                    if (lesson.addLearner(learnerName)) {
                        System.out.println("Lesson booked successfully for " + learnerName);
                    } else {
                        System.out.println("Sorry, the lesson is already full.");
                    }
                    return;
                }
            }
            System.out.println("Invalid time for " + day);
        } else {
            System.out.println("No lessons available for " + day);
        }
    }

//    public void showByDay(String day, Map<Integer, Learner> learners) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Displaying timetable for " + day + ":");
//        List<SwimmingLesson> lessons = lessonsByDay.get(day);
//        if (lessons != null) {
//            for (SwimmingLesson lesson : lessons) {
//                lesson.displayAvailableSlots();
//            }
//
//            // Prompt the user to choose a slot
//            System.out.println("Choose a slot (enter time, e.g., 4-5pm):");
//            String chosenTime = scanner.nextLine();
//
//            // Find the lesson corresponding to the chosen time
//            for (SwimmingLesson lesson : lessons) {
//                if (lesson.getTime().equals(chosenTime)) {
//                    // Try to book the chosen slot
//                    System.out.println("Enter learner's ID:");
//                    int learnerId = scanner.nextInt();
//                    scanner.nextLine(); // Consume newline
//                    Learner learner = learners.get(learnerId);
//                    if (learner != null && learner.getGrade() >= Integer.parseInt(lesson.getName().substring(6))) {
//                        if (lesson.addLearner(learner.getName())) {
//                            System.out.println("Slot booked successfully for " + learner.getName());
//                        } else {
//                            System.out.println("Sorry, the slot is already full.");
//                        }
//                    } else {
//                        System.out.println("Invalid learner ID or grade.");
//                    }
//                    return;
//                }
//            }
//            System.out.println("Invalid slot chosen.");
//        } else {
//            System.out.println("No lessons available for " + day);
//        }
//    }
//public void showByDay(String day, Map<Integer, Learner> learners) {
//    Scanner scanner = new Scanner(System.in);
//    System.out.println("Displaying timetable for " + day + ":");
//    List<SwimmingLesson> lessons = lessonsByDay.get(day);
//    if (lessons != null) {
//        for (SwimmingLesson lesson : lessons) {
//            lesson.displayAvailableSlots();
//        }
//
//        // Prompt the user to choose a slot
//        System.out.println("Choose a slot (enter time, e.g., 4-5pm):");
//        String chosenTime = scanner.nextLine();
//
//        // Find the lesson corresponding to the chosen time
//        for (SwimmingLesson lesson : lessons) {
//            if (lesson.getTime().equals(chosenTime)) {
//                // Try to book the chosen slot
//                System.out.println("Enter learner's ID:");
//                int learnerId = scanner.nextInt();
//                scanner.nextLine(); // Consume newline
//                Learner learner = learners.get(learnerId);
//                if (learner != null) {
//                    // Extract grade number from lesson name using regular expression
//                    Pattern pattern = Pattern.compile("\\b\\d+\\b");
//                    Matcher matcher = pattern.matcher(lesson.getName());
//                    if (matcher.find()) {
//                        int lessonGrade = Integer.parseInt(matcher.group());
//                        if (learner.getGrade() >= lessonGrade) {
//                            if (lesson.addLearner(learner.getName())) {
//                                System.out.println("Slot booked successfully for " + learner.getName());
//                            } else {
//                                System.out.println("Sorry, the slot is already full.");
//                            }
//                        } else {
//                            System.out.println("Learner's grade is too low for this lesson.");
//                        }
//                    } else {
//                        System.out.println("Failed to extract lesson grade.");
//                    }
//                } else {
//                    System.out.println("Invalid learner ID.");
//                }
//                return;
//            }
//        }
//        System.out.println("Invalid slot chosen.");
//    } else {
//        System.out.println("No lessons available for " + day);
//    }
//}
public void showByDay(String day, Map<Integer, Learner> learners) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Displaying timetable for " + day + ":");
    List<SwimmingLesson> lessons = lessonsByDay.get(day);
    if (lessons != null) {
        for (SwimmingLesson lesson : lessons) {
            lesson.displayAvailableSlots();
        }

        System.out.println("Choose a slot (enter time, e.g., 4-5pm):");
        String chosenTime = scanner.nextLine();

        for (SwimmingLesson lesson : lessons) {
            if (lesson.getTime().equals(chosenTime)) {
                System.out.println("Enter learner's ID:");
                int learnerId = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Registered learners: " + learners);
                
                Learner learner = learners.get(learnerId);
                if (learner != null && learner.getGrade() >= Integer.parseInt(lesson.getName().substring(6))) {
                    if (lesson.addLearner(learner.getName())) {
                        System.out.println("Slot booked successfully for " + learner.getName());
                    } else {
                        System.out.println("Sorry, the slot is already full.");
                    }
                } else {
                    System.out.println("Invalid learner ID or grade.");
                }
                return;
            }
        }
        System.out.println("Invalid slot chosen.");
    } else {
        System.out.println("No lessons available for " + day);
    }
}

}
