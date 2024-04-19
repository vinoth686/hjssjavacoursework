import java.util.ArrayList;
public class ReviewDetails {
    private String userName;
    private String coachName;
    private int rating;
    private String comment;
    private int reviewMonth;

    public ReviewDetails(String userName, String coachName, int rating, String comment, int reviewMonth) {
        this.userName = userName;
        this.coachName = coachName;
        this.rating = rating;
        this.comment = comment;
        this.reviewMonth = reviewMonth;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getReviewMonth() {
        return reviewMonth;
    }

    public void setReviewMonth(int reviewMonth) {
        this.reviewMonth = reviewMonth;
    }
}
