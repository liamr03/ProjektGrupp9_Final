import crud.*;
import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static EntityManagerFactory emf;
    private static EntityManager em;

    private static StudentCRUD studentCRUD;
    private static CourseCRUD courseCRUD;
    private static GradeCRUD gradeCRUD;
    private static CourseFeedbackCRUD courseFeedbackCRUD;
    private static TeacherCRUD teacherCRUD;

    public static void main(String[] args) {
        // Initialize EntityManagerFactory and EntityManager
        emf = Persistence.createEntityManagerFactory("educationPU");
        em = emf.createEntityManager();

        // Initialize CRUD instances
        studentCRUD = new StudentCRUD(em);
        courseCRUD = new CourseCRUD(em);
        gradeCRUD = new GradeCRUD(em);
        courseFeedbackCRUD = new CourseFeedbackCRUD(em);

        // Start the menu
        startMenu();

        // Close resources
        em.close();
        emf.close();
    }

    private static void startMenu() {
        while (true) {
            System.out.println("\n--- Education Management System ---");
            System.out.println("1. Add Course (HT/VT)");
            System.out.println("2. Add Students");
            System.out.println("3. Add Teachers");
            System.out.println("4. Grade Student");
            System.out.println("5. Add Student Feedback for Courses");
            System.out.println("6. View Students");
            System.out.println("7. View Grades");
            System.out.println("8. View Teachers");
            System.out.println("9. Update Grade");
            System.out.println("10. Remove Course");
            System.out.println("11. Remove Teacher");
            System.out.println("12. Remove Student");
            System.out.println("13. Average Student Grade (all courses)");
            System.out.println("14. View Course Pulse Average");
            System.out.println("15. Exit Program");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addCourse();
                case 2 -> addStudent();
                case 3 -> addTeacher();
                case 4 -> gradeStudent();
                case 5 -> addStudentFeedback();
                case 6 -> viewAllStudents();
                case 7 -> viewAllGrades();
                case 8 -> viewAllTeachers();
                case 9 -> updateGrade();
                case 10 -> removeCourse();
                case 11 -> removeTeacher();
                case 12 -> removeStudent();
                case 13 -> averageStudentGrade();
                case 14 -> viewCoursePulseAverage();
                case 15 -> viewAllCourses();
                case 16 -> {
                    System.out.println("Exiting program...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void viewAllCourses() {
        courseCRUD.getAllCourses().forEach(course ->
                        System.out.println(course.getCourseId() + ". " + course.getCourseName())

                //Press enter to continue needs to be added here
        );
    }

    // CRUD operations for Students
    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter student contact information (Email): ");
        String contact = scanner.nextLine();

        // Choose a course for the student
        System.out.println("Select a course for the student:");
        courseCRUD.getAllCourses().forEach(course -> System.out.println(course.getCourseId() + ". " + course.getCourseName()));
        int courseId = scanner.nextInt();
        scanner.nextLine();
        Course course = courseCRUD.getCourse(courseId);

        //Select StartDate (Not yet implemented) (HT/VT)

        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        student.setContact(contact);
        student.setCourse(course);  // Assign course to student

        studentCRUD.addStudent(student);
        System.out.println("Student added successfully!");

        pause(2000);  // Pause for 2 seconds
    }

    private static void viewAllStudents() {
        studentCRUD.getAllStudents().forEach(System.out::println);
        //Enter to continue (Not yet implemented)
    }

    private static void removeStudent() {
        System.out.print("Enter student ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        studentCRUD.deleteStudent(id);
        System.out.println("Student removed successfully!");
        pause(2000);  // Pause for 2 seconds
    }

    private static void addCourse() {
        System.out.print("Enter course name (HT/VT): ");
        String name = scanner.nextLine();

        Course course = new Course();
        course.setCourseName(name);

        courseCRUD.addCourse(course);
        System.out.println("Course added successfully!");

        pause(2000);  // Pause for 2 seconds
    }

    private static void addTeacher() {
        System.out.print("Enter teacher name: ");
        String name = scanner.nextLine();

        Teachers teacher = new Teachers();
        teacher.setTeacherName(name);

        teacherCRUD.addTeacher(teacher);
        System.out.println("Teacher added successfully! ");

        pause(2000);  // Pause for 2 seconds
    }

    private static void gradeStudent() {
        // Implement logic for grading a student
        System.out.println("Grade student functionality not implemented.");

        pause(2000);  // Pause for 2 seconds
    }

    private static void addStudentFeedback() {
        System.out.print("Enter feedback rating (1-5): ");
        int feedbackRating = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter course ID: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        CourseFeedback courseFeedback = new CourseFeedback();
        courseFeedback.setFeedbackGrade(feedbackRating);
        courseFeedback.setCourse(courseCRUD.getCourse(courseId));
        courseFeedback.setStudent(studentCRUD.getStudent(studentId));

        courseFeedbackCRUD.addCourseFeedback(courseFeedback);
        System.out.println("Course feedback added successfully!");

        pause(2000);  // Pause for 2 seconds
    }

    private static void viewAllGrades() {
        gradeCRUD.getAllGrades().forEach(System.out::println);

        //Enter to continue (Not yet implemented)
    }

    private static void updateGrade() {
        System.out.print("Enter grade ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Grades grade = gradeCRUD.getGrade(id);
        if (grade == null) {
            System.out.println("Grade not found.");
            return;
        }

        System.out.print("Enter new grade value (1-5): ");
        int newValue = scanner.nextInt();
        scanner.nextLine();

        try {
            grade.setValue(newValue);
            gradeCRUD.updateGrade(grade);
            System.out.println("Grade updated successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid grade value: " + e.getMessage());
        }

        pause(2000);  // Pause for 2 seconds
    }

    private static void removeCourse() {
        System.out.print("Enter course ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        courseCRUD.deleteCourse(id);
        System.out.println("Course removed successfully!");

        pause(2000);  // Pause for 2 seconds
    }

    private static void removeTeacher() {
        System.out.print("Enter teacher ID to remove: ");
        int teacherId = scanner.nextInt();
        scanner.nextLine();

        Teachers teacher = TeacherCRUD.getTeacher(teacherId); // Use the instance of teacherCRUD
        if (teacher == null) {
            System.out.println("Teacher not found.");
            return;
        }

        TeacherCRUD.deleteTeacher(teacherId);
        System.out.println("Teacher removed successfully!");

        pause(2000);  // Pause for 2 seconds
    }


    private static void averageStudentGrade() {
        // Implement logic to calculate average student grade for all courses
        System.out.println("Average student grade functionality not implemented.");

        //Enter to continue (Not yet implemented)
    }

    private static void viewCoursePulseAverage() {
        // Implement logic to view course pulse average
        System.out.println("View course pulse average functionality not implemented.");

        //Enter to continue (Not yet implemented)
    }

    private static void viewAllTeachers() {
        TeacherCRUD.getAllTeachers().forEach(System.out::println); // Use the instance of teacherCRUD

        //Enter to continue (Not yet implemented)
    }

    private static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
