package repositories;

import crud.CourseFeedbackCRUD;
import entities.CourseFeedback;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CourseFeedbackRepository {

    private final CourseFeedbackCRUD CourseFeedbackCRUD;

    public CourseFeedbackRepository(EntityManager entityManager) {
        this.CourseFeedbackCRUD = new CourseFeedbackCRUD(entityManager);
    }

    // Method to add a course pulse
    public void addCourseFeedback(CourseFeedback CourseFeedback) {
        CourseFeedbackCRUD.addCourseFeedback(CourseFeedback);
    }

    // Method to get a course pulse by its ID
    public CourseFeedback getCourseFeedback(int pulsID) {
        return CourseFeedbackCRUD.getCourseFeedback(pulsID);
    }

    // Method to get all course pulses
    public List<CourseFeedback> getAllCourseFeedbacks() {
        return CourseFeedbackCRUD.getAllCourseFeedbacks();
    }

    // Method to update a course pulse
    public void updateCourseFeedback(CourseFeedback CourseFeedback) {
        CourseFeedbackCRUD.updateCourseFeedback(CourseFeedback);
    }

    // Method to delete a course pulse
    public void deleteCourseFeedback(int pulsID) {
        CourseFeedbackCRUD.deleteCourseFeedback(pulsID);
    }
}
