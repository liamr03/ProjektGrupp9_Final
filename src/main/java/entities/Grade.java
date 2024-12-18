package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Betygs")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BetygsID", nullable = false)
    private int id;

    @Column(name = "Betyg", nullable = false)
    private int value;

    @ManyToOne
    @JoinColumn(name = "LärarID")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "KursID")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "StudentID")
    private com.example.entity.Student student;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (value < 1 || value > 5) {
            throw new IllegalArgumentException("Betyg måste vara mellan 1 och 5");
        }
        this.value = value;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public com.example.entity.Student getStudent() {
        return student;
    }

    public void setStudent(com.example.entity.Student student) {
        this.student = student;
    }
}
