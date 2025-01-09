package crud;

import entities.Grades;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class GradeCRUD {

    private static EntityManager entityManager;

    public GradeCRUD(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addGrade(Grades grade) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(grade);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public static Grades getGrade(int gradeID) {
        return entityManager.find(Grades.class, gradeID);
    }

    public static List<Grades> getAllGrades() {
        return entityManager.createQuery("FROM Grades", Grades.class).getResultList();
    }

    public static void updateGrade(Grades grade) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(grade);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public void deleteGrade(int gradeID) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Grades grade = entityManager.find(Grades.class, gradeID);
            if (grade != null) {
                entityManager.remove(grade);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
