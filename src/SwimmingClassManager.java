import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class SwimmingLesson {
    private final String name;
    private final String time;
    private final int grade;
    public final String coach;
    final int maxCapacity;
    List<String> learners;
    int remainingCapacity;

    public int getRemainingCapacity() {
        return remainingCapacity;
    }
    public SwimmingLesson(String name, String time, int grade, String coach, int maxCapacity) {
        this.name = name;
        this.time = time;
        this.grade = grade;
        this.coach = coach;
        this.maxCapacity = maxCapacity;
        this.learners = new ArrayList<>();
        this.remainingCapacity = maxCapacity;
    }

    public void updateCapacity() {
        this.remainingCapacity = this.maxCapacity - this.learners.size();
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
    updateCapacity();
    String remainingCapacity = "Remaining Capacity: " + this.remainingCapacity;
    System.out.printf("| %-10s | %-10s | %-10s | %-20s |\n", day, "Grade " + grade, time, remainingCapacity);
}

    public String getTime() {
        return time;
    }
}

public class SwimmingClassManager {
    private static SwimmingClassManager single_instance = null;
    private static int lastBookingId = 0;
    private final Map<String, List<SwimmingLesson>> lessonsByDay;
    private final List<BookingDetails> bookings;

    public List<SwimmingLesson> getLessonsByDay(String day) {
        return lessonsByDay.getOrDefault(day, new ArrayList<>());
    }
    public SwimmingClassManager() {
        lessonsByDay = new HashMap<>();
        bookings = new ArrayList<>();
        initializeLessons();
    }

