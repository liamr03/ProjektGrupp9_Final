package repositories;

import crud.TeacherCRUD;
import entities.Teacher;
import jakarta.persistence.EntityManager;

import java.util.List;

public class TeacherRepository {

    private final TeacherCRUD teacherCRUD;

    public TeacherRepository(EntityManager entityManager) {
        this.teacherCRUD = new TeacherCRUD(entityManager);
    }

    // Method to add a teacher
    public void addTeacher(Teacher teacher) {
        teacherCRUD.addTeacher(teacher);
    }

    // Method to get a teacher by its ID
    public Teacher getTeacher(int teacherID) {
        return teacherCRUD.getTeacher(teacherID);
    }

    // Method to get all teachers
    public List<Teacher> getAllTeachers() {
        return teacherCRUD.getAllTeachers();
    }

    // Method to update a teacher
    public void updateTeacher(Teacher teacher) {
        teacherCRUD.updateTeacher(teacher);
    }

    // Method to delete a teacher
    public void deleteTeacher(int teacherID) {
        teacherCRUD.deleteTeacher(teacherID);
    }
}
