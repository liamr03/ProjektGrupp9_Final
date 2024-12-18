package crud;

import entities.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class TeacherCRUD {

    private EntityManager entityManager;

    public TeacherCRUD(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addTeacher(Teacher teacher) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(teacher);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public Teacher getTeacher(int teacherID) {
        return entityManager.find(Teacher.class, teacherID);
    }

    public List<Teacher> getAllTeachers() {
        return entityManager.createQuery("FROM Teacher", Teacher.class).getResultList();
    }

    public void updateTeacher(Teacher teacher) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(teacher);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public void deleteTeacher(int teacherID) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Teacher teacher = entityManager.find(Teacher.class, teacherID);
            if (teacher != null) {
                entityManager.remove(teacher);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
