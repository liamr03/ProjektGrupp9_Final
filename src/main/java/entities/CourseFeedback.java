package entities;

import jakarta.persistence.*;

@Entity
public class CourseFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generated primary key
    private int feedbackID;  // Changed to "feedbackID" for clarity

    @Column(name = "FeedbackGrade")
    private int feedbackGrade;  // Rating (from 1 to 5)

    @ManyToOne
    @JoinColumn(name = "CourseID", referencedColumnName = "CourseID", nullable = false)
    private Course course;  // Many feedback entries can be associated with one Course

    @ManyToOne
    @JoinColumn(name = "StudentID", referencedColumnName = "StudentID", nullable = false)
    private Student student;  // Many feedback entries can be associated with one Student

    // Getters and setters
    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public int getFeedbackGrade() {
        return feedbackGrade;
    }

    public void setFeedbackGrade(int feedbackGrade) {
        this.feedbackGrade = feedbackGrade;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