    public BookingDetails getBookingById(int bookingId) {
        for (BookingDetails booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                return booking;
            }
        }
        return null;
    }

    public boolean cancelBooking(BookingDetails booking) {
        String lessonName = "Grade " + booking.getUserGrade() + " " + booking.getDay() + " " + booking.getTimeSlot();

        SwimmingLesson lesson = findLessonByName(lessonName);

        if (lesson != null && bookings.contains(booking)) {

            boolean removed = lesson.learners.removeIf(user -> user.equalsIgnoreCase(booking.getUserName()));

            if (removed) {
                lesson.updateCapacity();
                booking.setStatus("cancelled");
                bookings.remove(booking);
                bookings.add(booking);
                System.out.println("Booking cancelled successfully. Updated capacities:");
                displayAvailableClassesByDay(booking.getDay());
                return true;
            } else {
                System.out.println("Failed to remove booking. No changes made.");
                return false;
            }
        }
        System.out.println("No lesson found with the given name or booking does not exist. Cancellation unsuccessful.");
        return false;
    }

    public List<BookingDetails> getAllBookingsForUser(String userName) {
        List<BookingDetails> userBookings = new ArrayList<>();
        for (BookingDetails booking : bookings) {
            if (booking.getUserName().equalsIgnoreCase(userName)) {
                userBookings.add(booking);
            }
        }
        return userBookings;
    }

    public void displayUserHistory(String userName) {
        List<BookingDetails> bookings = getAllBookingsForUser(userName);
        if (bookings.isEmpty()) {
            System.out.println("No bookings found for " + userName);
            return;
        }
        System.out.println("Booking history for " + userName + ":");
        for (BookingDetails booking : bookings) {
            System.out.println("---------------------------------");
            System.out.println("DEBUG: Printing booking details...");
            System.out.println("Booking ID: " + booking.getBookingId());
            System.out.println("Learner ID: " + booking.getLearnerId());
            System.out.println("User Name: " + booking.getUserName());
            System.out.println("User Grade: " + booking.getUserGrade());
            System.out.println("Day: " + booking.getDay());
            System.out.println("Time Slot: " + booking.getTimeSlot());
            System.out.println("Status: " + booking.getStatus());
            System.out.println("Coach Name: " + booking.getCoachName());
            System.out.println("---------------------------------");
            System.out.printf("ID: %d, Month: %d, Grade: %d, Day: %s, Time: %s, Status: %s\n",
                    booking.getBookingId(), booking.getBookingMonth(), booking.getUserGrade(), booking.getDay(),
                    booking.getTimeSlot(), booking.getStatus());
        }
    }

    private void displayAvailableClassesByDay(String day) {
        List<SwimmingLesson> lessons = getLessonsByDay(day);
        for (SwimmingLesson lesson : lessons) {
            System.out.printf("| %-10s | %-10s | Remaining Capacity: %d |\n",
                    lesson.getTime(), lesson.coach, lesson.getRemainingCapacity());
        }
    }

    public static SwimmingClassManager getInstance() {
        if (single_instance == null)
            single_instance = new SwimmingClassManager();

        return single_instance;
    }
    private void initializeLessons() {
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

        Pattern p = Pattern.compile("\\b(Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday)\\b");
        Matcher m = p.matcher(lessonName);
        if (m.find()) {
            return m.group(1);
        }
        return "Unknown Day";
    }

    public void showByDay() {
        Map<Integer, Learner> learners = Learner.getLearnerMap();
        Scanner scanner = new Scanner(System.in);
        boolean continueBooking = true;

        while (continueBooking) {
//            System.out.println("Enter learner's ID:");
//            int learnerId = Integer.parseInt(scanner.nextLine().trim());
//            Learner learner = learners.get(learnerId);

            int learnerId = -1;
            Learner learner = null;
            while (learner == null) {
                System.out.println("Enter learner's ID (numeric only):");
                String input = scanner.nextLine().trim();
                try {
                    learnerId = Integer.parseInt(input);
                    learner = learners.get(learnerId);
                    if (learner == null) {
                        System.out.println("No learner found with this ID. Please try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric ID.");
                }
            }

            if (learner == null) {
                System.out.println("Invalid learner ID. Please try again.");
                continue;
            }

            if (learner.getAge() > 11) {
                System.out.println("Sorry, you can't book a class. The maximum age limit is 11.");
                continue;
            }

            int currentGrade = learner.getGrade();
            System.out.println("Hello " + learner.getName() + ", you are currently in grade " + currentGrade + ".");

            while (true) {
                System.out.println("Available classes");
                displayClassesForGrades(currentGrade, currentGrade + 1);

                System.out.println("Choose a day for your class (e.g., 'Monday'):");
                String chosenDay = scanner.nextLine().trim();
                List<SwimmingLesson> lessons = lessonsByDay.get(chosenDay);

                if (lessons == null || lessons.isEmpty()) {
                    System.out.println("No lessons available for " + chosenDay + ". Please choose another day.");
                    continue;
                }

                System.out.println("Available slots on " + chosenDay + ":");
                boolean slotsAvailable = false;
                for (SwimmingLesson lesson : lessons) {
                    if (lesson.getGrade() == currentGrade || lesson.getGrade() == currentGrade + 1) {
                        lesson.displayAvailableSlots(chosenDay);
                        slotsAvailable = true;
                    }
                }

                if (!slotsAvailable) {
                    System.out.println("No available slots for your grade on " + chosenDay + ". Please choose another day.");
                    continue;
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
                    learner = Learner.getLearnerMap().get(learnerId);
                    BookingDetails booking = new BookingDetails(learner.getId(),learner.getName(), selectedLesson.getGrade(), learner.getEmergencyContact(), learner.getAge(), chosenDay, chosenTime,  selectedLesson.coach,"booked");
                    bookings.add(booking);
                    System.out.println("Booking successful!");

                    System.out.printf("Booking ID: %s - %s for Grade %d on %s during %s in month %d\n",
                            booking.getBookingId(), booking.getUserName(), booking.getUserGrade(),
                            booking.getDay(), booking.getTimeSlot(), booking.getBookingMonth());
                    System.out.println("List of Bookings");
                    for (BookingDetails booked : bookings) {
                        System.out.println("Booking ID: " + booked.getBookingId());
                        System.out.println("User: " + booked.getUserName());
                        System.out.println("Grade: " + booked.getUserGrade());
                        System.out.println("Age: " + booked.getAge());
                        System.out.println("Emergency Contact: " + booked.getUserNumber());
                        System.out.println("Day: " + booked.getDay());
                        System.out.println("Time Slot: " + booked.getTimeSlot());
                        System.out.println("Coach: " + booked.getCoachName());
                        System.out.println("Month: " + booking.getBookingMonth());
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

    public List<BookingDetails> getAllBookings() {
        return new ArrayList<>(bookings);
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
            int learnerId = -1;

            while (learnerId == -1) {
                try {
                    learnerId = Integer.parseInt(scanner.nextLine().trim());
                    if (!learners.containsKey(learnerId)) {
                        System.out.println("Invalid learner ID. Please try again.");
                        learnerId = -1;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric ID.");
                }
            }
            Learner learner = learners.get(learnerId);

            if (learner == null) {
                System.out.println("Invalid learner ID. Please try again.");
                continue;
            }

            if (learner.getAge() > 11) {
                System.out.println("Sorry, you can't book a class. The maximum age limit is 11.");
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
                String correctDay = extractDayFromLessonName(selectedLesson.getName());
                learner = Learner.getLearnerMap().get(learnerId);
                BookingDetails booking = new BookingDetails(learner.getId(),learner.getName(), selectedLesson.getGrade(), learner.getEmergencyContact(), learner.getAge(), correctDay, selectedLesson.getTime(), selectedLesson.coach,"booked");
                bookings.add(booking);
                System.out.println("Booking successful!");
                System.out.printf("Booking ID: %s - %s for Grade %d on %s during %s in month %d\n",
                        booking.getBookingId(), booking.getUserName(), booking.getUserGrade(),
                        booking.getDay(), booking.getTimeSlot(), booking.getBookingMonth());
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
            String coachName = "";
            boolean isValidName = false;

            while (!isValidName) {
                System.out.println("Enter the coach's name to display their timetable (letters only):");
                coachName = scanner.nextLine().trim();
                if (coachName.matches("[a-zA-Z ]+")) {
                    isValidName = true;
                } else {
                    System.out.println("Invalid input. Please enter letters only.");
                }
            }

            int learnerId = -1;
            while (learnerId == -1) {
                System.out.println("Enter learner's ID (numeric only):");
                String input = scanner.nextLine().trim();
                try {
                    learnerId = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric ID.");
                }
            }
            Learner learner = Learner.getLearnerMap().get(learnerId);

            if (learner == null) {
                System.out.println("Invalid learner ID. Please try again.");
                continue;
            }

            if (learner.getAge() > 11) {
                System.out.println("Sorry, you can't book a class. The maximum age limit is 11.");
                continue;
            }

            int currentGrade = learner.getGrade();
            System.out.println("Hello " + learner.getName() + ", you are currently in grade " + currentGrade + ".");

            List<SwimmingLesson> lessons = getLessonsByCoachAndGrade(coachName, currentGrade, currentGrade + 1);
            if (lessons.isEmpty()) {
                System.out.println("No available classes for Coach " + coachName + " that match your current or next grade level.");
                boolean validResponse = false;
                while (!validResponse) {
                    System.out.println("Do you want to try another coach? (yes/no)");
                    String response = scanner.nextLine().trim().toLowerCase();
                    if (response.equals("yes") || response.equals("no")) {
                        validResponse = true;
                        if (response.equals("no")) {
                            continueBooking = false;
                        }
                    } else {
                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                    }
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
                learner = Learner.getLearnerMap().get(learnerId);
                BookingDetails booking = new BookingDetails(learner.getId(),learner.getName(), selectedLesson.getGrade(), learner.getEmergencyContact(),learner.getAge(),
                        extractDayFromLessonName(selectedLesson.getName()), selectedLesson.getTime(),  selectedLesson.coach,"booked");
                bookings.add(booking);
                System.out.println("Booking successful!");
                System.out.printf("Booking ID: %s - %s for Grade %d on %s during %s in month %d\n",
                        booking.getBookingId(), booking.getUserName(), booking.getUserGrade(),
                        booking.getDay(), booking.getTimeSlot(), booking.getBookingMonth());
            } else {
                System.out.println("Sorry, either the slot is already full or you have already booked this class.");
            }

            System.out.println("Do you want to book another lesson? (yes/no)");
            String choice = scanner.nextLine().trim().toLowerCase();
            continueBooking = "yes".equals(choice);
            if (!continueBooking) {
                System.out.println("Welcome to the main menu");
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
                    lesson.displayAvailableSlots(lesson.getName().split(" ")[2]);
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

            BookingDetails booking = new BookingDetails(learner.getId(),learner.getName(), selectedLesson.getGrade(), learner.getEmergencyContact(), learner.getAge(),extractDayFromLessonName(selectedLesson.getName()), selectedLesson.getTime(),  selectedLesson.coach,"booked");
            bookings.add(booking);
            System.out.println("Booking successful! Your booking details:");
            System.out.printf("Booking ID: %s - %s for Grade %d on %s during %s in month %d\n",
                    booking.getBookingId(), booking.getUserName(), booking.getUserGrade(),
                    booking.getDay(), booking.getTimeSlot(), booking.getBookingMonth());
        } else {
            System.out.println("Sorry, either the slot is already full, you have already booked this class, or no such class exists.");
        }
    }

    private SwimmingLesson findLessonByName(String name) {
        String formattedSearchName = name.trim().toLowerCase();

        for (Map.Entry<String, List<SwimmingLesson>> entry : lessonsByDay.entrySet()) {
            for (SwimmingLesson lesson : entry.getValue()) {
                String formattedLessonName = lesson.getName().toLowerCase();
                if (formattedLessonName.equals(formattedSearchName)) {
                    return lesson;
                }
            }
        }
        return null;
    }

    private void displayAvailableClasses(List<SwimmingLesson> lessons) {
        System.out.println("Available classes:");
        for (SwimmingLesson lesson : lessons) {
            System.out.printf("| %-7s | %-10s | Grade %d | %-10s | Remaining Capacity: %d |\n",
                    lesson.coach, lesson.getName().split(" ")[2], lesson.getGrade(), lesson.getTime(), lesson.maxCapacity - lesson.learners.size());
        }
        System.out.println("Choose a time slot (e.g., '4-5pm'):");
    }

    private void bookClass(String chosenTime, List<SwimmingLesson> lessons, Learner learner) {
        SwimmingLesson selectedLesson = null;
        for (SwimmingLesson lesson : lessons) {
            if (lesson.getTime().equals(chosenTime)) {
                if (lesson.addLearner(learner.getName())) {
                    learner = Learner.getLearnerMap().get(learner);
                    BookingDetails booking = new BookingDetails(learner.getId(), learner.getName(), lesson.getGrade(), learner.getEmergencyContact(), learner.getAge(),extractDayFromLessonName(lesson.getName()), chosenTime,  selectedLesson.coach,"booked");
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

    public boolean changeBooking(BookingDetails existingBooking, String newClassName) {
        SwimmingLesson newLesson = findLessonByName(newClassName);
        if (newLesson != null && newLesson.addLearner(existingBooking.getUserName())) {
            newLesson.updateCapacity();

            SwimmingLesson oldLesson = findLessonByName("Grade " + existingBooking.getUserGrade() + " " + existingBooking.getDay() + " " + existingBooking.getTimeSlot());
            if (oldLesson != null) {
                oldLesson.learners.remove(existingBooking.getUserName());
                oldLesson.updateCapacity();
            }

            existingBooking.setDay(newLesson.getName().split(" ")[2]);
            existingBooking.setTimeSlot(newLesson.getTime());
            return true;
        }
        return false;
    }

    public BookingDetails createBooking(int learnerId, String userName, int userGrade, int userNumber, int age, String day, String timeSlot, String coachName) {
        BookingDetails newBooking = new BookingDetails(learnerId, userName, userGrade, userNumber, age, day, timeSlot,coachName,"booked");
        bookings.add(newBooking);
        return newBooking;
    }

    public void exampleBookingMethod() {
        createBooking(1,"Test user", 3, 988425678, 1, "Monday", "4-5pm", "Ashwath");
    }

    public List<SwimmingLesson> getLessonsByGrade(int grade) {
        List<SwimmingLesson> lessons = new ArrayList<>();
        for (List<SwimmingLesson> dayLessons : lessonsByDay.values()) {
            for (SwimmingLesson lesson : dayLessons) {
                if (lesson.getGrade() == grade) {
                    lessons.add(lesson);
                }
            }
        }
        return lessons;
    }

    public void showAvailableClassesByGrade(int grade) {
        List<SwimmingLesson> lessons = getLessonsByGrade(grade);
        displayAvailableClasses(lessons);
    }

    public void showAvailableSlots(String day, int grade) {
        List<SwimmingLesson> lessons = lessonsByDay.get(day);
        for (SwimmingLesson lesson : lessons) {
            if (lesson.getGrade() == grade) {
                System.out.println(lesson.getTime() + " - Available Capacity: " + (lesson.maxCapacity - lesson.learners.size()));
            }
        }
    }
    public boolean updateBooking(BookingDetails existingBooking, String newTimeSlot) {
        String day = existingBooking.getDay();
        int grade = existingBooking.getUserGrade();
        String learnerName = existingBooking.getUserName();

        List<SwimmingLesson> dayLessons = lessonsByDay.get(day);

        SwimmingLesson newLesson = null;
        SwimmingLesson oldLesson = null;
        for (SwimmingLesson lesson : dayLessons) {
            if (lesson.getTime().equals(newTimeSlot) && lesson.getGrade() == grade) {
                newLesson = lesson;
            }
            if (lesson.getTime().equals(existingBooking.getTimeSlot()) && lesson.getGrade() == grade) {
                oldLesson = lesson;
            }
        }

        if (newLesson == null || oldLesson == null || newLesson == oldLesson) {
            System.out.println("Error finding lessons. No changes made.");
            return false;
        }

        boolean removed = oldLesson.learners.remove(learnerName);
        if (removed) {
            oldLesson.updateCapacity();
            newLesson.learners.add(learnerName);
            newLesson.updateCapacity();

            existingBooking.setTimeSlot(newTimeSlot);

            System.out.println("Booking updated successfully.");
            return true;
        } else {
            System.out.println("Error updating booking. Learner not found in old lesson.");
            return false;
        }
    }
}
