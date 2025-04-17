import java.util.*;

public class Main {
    @SuppressWarnings("FieldMayBeFinal")
    private static Scanner sc = new Scanner(System.in);
    @SuppressWarnings("FieldMayBeFinal")
    private static StudentManagement sm = new StudentManagement();
    private static int nextId = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student by Name");
            System.out.println("6. Show Average Grade");
            System.out.println("7. Show Highest and Lowest Grade Student");
            System.out.println("8. Show Students by Course");
            System.out.println("9. Mark Attendance");
            System.out.println("10. Increment Class Count for Course");
            System.out.println("11. Show Students with Low Attendance");
            System.out.println("12. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> sm.displayAllStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> searchStudent();
                case 6 -> System.out.println("Average Grade: " + sm.getAverageGrade());
                case 7 -> showHighLow();
                case 8 -> showByCourse();
                case 9 -> markAttendance();
                case 10 -> incrementClassCount();
                case 11 -> showLowAttendance();
                case 12 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Age: ");
        int age = Integer.parseInt(sc.nextLine());
        System.out.print("Course: ");
        String course = sc.nextLine();
        System.out.print("Grade: ");
        double grade = Double.parseDouble(sc.nextLine());

        sm.addStudent(new Student(nextId++, name, age, course, grade));
    }

    private static void updateStudent() {
        System.out.print("Enter ID to update: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("New Name: ");
        String name = sc.nextLine();
        System.out.print("New Age: ");
        int age = Integer.parseInt(sc.nextLine());
        System.out.print("New Course: ");
        String course = sc.nextLine();
        System.out.print("New Grade: ");
        double grade = Double.parseDouble(sc.nextLine());

        boolean updated = sm.updateStudent(id, name, age, course, grade);
        System.out.println(updated ? "Updated successfully!" : "Student not found!");
    }

    private static void deleteStudent() {
        System.out.print("Enter ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());
        boolean deleted = sm.deleteStudent(id);
        System.out.println(deleted ? "Deleted successfully!" : "Student not found!");
    }

    private static void searchStudent() {
        System.out.print("Enter name to search: ");
        String name = sc.nextLine();
        List<Student> found = sm.searchByName(name);
        if (found.isEmpty()) {
            System.out.println("No student found.");
        } else {
            found.forEach(System.out::println);
        }
    }

    private static void showHighLow() {
        Student high = sm.getHighestGradeStudent();
        Student low = sm.getLowestGradeStudent();
        if (high != null) {
            System.out.println("\nHighest Grade:\n" + high);
        }
        if (low != null) {
            System.out.println("\nLowest Grade:\n" + low);
        }
    }

    private static void showByCourse() {
        Map<String, List<Student>> map = sm.getStudentsByCourse();
        for (var entry : map.entrySet()) {
            System.out.println("\nCourse: " + entry.getKey());
            entry.getValue().forEach(System.out::println);
        }
    }

    private static void markAttendance() {
        System.out.print("Enter student ID to mark attendance: ");
        int id = Integer.parseInt(sc.nextLine());
        sm.markAttendance(id);
        System.out.println("Attendance marked!");
    }

    private static void incrementClassCount() {
        System.out.print("Enter course to increment class count: ");
        String course = sc.nextLine();
        sm.incrementClassCount(course);
        System.out.println("Class count incremented for " + course);
    }

    private static void showLowAttendance() {
        System.out.print("Enter threshold percentage (e.g., 75): ");
        double threshold = Double.parseDouble(sc.nextLine());
        List<Student> low = sm.getLowAttendance(threshold);
        if (low.isEmpty()) {
            System.out.println("No students with low attendance.");
        } else {
            System.out.println("Students below " + threshold + "%:");
            low.forEach(System.out::println);
        }
    }
}
