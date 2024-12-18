package crud;

import com.example.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class StudentCRUD {

    @PersistenceContext
    private EntityManager entityManager;

    public void addStudent(Student student) {
        entityManager.persist(student);
    }

    public Student getStudent(int id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> getAllStudents() {
        return entityManager.createQuery("FROM Student", Student.class).getResultList();
    }

    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    public void deleteStudent(int id) {
        Student student = getStudent(id);
        if (student != null) {
            entityManager.remove(student);
        }
    }
}
