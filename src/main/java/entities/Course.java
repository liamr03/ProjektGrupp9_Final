package entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourseId", nullable = false)
    private int courseId;

    @Column(name = "CourseName", nullable = false, unique = true)
    private String courseName;

    @Column(name = "CourseStart")
    private LocalDate courseStart;

    @Column(name = "CourseTeacher")
    private String courseTeacher;

    // Getters and setters for each field
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getCourseStart() {
        return courseStart;
    }

    public void setCourseStart(LocalDate courseStart) {
        this.courseStart = courseStart;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }
}
