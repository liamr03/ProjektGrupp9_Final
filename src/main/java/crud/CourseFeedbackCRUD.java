package crud;

import entities.CourseFeedback;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class CourseFeedbackCRUD {

    private static EntityManager entityManager;

    public CourseFeedbackCRUD(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addCourseFeedback(CourseFeedback courseFeedback) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(courseFeedback);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public CourseFeedback getCourseFeedback(int feedbackID) {
        return entityManager.find(CourseFeedback.class, feedbackID);
    }

    public List<CourseFeedback> getAllCourseFeedbacks() {
        return entityManager.createQuery("FROM CourseFeedback", CourseFeedback.class).getResultList();
    }

    public void updateCourseFeedback(CourseFeedback courseFeedback) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(courseFeedback);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public static void deleteCourseFeedback(int feedbackID) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            CourseFeedback courseFeedback = entityManager.find(CourseFeedback.class, feedbackID);
            if (courseFeedback != null) {
                entityManager.remove(courseFeedback);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
