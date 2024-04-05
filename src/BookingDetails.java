public class BookingDetails {
    private static int nextBookingId = 1;

    private final int bookingId;
    private final String userName;
    private final int userGrade;
    private final String day;
    private final String timeSlot;
    private String status;

    public BookingDetails(String userName, int userGrade, String day, String timeSlot, String status    ) {
        this.bookingId = nextBookingId++;
        this.userName = userName;
        this.userGrade = userGrade;
        this.day = day;
        this.timeSlot = timeSlot;
        this.status = "booked";
    }
    
    public int getBookingId() {
        return bookingId;
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

    public void setStatus(String status) {
        this.status = status;
    }

}