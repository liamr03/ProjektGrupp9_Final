package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Grades")
public class Grades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GradeID", nullable = false)
    private int id;

    @Column(name = "Grade", nullable = false)
    private int value;

    @ManyToOne
    @JoinColumn(name = "TeacherID")
    private Teachers teacher;

    @ManyToOne
    @JoinColumn(name = "CourseID")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "StudentID")
    private Student student;  // Corrected import for Student

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
            throw new IllegalArgumentException("Grade must be between 1-5");
        }
        this.value = value;
    }

    public Teachers getTeacher() {
        return teacher;
    }

    public void setTeacher(Teachers teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {  // Corrected getter for Student
        return student;
    }

    public void setStudent(Student student) {  // Corrected setter for Student
        this.student = student;
    }
}
