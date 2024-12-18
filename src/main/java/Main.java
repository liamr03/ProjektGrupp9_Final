import com.example.entity.Student;
import crud.StudentCRUD;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentCRUD STUDENT_CRUD = new StudentCRUD();

    public static void main(String[] args) {
        // Start the menu
        start();
    }

    public static void start() {
        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAllStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> {
                    System.out.println("Exiting program...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter contact: ");
        String contact = scanner.nextLine();

        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        student.setContact(contact);

        STUDENT_CRUD.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private static void viewAllStudents() {
        STUDENT_CRUD.getAllStudents().forEach(student ->
                System.out.println(student.getId() + ": " + student.getName() + " (" + student.getAge() + ")"));
    }

    private static void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Student student = STUDENT_CRUD.getStudent(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new name: ");
        student.setName(scanner.nextLine());
        System.out.print("Enter new age: ");
        student.setAge(scanner.nextInt());
        scanner.nextLine(); // Consume newline
        STUDENT_CRUD.updateStudent(student);
        System.out.println("Student updated successfully.");
    }

    private static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        STUDENT_CRUD.deleteStudent(id);
        System.out.println("Student deleted successfully.");
    }
}
