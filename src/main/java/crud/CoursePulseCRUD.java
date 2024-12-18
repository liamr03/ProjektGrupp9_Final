package crud;

import entities.CoursePulse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class CoursePulseCRUD {

    private EntityManager entityManager;

    public CoursePulseCRUD(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addCoursePulse(CoursePulse coursePulse) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(coursePulse);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public CoursePulse getCoursePulse(int pulsID) {
        return entityManager.find(CoursePulse.class, pulsID);
    }

    public List<CoursePulse> getAllCoursePulses() {
        return entityManager.createQuery("FROM CoursePulse", CoursePulse.class).getResultList();
    }

    public void updateCoursePulse(CoursePulse coursePulse) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(coursePulse);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public void deleteCoursePulse(int pulsID) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            CoursePulse coursePulse = entityManager.find(CoursePulse.class, pulsID);
            if (coursePulse != null) {
                entityManager.remove(coursePulse);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
