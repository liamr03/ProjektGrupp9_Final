package crud;

import entities.Teachers;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class TeacherCRUD {

    private static EntityManager entityManager;

    public TeacherCRUD(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public static Teachers getTeacher(int teacherID) {
        return entityManager.find(Teachers.class, teacherID);
    }

    public static List<Teachers> getAllTeachers() {
        return entityManager.createQuery("FROM Teachers", Teachers.class).getResultList();
    }

    public static void deleteTeacher(int teacherID) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entities.Teachers teacher = entityManager.find(Teachers.class, teacherID);
            if (teacher != null) {
                entityManager.remove(teacher);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public void addTeacher(Teachers teacher) {
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

    public void updateTeacher(Teachers teacher) {
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
}
