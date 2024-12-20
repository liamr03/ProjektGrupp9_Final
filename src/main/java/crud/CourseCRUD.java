package crud;

import entities.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class CourseCRUD {

    private EntityManager entityManager;

    public CourseCRUD(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addCourse(Course course) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(course);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public Course getCourse(int courseID) {
        return entityManager.find(Course.class, courseID);
    }

    public List<Course> getAllCourses() {
        return entityManager.createQuery("FROM Course", Course.class).getResultList();
    }

    public void updateCourse(Course course) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(course);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public void deleteCourse(int courseID) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Course course = entityManager.find(Course.class, courseID);
            if (course != null) {
                entityManager.remove(course);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
