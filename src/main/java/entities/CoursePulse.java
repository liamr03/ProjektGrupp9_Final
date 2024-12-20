package entities;

import jakarta.persistence.*;

@Entity
public class CoursePulse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generated primary key
    private int pulsID;

    @Column(name = "PulsBetyg")
    private int pulsBetyg;  // Rating (from 1 to 5)

    @ManyToOne
    @JoinColumn(name = "KursID", referencedColumnName = "KursID", nullable = false)
    private Course course;  // Many CoursePuls can be associated with one Course

    @ManyToOne
    @JoinColumn(name = "StudentID", referencedColumnName = "StudentID", nullable = false)
    private Student student;  // Many CoursePuls can be associated with one Student

    // Getters and setters
    public int getPulsID() {
        return pulsID;
    }

    public void setPulsID(int pulsID) {
        this.pulsID = pulsID;
    }

    public int getPulsBetyg() {
        return pulsBetyg;
    }

    public void setPulsBetyg(int pulsBetyg) {
        this.pulsBetyg = pulsBetyg;
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
