package crud;

import entities.Grade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class GradeCRUD {

    private EntityManager entityManager;

    public GradeCRUD(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addGrade(Grade grade) {
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

    public Grade getGrade(int gradeID) {
        return entityManager.find(Grade.class, gradeID);
    }

    public List<Grade> getAllGrades() {
        return entityManager.createQuery("FROM Grade", Grade.class).getResultList();
    }

    public void updateGrade(Grade grade) {
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
            Grade grade = entityManager.find(Grade.class, gradeID);
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
