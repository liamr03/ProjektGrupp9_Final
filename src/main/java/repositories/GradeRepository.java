package repositories;

import crud.GradeCRUD;
import entities.Grade;
import jakarta.persistence.EntityManager;

import java.util.List;

public class GradeRepository {

    private final GradeCRUD gradeCRUD;

    public GradeRepository(EntityManager entityManager) {
        this.gradeCRUD = new GradeCRUD(entityManager);
    }

    // Method to add a grade
    public void addGrade(Grade grade) {
        gradeCRUD.addGrade(grade);
    }

    // Method to get a grade by its ID
    public Grade getGrade(int gradeID) {
        return gradeCRUD.getGrade(gradeID);
    }

    // Method to get all grades
    public List<Grade> getAllGrades() {
        return gradeCRUD.getAllGrades();
    }

    // Method to update a grade
    public void updateGrade(Grade grade) {
        gradeCRUD.updateGrade(grade);
    }

    // Method to delete a grade
    public void deleteGrade(int gradeID) {
        gradeCRUD.deleteGrade(gradeID);
    }
}
