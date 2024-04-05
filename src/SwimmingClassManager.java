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

    public void displayAvailableSlots(String day) {
        String remainingCapacity = "Remaining Capacity: " + (maxCapacity - learners.size());
        System.out.printf("| %-10s | %-10s | %-20s |\n", day, time, remainingCapacity);
    }

    public String getTime() {
        return time;
    }
}

public class SwimmingClassManager {
    private final Map<String, List<SwimmingLesson>> lessonsByDay;
    private final List<BookingDetails> bookings;
    public SwimmingClassManager() {
        lessonsByDay = new HashMap<>();
        bookings = new ArrayList<>();
        initializeLessons();
    }


    private void initializeLessons() {
        Learner.addStaticPreValues();
        for (Learner learner : Learner.learnermap.values()) {
            System.out.println(learner);
        }
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
                SwimmingLesson lesson = new SwimmingLesson(day + " " + time, time, 4);
                lessons.add(lesson);
            }

            lessonsByDay.put(day, lessons);
        }
    }

//    private void initializeLessons() {
//        Learner.addStaticPreValues();
//        for (Learner learner : Learner.learnermap.values()) {
//            System.out.println(learner);
//        }
//        String[] days = {"Monday", "Wednesday", "Friday", "Saturday"};
//        String[] timesMondayWednesdayFriday = {"4-5pm", "5-6pm", "6-7pm"};
//        String[] timesSaturday = {"2-3pm", "3-4pm"};
//
//        for (int week = 1; week <= 4; week++) { // Loop for 4 weeks
//            for (String day : days) {
//                List<SwimmingLesson> lessons = new ArrayList<>();
//
//                String[] times;
//                if (day.equals("Saturday")) {
//                    times = timesSaturday;
//                } else {
//                    times = timesMondayWednesdayFriday;
//                }
//
//                for (String time : times) {
//                    SwimmingLesson lesson = new SwimmingLesson("Week " + week + " " + day + " " + time, time, 4);
//                    lessons.add(lesson);
//                }
//
//                lessonsByDay.put("Week " + week + " " + day, lessons);
//            }
//        }
//    }

    public void showByDay(String day, Map<Integer, Learner> learners) {
        Scanner scanner = new Scanner(System.in);
        boolean continueBooking = true;

        while (continueBooking) {
            System.out.println("Displaying timetable for " + day + ":");
            List<SwimmingLesson> lessons = lessonsByDay.get(day);
            System.out.println(lessons);
            if (lessons != null) {
                for (SwimmingLesson lesson : lessons) {
                    lesson.displayAvailableSlots(day);
                }

                System.out.println("Choose a slot (enter time, e.g., 4-5pm):");
                String chosenTime = scanner.nextLine();

                for (SwimmingLesson lesson : lessons) {
                    if (lesson.getTime().equals(chosenTime)) {
                        Learner.addStaticPreValues();
                        System.out.println("Enter learner's ID:");
                        int learnerId = scanner.nextInt();
                        scanner.nextLine();

                        Learner learner = Learner.learnermap.get(learnerId);
                        if (learner != null) {
                            System.out.println("Hey " + learner.getName() + ", you are currently in grade " + learner.getGrade() + ". Which grade do you want to book the class for?");
                            int desiredGrade = scanner.nextInt();
                            scanner.nextLine();

                            if (desiredGrade >= learner.getGrade() && desiredGrade <= learner.getGrade() + 1) {
                                if (lesson.addLearner(learner.getName())) {
                                    BookingDetails booking = new BookingDetails(learner.getName(), desiredGrade, day, chosenTime, "booked");
                                    bookings.add(booking);
                                    System.out.println("Slot booked successfully for " + learner.getName());
                                    System.out.println("Booked User Details:");
                                    System.out.println("User: " + learner.getName());
                                    System.out.println("Grade: " + desiredGrade);
                                    System.out.println("Day: " + day);
                                    System.out.println("Time Slot: " + chosenTime);
                                    System.out.println("Booking Status: " + booking.getStatus());
                                    for (SwimmingLesson test : lessons) {
                                        test.displayAvailableSlots(day);
                                    }
                                    System.out.println("All Booking Details:");
                                    for (BookingDetails booked : bookings) {
                                        System.out.println("Booking ID: " + booked.getBookingId());
                                        System.out.println("User: " + booked.getUserName());
                                        System.out.println("Grade: " + booked.getUserGrade());
                                        System.out.println("Day: " + booked.getDay());
                                        System.out.println("Time Slot: " + booked.getTimeSlot());
                                        System.out.println("Status: " + booked.getStatus());
                                        System.out.println();
                                    }
                                } else {
                                    System.out.println("Sorry, the slot is already full.");
                                }
                            } else {
                                System.out.println("You can only book your current grade or the next grade.");
                            }
                        } else {
                            System.out.println("Invalid learner ID.");
                        }

                        System.out.println("Do you want to book another lesson? (yes/no)");
                        String choice = scanner.nextLine().trim().toLowerCase();
                        if (!choice.equals("yes")) {
                            continueBooking = false;
                        }
                        break;
                    }
                }
//                if (continueBooking) {
//                    System.out.println("Invalid slot chosen.");
//                }
            } else {
                System.out.println("No lessons available for " + day);
                continueBooking = false;
            }
        }
    }
}