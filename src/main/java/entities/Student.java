package com.example.entity;

import entities.Course;
import entities.Grade;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Studenter")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StudentID")
    private int id;

    @Column(name = "Namn", nullable = false)
    private String name;

    @Column(name = "Ålder")
    private int age;

    @Column(name = "Kontakt", unique = true)
    private String contact;

    @Column(name = "Skolstart")
    private LocalDate startDate;

    @ManyToOne
    @JoinColumn(name = "BetygID")
    private Grade grade;

    @ManyToOne
    @JoinColumn(name = "KursID")
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

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
