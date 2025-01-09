package entities;

import jakarta.persistence.*;

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

    public String getTeacherStartDate() {
        return teacherStartDate;
    }

    public void setTeacherStartDate(String teacherStartDate) {
        this.teacherStartDate = teacherStartDate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "Teacher ID=" + teacherId +
                ", Teacher Name='" + teacherName + '\'' +
                ", Age=" + teacherAge +
                ", Contact='" + teacherContact + '\'' +
                ", Hire Date=" + (teacherStartDate != null ? teacherStartDate : "null") +
                '}';
    }

    public void setHireDate(String hiredate) {
        this.teacherStartDate = (hiredate);
    }
}
