import crud.*;
import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.InputMismatchException;
import java.util.List;
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
        teacherCRUD = new TeacherCRUD(em);

        // Start the menu
        startMenu();

        // Close resources
        em.close();
        emf.close();
    }

    private static void startMenu() {
        while (true) {
            System.out.println("1. Add Menu");
            System.out.println("2. View Menu");
            System.out.println("3. Update Menu");
            System.out.println("4. Remove Menu");
            System.out.println("5. Statistics Menu");
            System.out.println("6. Exit Program");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> addMenu();
                    case 2 -> viewMenu();
                    case 3 -> updateMenu();
                    case 4 -> removeMenu();
                    case 5 -> statisticMenu();
                    case 6 -> {
                        System.out.println("Exiting program...");
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }


    private static void addMenu() {
        while (true) {
            System.out.println("1. Add Course");
            System.out.println("2. Add Students");
            System.out.println("3. Add Teachers");
            System.out.println("4. Add Grade");
            System.out.println("5. Add Course Feedback");
            System.out.println("6. Back to main menu");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> addCourse();
                    case 2 -> addStudent();
                    case 3 -> addTeacher();
                    case 4 -> gradeStudent();
                    case 5 -> addStudentFeedback();
                    case 6 -> startMenu();
                    default -> System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
    }

    private static void viewMenu() {
        while (true) {
            System.out.println("1. View Students");
            System.out.println("2. View Grades");
            System.out.println("3. View Teachers");
            System.out.println("4. View Courses");
            System.out.println("5. Go Back to Main Menu");
            System.out.println("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> viewAllStudents();
                    case 2 -> viewAllGrades();
                    case 3 -> viewAllTeachers();
                    case 4 -> viewAllCourses();
                    case 5 -> startMenu();
                    default -> System.out.println("Invalid choice. Try again");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void updateMenu() {

        while (true) {
            System.out.println("1. Update Students");
            System.out.println("2. Update Grades");
            System.out.println("3. Update Teachers");
            System.out.println("4. Update Courses");
            System.out.println("5. Go Back to Main Menu");
            System.out.println("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> updateStudent();
                    case 2 -> updateGrade();
                    case 3 -> updateTeacher();
                    case 4 -> updateCourse();
                    case 5 -> startMenu();
                    default -> System.out.println("Invalid choice. Try again");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void removeMenu() {

        while (true) {
            System.out.println("1. Remove Course Feedback");
            System.out.println("2. Go Back to Main Menu");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> removeCourseFeedback();
                case 2 -> startMenu();
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void statisticMenu() {

        while (true) {
            System.out.println("1. Average Grade For All Students");
            System.out.println("2. View Course Pulse Average");
            System.out.println("3. View Courses With Students");
            System.out.println("4. View Students With Grades");
            System.out.println("5. View Average Grade For Each Student");
            System.out.println("6. Go Back to Main Menu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> averageSchoolGrade();
                case 2 -> viewCoursePulseAverage();
                case 3 -> viewCoursePulsePerCourse();
                case 4 -> viewStudentsWithCoursesAndGrades();
                case 5 -> viewAverageGradeForEachStudent();
                case 6 -> startMenu();
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void viewCoursePulsePerCourse() {
        // Fetch all courses
        List<Course> courses = courseCRUD.getAllCourses();

        if (courses.isEmpty()) {
            System.out.println("No course data available.");
            return;
        }

        for (Course course : courses) {
            List<Grades> courseGrades = gradeCRUD.getGradesByCourse(course);

            if (courseGrades.isEmpty()) {
                System.out.println("Course " + course.getCourseName() + " has no grades.");
                continue;
            }

            double total = 0;
            int count = 0;

            for (Grades grade : courseGrades) {
                total += grade.getValue();
                count++;
            }

            double averageGrade = total / count;
            System.out.printf("Course: %s, Average Grade: %.2f, Student Count: %d\n",
                    course.getCourseName(), averageGrade, count);
        }

        waitForEnter(); // Wait for enter before continuing
    }


    private static void viewAverageGradeForEachStudent() {
        List<Student> students = studentCRUD.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            List<Grades> studentGrades = gradeCRUD.getGradesByStudent(student);

            if (studentGrades.isEmpty()) {
                System.out.println("Student " + student.getName() + " has no grades.");
                continue;
            }

            double total = 0;
            int count = 0;

            for (Grades grade : studentGrades) {
                total += grade.getValue();
                count++;
            }

            double averageGrade = total / count;

            System.out.printf("Student: %s, Average Grade: %.2f%n", student.getName(), averageGrade);
        }
        waitForEnter();
    }

    private static void viewStudentsWithCoursesAndGrades() {
        // Get all students from StudentCRUD
        List<Student> students = studentCRUD.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        // Iterate through each student and fetch their courses and grades
        for (Student student : students) {
            List<Grades> studentGrades = gradeCRUD.getGradesByStudent(student);

            if (studentGrades.isEmpty()) {
                System.out.println("Student " + student.getName() + " has no grades.");
                continue;
            }

            System.out.println("Student: " + student.getName());

            // Iterate through each grade of the student
            for (Grades grade : studentGrades) {
                Course course = grade.getCourse(); // Assuming Grades has a 'getCourse' method

                System.out.printf("Course: %s, Grade: %d%n", course.getCourseName(), grade.getValue());
            }
        }

        waitForEnter(); // Wait for user input before continuing
    }


    private static void removeCourseFeedback() {
        // Fetch all course feedback from the CRUD method
        List<CourseFeedback> allFeedback = courseFeedbackCRUD.getAllCourseFeedbacks();

        if (allFeedback.isEmpty()) {
            System.out.println("No course feedback found.");
            return;
        }

        System.out.println("Course Feedback List:");
        for (CourseFeedback feedback : allFeedback) {
            System.out.printf("ID: %d, Course: %s, Feedback: %s%n", feedback.getFeedbackID(), feedback.getCourse().getCourseName(), feedback.getFeedbackGrade());
        }

        try {
            System.out.print("Enter Course Feedback ID to remove: ");
            int feedbackID = scanner.nextInt();
            scanner.nextLine(); // Consume the newline left by nextInt()

            // Call the CRUD method to delete the feedback
            CourseFeedbackCRUD.deleteCourseFeedback(feedbackID); // Assuming this is an instance method, not static
            System.out.println("Course Feedback removed successfully!");

            waitForEnter(); // Wait for Enter before continuing
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); // Consume the invalid input to prevent infinite loop
        }
    }


    private static void viewAllCourses() {
        List<Course> courses = courseCRUD.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            courses.forEach(course ->
                    System.out.println("ID: " + course.getCourseId() + ", Name: " + course.getCourseName())
            );
        }
        waitForEnter(); // Wait for Enter before continuing
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
        System.out.print("Enter student enrollment date: ");
        String enrollmentdate = scanner.nextLine();


        // Choose a course for the student
        System.out.println("Select a course for the student:");
        courseCRUD.getAllCourses().forEach(course -> System.out.println(course.getCourseId() + ". " + course.getCourseName()));
        int courseId = scanner.nextInt();
        scanner.nextLine();
        Course course = courseCRUD.getCourse(courseId);

        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        student.setContact(contact);
        student.setCourse(course);  // Assign course to student
        student.setStartDate(enrollmentdate);

        studentCRUD.addStudent(student);
        System.out.println("Student added successfully!");

        waitForEnter(); // Wait for Enter before continuing
    }

    private static void viewAllStudents() {
        System.out.println("\n--- List of All Students ---");


        List<Student> students = studentCRUD.getAllStudents();


        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        students.forEach(student -> {
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println("Contact: " + student.getContact());
            System.out.println("Course: " + (student.getCourse() != null ? student.getCourse().getCourseName() : "None"));
            System.out.println("Enrollment Date: " + (student.getStartDate() != null ? student.getStartDate() : "None"));
            System.out.println("-------------------------------------");


        });
    }

    private static void addCourse() {
        System.out.print("Enter course name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Course Start: ");
        String courseStart = scanner.nextLine();

        System.out.print("Enter Course Teacher: ");
        String courseTeacher = scanner.nextLine();

        Course course = new Course();
        course.setCourseName(name);
        course.setCourseStart(courseStart);
        course.setCourseTeacher(courseTeacher);

        courseCRUD.addCourse(course);
        System.out.println("Course added successfully!");

        waitForEnter(); // Wait for Enter before continuing
    }

    private static void addTeacher() {
        System.out.print("Enter teacher name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Contact: ");
        String contact = scanner.nextLine();

        System.out.print("Enter HireDate: ");
        String hiredate = scanner.nextLine();

        Teachers teacher = new Teachers();
        teacher.setTeacherName(name);
        teacher.setTeacherAge(age);
        teacher.setTeacherContact(contact);
        teacher.setHireDate(hiredate);

        teacherCRUD.addTeacher(teacher);
        System.out.println("Teacher added successfully! ");

        waitForEnter(); // Wait for Enter before continuing
    }

    private static void gradeStudent() {
        // Fetch and display all students
        List<Student> allStudents = studentCRUD.getAllStudents();
        if (allStudents.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("Students List:");
        for (Student student : allStudents) {
            System.out.printf("Student ID: %d, Name: %s, Age: %d, Contact: %s%n",
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    student.getContact());
        }

        // Fetch and display all courses
        List<Course> allCourses = courseCRUD.getAllCourses();
        if (allCourses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        System.out.println("Courses List:");
        for (Course course : allCourses) {
            System.out.printf("Course ID: %d, Name: %s%n",
                    course.getCourseId(),
                    course.getCourseName());
        }

        // Fetch and display all teachers
        List<Teachers> allTeachers = TeacherCRUD.getAllTeachers();
        if (allTeachers.isEmpty()) {
            System.out.println("No teachers found.");
            return;
        }

        System.out.println("Teachers List:");
        for (Teachers teacher : allTeachers) {
            System.out.printf("Teacher ID: %d, Name: %s, Age: %d, Contact: %s, Hire Date: %s%n",
                    teacher.getTeacherId(),
                    teacher.getTeacherName(),
                    teacher.getTeacherAge(),
                    teacher.getTeacherContact(),
                    teacher.getTeacherStartDate());
        }

        // Prompt user for IDs and grade value
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();

        System.out.print("Enter course ID: ");
        int courseId = scanner.nextInt();

        System.out.print("Enter teacher ID: ");
        int teacherId = scanner.nextInt();

        System.out.print("Enter the student's grade (1-5): ");
        int gradeValue = scanner.nextInt();

        // Retrieve the corresponding entities
        Student student = studentCRUD.getStudent(studentId);
        Course course = courseCRUD.getCourse(courseId);
        Teachers teacher = TeacherCRUD.getTeacher(teacherId);

        // Validate the IDs
        if (student == null || course == null || teacher == null) {
            System.out.println("Invalid student, course, or teacher ID.");
            return;
        }

        // Create and assign the grade
        Grades grade = new Grades();
        grade.setStudent(student);
        grade.setCourse(course);
        grade.setTeacher(teacher);
        grade.setValue(gradeValue);

        // Save the grade
        gradeCRUD.addGrade(grade);
        System.out.println("Grade added successfully!");

        waitForEnter(); // Wait for Enter before continuing
    }


    private static void addStudentFeedback() {
        try {

            System.out.print("Enter course ID: ");
            int courseId = scanner.nextInt();
            scanner.nextLine();


            Course course = courseCRUD.getCourse(courseId);
            if (course == null) {
                System.out.println("Invalid course ID. Please try again.");
                return;
            }

            System.out.print("Enter feedback rating (1-5): ");
            int feedbackRating = scanner.nextInt();
            scanner.nextLine();

            if (feedbackRating < 1 || feedbackRating > 5) {
                System.out.println("Invalid feedback rating. Please enter a value between 1 and 5.");
                return;
            }

            System.out.print("Enter student ID: ");
            int studentId = scanner.nextInt();
            scanner.nextLine();


            Student student = studentCRUD.getStudent(studentId);
            if (student == null) {
                System.out.println("Invalid student ID. Please try again.");
                return;
            }

            CourseFeedback courseFeedback = new CourseFeedback();
            courseFeedback.setFeedbackGrade(feedbackRating);
            courseFeedback.setCourse(course);
            courseFeedback.setStudent(student);

            courseFeedbackCRUD.addCourseFeedback(courseFeedback);
            System.out.println("Course feedback added successfully!");

            waitForEnter(); // Wait for Enter before continuing();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid numbers for feedback rating, course ID, and student ID.");
            scanner.nextLine();

        }
    }

    private static void viewAllGrades() {
        List<Grades> grades = gradeCRUD.getAllGrades();
        if (grades.isEmpty()) {
            System.out.println("No grades found.");
        } else {
            grades.forEach(System.out::println);
        }
        waitForEnter(); // Wait for Enter before continuing
    }

    private static void updateGrade() {
        // Fetch all grades from the CRUD method
        List<Grades> allGrades = GradeCRUD.getAllGrades();

        if (allGrades.isEmpty()) {
            System.out.println("No grades found.");
            return;
        }

        // Display the list of grades
        System.out.println("Grades List:");
        for (Grades grade : allGrades) {
            System.out.printf("ID: %d, Course: %s, Student: %s, Grade: %d%n",
                    grade.getId(),
                    grade.getCourse().getCourseName(),
                    grade.getStudent().getName(),
                    grade.getValue());
        }

        try {
            System.out.print("Enter grade ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Grades grade = GradeCRUD.getGrade(id); // Ensure consistent capitalization (GradeCRUD)
            if (grade == null) {
                System.out.println("Grade not found.");
                return;
            }

            System.out.print("Enter new grade value (1-5): ");
            int newValue = scanner.nextInt();
            scanner.nextLine();

            if (newValue < 1 || newValue > 5) {
                throw new IllegalArgumentException("Grade value must be between 1 and 5.");
            }

            grade.setValue(newValue);
            GradeCRUD.updateGrade(grade);
            System.out.println("Grade updated successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); // Clear the invalid input
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid grade value: " + e.getMessage());
        }

        waitForEnter(); // Wait for Enter before continuing
    }


    private static void updateCourse() {
        // Fetch all courses from the CRUD method
        List<Course> allCourses = CourseCRUD.getAllCourses();

        if (allCourses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        // Display the list of courses
        System.out.println("Courses List:");
        for (Course course : allCourses) {
            System.out.printf("ID: %d, Name: %s, Teacher: %s%n",
                    course.getCourseId(),
                    course.getCourseName(),
                    course.getCourseTeacher());
        }

        try {
            System.out.print("Enter the Course ID to update: ");
            int courseId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Course course = CourseCRUD.getCourse(courseId);
            if (course == null) {
                System.out.println("Course not found.");
                return;
            }

            System.out.print("Enter new course name (current: " + course.getCourseName() + "): ");
            String newName = scanner.nextLine();

            if (newName.isBlank()) {
                System.out.println("Course name cannot be empty.");
                return;
            }

            course.setCourseName(newName);
            CourseCRUD.updateCourse(course); // Update the course
            System.out.println("Course updated successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid Course ID.");
            scanner.nextLine(); // Clear invalid input
        }

        waitForEnter(); // Wait for Enter before continuing
    }


    private static void updateTeacher() {
        // Fetch all teachers from the CRUD method
        List<Teachers> allTeachers = TeacherCRUD.getAllTeachers();

        if (allTeachers.isEmpty()) {
            System.out.println("No teachers found.");
            return;
        }

        // Display the list of teachers
        System.out.println("Teachers List:");
        for (Teachers teacher : allTeachers) {
            System.out.printf("ID: %d, Name: %s, Age: %d, Contact: %s, Hire Date: %s%n",
                    teacher.getTeacherId(),
                    teacher.getTeacherName(),
                    teacher.getTeacherAge(),
                    teacher.getTeacherContact(),
                    teacher.getTeacherStartDate());
        }

        try {
            System.out.print("Enter the Teacher ID to update: ");
            int teacherId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Teachers teacher = TeacherCRUD.getTeacher(teacherId);
            if (teacher == null) {
                System.out.println("Teacher not found.");
                return;
            }

            // Update teacher's name
            System.out.print("Enter new teacher name (current: " + teacher.getTeacherName() + "): ");
            String newName = scanner.nextLine();
            if (!newName.isBlank()) {
                teacher.setTeacherName(newName);
            }

            // Update teacher's age
            System.out.print("Enter new age (current: " + teacher.getTeacherAge() + "): ");
            String ageInput = scanner.nextLine();
            if (!ageInput.isBlank()) {
                try {
                    int newAge = Integer.parseInt(ageInput);
                    teacher.setTeacherAge(newAge);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid age input. Keeping the current age.");
                }
            }

            // Update teacher's contact
            System.out.print("Enter new contact (current: " + teacher.getTeacherContact() + "): ");
            String newContact = scanner.nextLine();
            if (!newContact.isBlank()) {
                teacher.setTeacherContact(newContact);
            }

            // Update teacher's hire date
            System.out.print("Enter new hire date (current: " + teacher.getTeacherStartDate() + "): ");
            String newHireDate = scanner.nextLine();
            if (!newHireDate.isBlank()) {
                teacher.setTeacherStartDate(newHireDate);
            }

            // Update the teacher in the database
            TeacherCRUD.updateTeacher(teacher);
            System.out.println("Teacher updated successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid Teacher ID.");
            scanner.nextLine(); // Clear invalid input
        }

        waitForEnter(); // Wait for Enter before continuing
    }


    private static void updateStudent() {
        // Fetch all students from the CRUD method
        List<Student> allStudents = studentCRUD.getAllStudents();

        if (allStudents.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        // Display the list of students
        System.out.println("Students List:");
        for (Student student : allStudents) {
            System.out.printf("ID: %d, Name: %s, Age: %d, Contact: %s%n",
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    student.getContact());
        }

        try {
            System.out.print("Enter the Student ID to update: ");
            int studentId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Student student = studentCRUD.getStudent(studentId);
            if (student == null) {
                System.out.println("Student not found.");
                return;
            }

            // Update student name
            System.out.print("Enter new student name (current: " + student.getName() + "): ");
            String newName = scanner.nextLine();
            if (!newName.isBlank()) {
                student.setName(newName);
            }

            // Update student age
            System.out.print("Enter new student age (current: " + student.getAge() + "): ");
            String ageInput = scanner.nextLine();
            if (!ageInput.isBlank()) {
                try {
                    int newAge = Integer.parseInt(ageInput);
                    student.setAge(newAge);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid age input. Keeping the current age.");
                }
            }

            // Update student contact
            System.out.print("Enter new student contact information (current: " + student.getContact() + "): ");
            String newContact = scanner.nextLine();
            if (!newContact.isBlank()) {
                student.setContact(newContact);
            }

            // Save the updated student to the database
            studentCRUD.updateStudent(student);
            System.out.println("Student updated successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid Student ID.");
            scanner.nextLine(); // Clear invalid input
        }

        waitForEnter(); // Wait for Enter before continuing
    }


    private static void averageSchoolGrade() {
        List<Grades> allGrades = gradeCRUD.getAllGrades();

        if (allGrades.isEmpty()) {
            System.out.println("No grades available for statistics.");
            return;
        }


        double total = 0;
        int count = 0;
        int highestGrade = Integer.MIN_VALUE;
        int lowestGrade = Integer.MAX_VALUE;

        for (Grades grade : allGrades) {
            int value = grade.getValue();
            total += value;
            count++;

            if (value > highestGrade) {
                highestGrade = value;
            }

            if (value < lowestGrade) {
                lowestGrade = value;
            }
        }

        double average = total / count;

        System.out.println("Statistics:");
        System.out.println("Total Grades: " + count);
        System.out.println("Average Grade: " + average);
        System.out.println("Highest Grade: " + highestGrade);
        System.out.println("Lowest Grade: " + lowestGrade);
        waitForEnter(); //Wait for enter before continuing
    }

    private static void viewCoursePulseAverage() {
        try {
            List<CourseFeedback> feedbacks = courseFeedbackCRUD.getAllCourseFeedbacks();
            if (feedbacks.isEmpty()) {
                System.out.println("No feedback available.");
                return;
            }

            double total = 0;
            int count = 0;
            for (CourseFeedback feedback : feedbacks) {
                total += feedback.getFeedbackGrade();
                count++;
            }

            double average = total / count;
            System.out.printf("Course Pulse Average: %.2f%n", average);
        } catch (Exception e) {
            System.out.println("An error occurred while calculating the course pulse average: " + e.getMessage());
        }
        waitForEnter();
    }

    private static void viewAllTeachers() {
        List<Teachers> teachers = TeacherCRUD.getAllTeachers(); // Use instance
        if (teachers.isEmpty()) {
            System.out.println("No teachers found.");
            return;
        }
        teachers.forEach(System.out::println);
        waitForEnter();
    }

    // Helper method to wait for Enter key
    private static void waitForEnter() {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();  // Wait for user to press Enter
    }

    public static void setTeacherCRUD(TeacherCRUD teacherCRUD) {
        Main.teacherCRUD = teacherCRUD;
    }
}
