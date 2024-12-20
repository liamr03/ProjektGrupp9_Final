package repositories;

import crud.TeacherCRUD;
import entities.Teachers;
import jakarta.persistence.EntityManager;

import java.util.List;

public class TeacherRepository {

    private final TeacherCRUD teacherCRUD;

    public TeacherRepository(EntityManager entityManager) {
        this.teacherCRUD = new TeacherCRUD(entityManager);
    }

    // Method to add a teacher
    public void addTeacher(Teachers teacher) {
        teacherCRUD.addTeacher(teacher);
    }

    // Method to get a teacher by its ID
    public Teachers getTeacher(int teacherID) {
        return teacherCRUD.getTeacher(teacherID);
    }

    // Method to get all teachers
    public List<Teachers> getAllTeachers() {
        return teacherCRUD.getAllTeachers();
    }

    // Method to update a teacher
    public void updateTeacher(Teachers teacher) {
        teacherCRUD.updateTeacher(teacher);
    }

    // Method to delete a teacher
    public void deleteTeacher(int teacherID) {
        teacherCRUD.deleteTeacher(teacherID);
    }
}
