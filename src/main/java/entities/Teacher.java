package entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "Teacher")
public class Teacher {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "teacher_Id", nullable = false)
    private int teacherId;

    @Column (name = "teacher_name", nullable = false)
    private String teacherName;

    @Column (name = "teacher_age")
    private int teacherAge;

    @Column(name = "teacher_kontakt", unique = true)
    private String teacherKontakt;

    @Column(name= "teacher_startDatum")
    private LocalDate teacherStartDatum;

    @Column (name="kursId")
    private int kursId;

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
    public LocalDate getTeacherStartDatum() {
        return teacherStartDatum;
    }
    public void setTeacherStartDatum(LocalDate teacherStartDatum) {
        this.teacherStartDatum = teacherStartDatum;
    }
    public int getKursId() {
        return kursId;
    }
    public void setKursId(int kursId) {
        this.kursId = kursId;
    }

}
