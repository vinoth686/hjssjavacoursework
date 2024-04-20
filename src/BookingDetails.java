import java.util.Objects;
import java.util.Calendar;

public class BookingDetails {
    private static int nextBookingId = 1;
    private final int bookingId;
    private final String userName;
    private final int userGrade;
    private final int userNumber;
    private final int age;
    private String day;
    private String timeSlot;
    private String status;
    private int learnerId;
    private String coachName;
    private final int bookingMonth;


    public BookingDetails(int learnerId, String userName, int userGrade, int userNumber,int age, String day, String timeSlot, String coachName, String status) {
        this.bookingId = nextBookingId++;
        this.userName = userName;
        this.userGrade = userGrade;
        this.userNumber = userNumber;
        this.age = age;
        this.day = day;
        this.timeSlot = timeSlot;
        this.learnerId = learnerId;
        this.status = "booked";
        this.coachName = coachName;
        Calendar now = Calendar.getInstance();
        this.bookingMonth = now.get(Calendar.MONTH) + 1;
    }

    public int getBookingMonth() {
        return bookingMonth;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public int getAge() {
        return age;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getCoachName() {
        return coachName;
    }

    public int getLearnerId() {
        return learnerId;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserGrade() {
        return userGrade;
    }

    public String getDay() {
        return day;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public String getStatus() {
        return status;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

//    public int getLearnerId() {
//        return learnerId;
//    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BookingDetails that = (BookingDetails) obj;
        return this.bookingId == that.bookingId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId);
    }

}
