package repositories;

import crud.CoursePulseCRUD;
import entities.CoursePulse;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CoursePulseRepository {

    private CoursePulseCRUD coursePulseCRUD;

    public CoursePulseRepository(EntityManager entityManager) {
        this.coursePulseCRUD = new CoursePulseCRUD(entityManager);
    }

    // Method to add a course pulse
    public void addCoursePulse(CoursePulse coursePulse) {
        coursePulseCRUD.addCoursePulse(coursePulse);
    }

    // Method to get a course pulse by its ID
    public CoursePulse getCoursePulse(int pulsID) {
        return coursePulseCRUD.getCoursePulse(pulsID);
    }

    // Method to get all course pulses
    public List<CoursePulse> getAllCoursePulses() {
        return coursePulseCRUD.getAllCoursePulses();
    }

    // Method to update a course pulse
    public void updateCoursePulse(CoursePulse coursePulse) {
        coursePulseCRUD.updateCoursePulse(coursePulse);
    }

    // Method to delete a course pulse
    public void deleteCoursePulse(int pulsID) {
        coursePulseCRUD.deleteCoursePulse(pulsID);
    }
}
