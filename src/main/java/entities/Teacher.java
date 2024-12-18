package entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Lärare")
public class Teacher {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "LärarID", nullable = false)
    private int teacherId;

    @Column(name = "LärarNamn", nullable = false)
    private String teacherName;

    @Column(name = "Ålder")
    private int teacherAge;

    @Column(name = "Kontakt", unique = true)
    private String teacherKontakt;

    @Column(name = " AnställningsDatum")
    private LocalDate teacherStartDate;

    @ManyToOne
    @JoinColumn(name = "KursID")
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
    public String getTeacherKontakt() {
        return teacherKontakt;
    }
    public void setTeacherKontakt(String teacherKontakt) {
        this.teacherKontakt = teacherKontakt;
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
