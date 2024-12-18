import com.example.entity.Student;
import repositories.StudentDAO;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final StudentDAO studentDAO = new StudentDAO();

    public Menu(StudentDAO studentDAO) {

    }

    public void start() {
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

    private void addStudent() {
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

        studentDAO.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private void viewAllStudents() {
        studentDAO.getAllStudents().forEach(student ->
                System.out.println(student.getId() + ": " + student.getName() + " (" + student.getAge() + ")"));
    }

    private void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Student student = studentDAO.getStudent(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new name: ");
        student.setName(scanner.nextLine());
        System.out.print("Enter new age: ");
        student.setAge(scanner.nextInt());
        scanner.nextLine(); // Consume newline
        studentDAO.updateStudent(student);
        System.out.println("Student updated successfully.");
    }

    private void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        studentDAO.deleteStudent(id);
        System.out.println("Student deleted successfully.");
    }
}
