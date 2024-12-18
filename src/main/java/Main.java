import repositories.StudentDAO;

public class Main {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        Menu menu = new Menu(studentDAO);
        menu.start();
    }
}
