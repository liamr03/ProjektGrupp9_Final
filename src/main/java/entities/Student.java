package entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StudentID")
    private int id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Age")
    private int age;

    @Column(name = "Contact", unique = true)
    private String contact;

    @Column(name = "EnrollmentDate")
    private LocalDate startDate;

    @ManyToOne
    @JoinColumn(name = "GradeID")
    private Grades grade;

    @ManyToOne
    @JoinColumn(name = "CourseID")
    private Course course;

    // Getters and setters for each field
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Grades getGrade() {
        return grade;
    }

    public void setGrade(Grades grade) {
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", contact='" + contact + '\'' +
                ", startDate=" + (startDate != null ? startDate : "null") +
                ", course=" + (course != null ? course.getCourseName() : "null") +
                ", grade=" + (grade != null ? grade.getValue() : "null") +
                '}';
    }

}
