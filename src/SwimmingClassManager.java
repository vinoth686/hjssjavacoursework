import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class SwimmingLesson {
    private final String name;
    private final String time;
    private final int grade;
    final String coach;
    final int maxCapacity;
    final List<String> learners;

    public SwimmingLesson(String name, String time, int grade, String coach, int maxCapacity) {
        this.name = name;
        this.time = time;
        this.grade = grade;
        this.coach = coach;
        this.maxCapacity = maxCapacity;
        this.learners = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public boolean addLearner(String learnerName) {
        if (!learners.contains(learnerName) && learners.size() < maxCapacity) {
            learners.add(learnerName);
            return true;
        }
        return false;
    }

    public void displayAvailableSlots(String day) {
        String remainingCapacity = "Remaining Capacity: " + (maxCapacity - learners.size());
        System.out.printf("| %-10s | %-10s | %-20s |\n", day, time, remainingCapacity);
    }

    public String getTime() {
        return time;
    }
}

public class SwimmingClassManager {
    private static SwimmingClassManager single_instance = null;
    private final Map<String, List<SwimmingLesson>> lessonsByDay;
    private final List<BookingDetails> bookings;
    public SwimmingClassManager() {
        lessonsByDay = new HashMap<>();
        bookings = new ArrayList<>();
        initializeLessons();
    }

    public static SwimmingClassManager getInstance() {
        if (single_instance == null)
            single_instance = new SwimmingClassManager();

        return single_instance;
    }
    private void initializeLessons() {
        // Example lessons based on the new structure
        List<SwimmingLesson> lessons = Arrays.asList(
                new SwimmingLesson("Grade 1 Saturday 2-3pm", "2-3pm", 1, "Raj", 4),
                new SwimmingLesson("Grade 2 Saturday 3-4pm", "3-4pm", 2, "Raj", 4),
                new SwimmingLesson("Grade 3 Monday 4-5pm", "4-5pm", 3, "Ashwath", 4),
                new SwimmingLesson("Grade 3 Monday 5-6pm", "5-6pm", 3, "Ashwath", 4),
                new SwimmingLesson("Grade 3 Monday 6-7pm", "6-7pm", 3, "Ashwath", 4),
                new SwimmingLesson("Grade 4 Wednesday 4-5pm", "4-5pm", 4, "Akash", 4),
                new SwimmingLesson("Grade 4 Wednesday 5-6pm", "5-6pm", 4, "Akash", 4),
                new SwimmingLesson("Grade 4 Wednesday 6-7pm", "6-7pm", 4, "Akash", 4),
                new SwimmingLesson("Grade 5 Friday 4-5pm", "4-5pm", 5, "Vinodh", 4),
                new SwimmingLesson("Grade 5 Friday 5-6pm", "5-6pm", 5, "Vinodh", 4),
                new SwimmingLesson("Grade 5 Friday 6-7pm", "6-7pm", 5, "Vinodh", 4)
        );

        for (SwimmingLesson lesson : lessons) {
            String day = lesson.getName().split(" ")[2];
            lessonsByDay.computeIfAbsent(day, k -> new ArrayList<>()).add(lesson);
        }
    }

    private String extractDayFromLessonName(String lessonName) {
        String[] parts = lessonName.split(" ");
        return parts.length > 1 ? parts[1] : "Unknown Day";
    }

    public void showByDay(String day) {
        Map<Integer, Learner> learners = Learner.getLearnerMap();
        Scanner scanner = new Scanner(System.in);
        boolean continueBooking = true;

        while (continueBooking) {
            System.out.println("Enter learner's ID:");
            int learnerId = Integer.parseInt(scanner.nextLine().trim());
            Learner learner = learners.get(learnerId);

            if (learner == null) {
                System.out.println("Invalid learner ID. Please try again.");
                continue;
            }

            int currentGrade = learner.getGrade();
            System.out.println("Hello " + learner.getName() + ", you are currently in grade " + currentGrade + ".");

            while (true) {
                System.out.println("Available classes for Grade " + currentGrade + " and Grade " + (currentGrade + 1) + ":");
                displayClassesForGrades(currentGrade, currentGrade + 1);

                System.out.println("Choose a day for your class (e.g., 'Monday'):");
                String chosenDay = scanner.nextLine().trim();
                List<SwimmingLesson> lessons = lessonsByDay.get(chosenDay);

                if (lessons == null || lessons.isEmpty()) {
                    System.out.println("No lessons available for " + chosenDay + ". Please choose another day.");
                    continue;
                }

                System.out.println("Available slots on " + chosenDay + ":");
                for (SwimmingLesson lesson : lessons) {
                    if (lesson.getGrade() == currentGrade || lesson.getGrade() == currentGrade + 1) {
                        lesson.displayAvailableSlots(chosenDay);
                    }
                }

                System.out.println("Choose a time slot (e.g., '4-5pm'):");
                String chosenTime = scanner.nextLine().trim();
                SwimmingLesson selectedLesson = null;

                for (SwimmingLesson lesson : lessons) {
                    if (lesson.getTime().equals(chosenTime) && (lesson.getGrade() == currentGrade || lesson.getGrade() == currentGrade + 1)) {
                        selectedLesson = lesson;
                        break;
                    }
                }

                if (selectedLesson == null) {
                    System.out.println("Invalid time slot entered. Please try again.");
                    continue;
                }

                if (selectedLesson.addLearner(learner.getName())) {
                    BookingDetails booking = new BookingDetails(learner.getName(), selectedLesson.getGrade(), chosenDay, chosenTime, "booked");
                    bookings.add(booking);
                    System.out.println("Booking successful!");

                    System.out.printf("Booking ID: %s - %s for Grade %d on %s during %s\n",
                            booking.getBookingId(), booking.getUserName(), booking.getUserGrade(),
                            booking.getDay(), booking.getTimeSlot());
//                    System.out.println("Learner ID: " + booking.getLearnerId());
                    for (BookingDetails booked : bookings) {
                        System.out.println("Booking ID: " + booked.getBookingId());
                        System.out.println("Learner ID: " + learner.getId());
                        System.out.println("User: " + booked.getUserName());
                        System.out.println("Grade: " + booked.getUserGrade());
                        System.out.println("Day: " + booked.getDay());
                        System.out.println("Time Slot: " + booked.getTimeSlot());
                        System.out.println("Status: " + booked.getStatus());
                        System.out.println();
                    }
                } else {
                    System.out.println("Sorry, either the slot is already full or you have already booked this class.");
                }

                break;
            }

            System.out.println("Do you want to book another lesson? (yes/no)");
            String choice = scanner.nextLine().trim().toLowerCase();
            continueBooking = "yes".equals(choice);
            if (!continueBooking) {
                System.out.println("hello");
                MainScreen timetableBookingInstance = new MainScreen();
                timetableBookingInstance.showMenu();
            }
        }
    }

    private void displayClassesForGrades(int currentGrade, int nextGrade) {
        boolean hasClasses;

        for (String day : lessonsByDay.keySet()) {
            List<SwimmingLesson> lessons = lessonsByDay.get(day);
            hasClasses = false;

            StringBuilder displayBuilder = new StringBuilder();
            displayBuilder.append("Classes on " + day + ":\n");

            for (SwimmingLesson lesson : lessons) {
                if (lesson.getGrade() == currentGrade || lesson.getGrade() == nextGrade) {
                    if (!hasClasses) {
                        hasClasses = true;
                    }
                    String remainingCapacity = "Remaining Capacity: " + (lesson.maxCapacity - lesson.learners.size());
                    displayBuilder.append(String.format("| %-10s | %-10s | %-20s |\n", lesson.getTime(), lesson.coach, remainingCapacity));
                }
            }

            if (hasClasses) {
                System.out.print(displayBuilder.toString());
            }
        }
    }


    public void showByGrade(int grade) {
        Map<Integer, Learner> learners = Learner.getLearnerMap();
        Scanner scanner = new Scanner(System.in);
        boolean continueBooking = true;

        while (continueBooking) {
            System.out.println("Enter learner's ID:");
            int learnerId = Integer.parseInt(scanner.nextLine().trim());
            Learner learner = learners.get(learnerId);

            if (learner == null) {
                System.out.println("Invalid learner ID. Please try again.");
                continue;
            }

            int currentGrade = learner.getGrade();
            System.out.println("Hello " + learner.getName() + ", you are currently in grade " + currentGrade + ".");

            System.out.println("You can book classes for Grade " + currentGrade + " or Grade " + (currentGrade + 1) + ".");
            List<SwimmingLesson> availableLessons = new ArrayList<>();
            for (List<SwimmingLesson> lessons : lessonsByDay.values()) {
                for (SwimmingLesson lesson : lessons) {
                    if (lesson.getGrade() == currentGrade || lesson.getGrade() == currentGrade + 1) {
                        availableLessons.add(lesson);
                    }
                }
            }

            if (availableLessons.isEmpty()) {
                System.out.println("No available classes for your grades.");
                continue;
            }

            System.out.println("Available classes:");
            for (SwimmingLesson lesson : availableLessons) {
                lesson.displayAvailableSlots(lesson.getName().split(" ")[2]);
            }

            System.out.println("Select a class by entering the exact name (e.g., 'Grade 3 Monday 4-5pm'):");
            String chosenClass = scanner.nextLine().trim();
            SwimmingLesson selectedLesson = null;

            for (SwimmingLesson lesson : availableLessons) {
                if (lesson.getName().equalsIgnoreCase(chosenClass)) {
                    selectedLesson = lesson;
                    break;
                }
            }

            if (selectedLesson == null) {
                System.out.println("Invalid class name entered. Please try again.");
                continue;
            }

            if (selectedLesson.addLearner(learner.getName())) {
                BookingDetails booking = new BookingDetails(learner.getName(), selectedLesson.getGrade(), extractDayFromLessonName(selectedLesson.getName()), selectedLesson.getTime(), "booked");
                bookings.add(booking);
                System.out.println("Booking successful!");
                System.out.printf("Booking ID: %s - %s for Grade %d during %s on %s\n", booking.getBookingId(), booking.getUserName(), booking.getUserGrade(), booking.getTimeSlot(), booking.getDay());
            } else {
                System.out.println("Sorry, either the slot is already full or you have already booked this class.");
            }

            System.out.println("Do you want to book another lesson? (yes/no)");
            String choice = scanner.nextLine().trim().toLowerCase();
            continueBooking = "yes".equals(choice);
            if (!continueBooking) {
                System.out.println("hello");
                MainScreen timetableBookingInstance = new MainScreen();
                timetableBookingInstance.showMenu();
            }
        }
    }

    public void showByCoach() {
        Scanner scanner = new Scanner(System.in);
        boolean continueBooking = true;

        System.out.println("Available coaches: " + getCoachesList());
        while (continueBooking) {
            System.out.println("Enter the coach's name to display their timetable:");
            String coachName = scanner.nextLine().trim();

            System.out.println("Enter learner's ID:");
            int learnerId = Integer.parseInt(scanner.nextLine().trim());
            Learner learner = Learner.getLearnerMap().get(learnerId);

            if (learner == null) {
                System.out.println("Invalid learner ID. Please try again.");
                continue;
            }

            int currentGrade = learner.getGrade();
            System.out.println("Hello " + learner.getName() + ", you are currently in grade " + currentGrade + ".");

            List<SwimmingLesson> lessons = getLessonsByCoachAndGrade(coachName, currentGrade, currentGrade + 1);
            if (lessons.isEmpty()) {
                System.out.println("No available classes for Coach " + coachName + " that match your current or next grade level.");
                System.out.println("Do you want to try another coach? (yes/no)");
                if (!"yes".equalsIgnoreCase(scanner.nextLine().trim())) {
                    break;
                }
                continue;
            }

            System.out.println("Available classes:");
            for (SwimmingLesson lesson : lessons) {
                lesson.displayAvailableSlots(lesson.getName().split(" ")[2]);
            }

            System.out.println("Choose a time slot (e.g., '4-5pm'):");
            String chosenTime = scanner.nextLine().trim();
            SwimmingLesson selectedLesson = null;

            for (SwimmingLesson lesson : lessons) {
                if (lesson.getTime().equals(chosenTime)) {
                    selectedLesson = lesson;
                    break;
                }
            }

            if (selectedLesson == null) {
                System.out.println("Invalid time slot entered. Please try again.");
                continue;
            }

            if (selectedLesson.addLearner(learner.getName())) {
                BookingDetails booking = new BookingDetails(learner.getName(), selectedLesson.getGrade(),
                        extractDayFromLessonName(selectedLesson.getName()), selectedLesson.getTime(), "booked");
                bookings.add(booking);
                System.out.println("Booking successful!");
                System.out.printf("Booking ID: %s - %s for Grade %d during %s on %s\n",
                        booking.getBookingId(), booking.getUserName(), booking.getUserGrade(),
                        booking.getTimeSlot(), booking.getDay());
            } else {
                System.out.println("Sorry, either the slot is already full or you have already booked this class.");
            }

//            System.out.println("Do you want to book another lesson? (yes/no)");
//            continueBooking = "yes".equalsIgnoreCase(scanner.nextLine().trim());

            System.out.println("Do you want to book another lesson? (yes/no)");
            String choice = scanner.nextLine().trim().toLowerCase();
            continueBooking = "yes".equals(choice);
            if (!continueBooking) {
                System.out.println("hello");
                MainScreen timetableBookingInstance = new MainScreen();
                timetableBookingInstance.showMenu();
            }
        }
    }


    private void displayAlternativeClasses(int currentGrade, int nextGrade, Learner learner, Scanner scanner) {
        boolean found = false;
        for (String coach : lessonsByDay.keySet()) {
            List<SwimmingLesson> lessons = getLessonsByCoachAndGrade(coach, currentGrade, nextGrade);
            if (!lessons.isEmpty()) {
                System.out.println("Classes available with Coach " + coach + ":");
                for (SwimmingLesson lesson : lessons) {
                    lesson.displayAvailableSlots(lesson.getName().split(" ")[2]);  // Day extracted from name
                    found = true;
                }
            }
        }
        if (found) {
            bookIfInterested(learner, scanner);
        }
    }

    private void bookIfInterested(Learner learner, Scanner scanner) {
        System.out.println("Do you want to book any of these classes? Enter 'yes' to book or 'no' to exit:");
        String decision = scanner.nextLine().trim();
        if ("yes".equalsIgnoreCase(decision)) {
            bookClassForLearner(learner, scanner);
        }
    }

    private void bookClassForLearner(Learner learner, Scanner scanner) {
        System.out.println("Enter the exact name of the class you want to book (e.g., 'Grade 3 Monday 4-5pm'):");
        String className = scanner.nextLine().trim();
        SwimmingLesson selectedLesson = findLessonByName(className);
        if (selectedLesson != null && selectedLesson.addLearner(learner.getName())) {
            BookingDetails booking = new BookingDetails(learner.getName(), selectedLesson.getGrade(), extractDayFromLessonName(selectedLesson.getName()), selectedLesson.getTime(), "booked");
            bookings.add(booking);
            System.out.println("Booking successful! Your booking details:");
            System.out.printf("Booking ID: %s - %s for Grade %d during %s on %s\n", booking.getBookingId(), booking.getUserName(), booking.getUserGrade(), booking.getTimeSlot(), booking.getDay());
        } else {
            System.out.println("Sorry, either the slot is already full, you have already booked this class, or no such class exists.");
        }
    }

    private SwimmingLesson findLessonByName(String name) {
        for (List<SwimmingLesson> lessons : lessonsByDay.values()) {
            for (SwimmingLesson lesson : lessons) {
                if (lesson.getName().equalsIgnoreCase(name)) {
                    return lesson;
                }
            }
        }
        return null;
    }


//    private void displayAlternativeClasses(int currentGrade, int nextGrade) {
//        for (String coach : lessonsByDay.keySet()) {
//            List<SwimmingLesson> lessons = getLessonsByCoachAndGrade(coach, currentGrade, nextGrade);
//            if (!lessons.isEmpty()) {
//                System.out.println("Classes available with Coach " + coach + ":");
//                for (SwimmingLesson lesson : lessons) {
//                    lesson.displayAvailableSlots(lesson.getName().split(" ")[2]); // Day extracted from name
//                }
//            }
//        }
//    }

    private void displayAvailableClasses(List<SwimmingLesson> lessons) {
        System.out.println("Available classes:");
        for (SwimmingLesson lesson : lessons) {
            System.out.printf("| %-7s | %-10s | Grade %d | %-10s | Remaining Capacity: %d |\n",
                    lesson.coach, lesson.getName().split(" ")[2], lesson.getGrade(), lesson.getTime(), lesson.maxCapacity - lesson.learners.size());
        }
        System.out.println("Choose a time slot (e.g., '4-5pm'):");
//        String chosenTime = scanner.nextLine().trim();
//        bookClass(chosenTime, lessons);
    }

//    private void bookClass(String chosenTime, List<SwimmingLesson> lessons) {
//        for (SwimmingLesson lesson : lessons) {
//            if (lesson.getTime().equals(chosenTime) && lesson.addLearner(learner.getName())) {
//                BookingDetails booking = new BookingDetails(learner.getName(), lesson.getGrade(), extractDayFromLessonName(lesson.getName()), chosenTime, "booked");
//                bookings.add(booking);
//                System.out.println("Booking successful! Your booking details:");
//                System.out.printf("Booking ID: %s - %s for Grade %d during %s on %s\n",
//                        booking.getBookingId(), booking.getUserName(), booking.getUserGrade(), booking.getTimeSlot(), booking.getDay());
//                return;
//            }
//        }
//        System.out.println("Invalid time slot entered or slot is full. Please try again.");
//    }


//    private void displayAlternativeClasses(int currentGrade, int nextGrade) {
//        for (String coach : lessonsByDay.keySet()) {
//            System.out.println("Classes available with Coach " + coach + ":");
//            List<SwimmingLesson> lessons = getLessonsByCoachAndGrade(coach, currentGrade, nextGrade);
//            for (SwimmingLesson lesson : lessons) {
//                lesson.displayAvailableSlots(lesson.getName().split(" ")[2]);  // Day extracted from name
//            }
//        }
//    }

    private void bookClass(String chosenTime, List<SwimmingLesson> lessons, Learner learner) {
        for (SwimmingLesson lesson : lessons) {
            if (lesson.getTime().equals(chosenTime)) {
                if (lesson.addLearner(learner.getName())) {
                    BookingDetails booking = new BookingDetails(learner.getName(), lesson.getGrade(), extractDayFromLessonName(lesson.getName()), chosenTime, "booked");
                    bookings.add(booking);
                    System.out.println("Booking successful! Your booking details:");
                    System.out.printf("Booking ID: %s - %s for Grade %d during %s on %s\n",
                            booking.getBookingId(), booking.getUserName(), booking.getUserGrade(),
                            booking.getTimeSlot(), booking.getDay());
                    return;
                } else {
                    System.out.println("Sorry, this time slot is already full or you have already booked this class.");
                    return;
                }
            }
        }
        System.out.println("Invalid time slot entered. Please try again.");
    }



    private List<SwimmingLesson> getLessonsByCoachAndGrade(String coachName, int currentGrade, int nextGrade) {
        List<SwimmingLesson> filteredLessons = new ArrayList<>();
        for (List<SwimmingLesson> dayLessons : lessonsByDay.values()) {
            for (SwimmingLesson lesson : dayLessons) {
                if (lesson.coach.equalsIgnoreCase(coachName) && (lesson.getGrade() == currentGrade || lesson.getGrade() == nextGrade)) {
                    filteredLessons.add(lesson);
                }
            }
        }
        return filteredLessons;
    }

    private String getCoachesList() {
        Set<String> coaches = new HashSet<>();
        for (List<SwimmingLesson> lessons : lessonsByDay.values()) {
            for (SwimmingLesson lesson : lessons) {
                coaches.add(lesson.coach);
            }
        }
        return String.join(", ", coaches);
    }


    private boolean handleBooking(Scanner scanner, SwimmingLesson selectedLesson, Map<Integer, Learner> learners) {
        return false;
    }

    public BookingDetails getBookingById(String bookingId) {
        for (BookingDetails booking : bookings) {
            if (Integer.toString(booking.getBookingId()).equals(bookingId)) {
                return booking;
            }
        }
        return null; // Return null if no matching booking is found
    }
}