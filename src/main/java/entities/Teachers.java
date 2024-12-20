package entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Teachers")
public class Teachers {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "TeacherID", nullable = false)
    private int teacherId;

    @Column(name = "TeacherName", nullable = false)
    private String teacherName;

    @Column(name = "Age")
    private int teacherAge;

    @Column(name = "Contact", unique = true)
    private String teacherContact;

    @Column(name = "HireDate")
    private LocalDate teacherStartDate;

    @ManyToOne
    @JoinColumn(name = "CourseID")
    private Course course;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
    public String getTeacherName() {
        return teacherName;
    }
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    public int getTeacherAge() {
        return teacherAge;
    }
    public void setTeacherAge(int teacherAge) {
        this.teacherAge = teacherAge;
    }

    public String getTeacherContact() {
        return teacherContact;
    }

    public void setTeacherContact(String teacherContact) {
        this.teacherContact = teacherContact;
    }

    public LocalDate getTeacherStartDate() {
        return teacherStartDate;
    }

    public void setTeacherStartDate(LocalDate teacherStartDate) {
        this.teacherStartDate = teacherStartDate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
