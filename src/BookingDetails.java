import java.util.Objects;

public class BookingDetails {
    private static int nextBookingId = 1;
    private final int bookingId;
    private final String userName;
    private final int userGrade;
    private String day;
    private String timeSlot;
    private String status;
    private int learnerId;
    private String coachName;

    public BookingDetails(String userName, int userGrade, String day, String timeSlot, String coachName, String status) {
        this.bookingId = nextBookingId++;
        this.userName = userName;
        this.userGrade = userGrade;
        this.day = day;
        this.timeSlot = timeSlot;
        this.learnerId = learnerId;
        this.status = "booked";
        this.coachName = coachName;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getCoachName() {
        return coachName;
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