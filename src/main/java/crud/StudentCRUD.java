package crud;

import com.example.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class StudentCRUD {
    private final EntityManager entityManager;

    // Constructor that accepts an EntityManager
    public StudentCRUD(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addStudent(Student student) {
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
    }

    public Student getStudent(int id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> getAllStudents() {
        String query = "SELECT s FROM Student s";
        TypedQuery<Student> typedQuery = entityManager.createQuery(query, Student.class);
        return typedQuery.getResultList();
    }

    public void updateStudent(Student student) {
        entityManager.getTransaction().begin();
        entityManager.merge(student);
        entityManager.getTransaction().commit();
    }

    public void deleteStudent(int id) {
        entityManager.getTransaction().begin();
        Student student = getStudent(id);
        if (student != null) {
            entityManager.remove(student);
        }
        entityManager.getTransaction().commit();
    }
}
