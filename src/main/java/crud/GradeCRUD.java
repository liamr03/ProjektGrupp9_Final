package crud;

import entities.Course;
import entities.Grades;
import entities.Student;
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

    public List<Grades> getGradesByStudent(Student student) {
        try {
            return entityManager.createQuery("SELECT g FROM Grades g WHERE g.student = :student", Grades.class)
                    .setParameter("student", student)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Grades> getGradesByCourse(Course course) {
        try {
            return entityManager.createQuery("SELECT g FROM Grades g WHERE g.course = :course", Grades.class)
                    .setParameter("course", course)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
