package repositories;

import crud.CourseCRUD;
import entities.Course;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CourseRepository {

    private final CourseCRUD courseCRUD;

    public CourseRepository(EntityManager entityManager) {
        this.courseCRUD = new CourseCRUD(entityManager);
    }

    // Method to add a course
    public void addCourse(Course course) {
        courseCRUD.addCourse(course);
    }

    // Method to get a course by its ID
    public Course getCourse(int kursID) {
        return courseCRUD.getCourse(kursID);
    }

    // Method to get all courses
    public List<Course> getAllCourses() {
        return courseCRUD.getAllCourses();
    }

    // Method to update a course
    public void updateCourse(Course course) {
        courseCRUD.updateCourse(course);
    }

    // Method to delete a course
    public void deleteCourse(int kursID) {
        courseCRUD.deleteCourse(kursID);
    }
}
