import java.io.*;
import java.util.*;

public class StudentManagement {
    private static final String DATA_FILE = "students.dat";
    private List<Student> students;

    public StudentManagement() {
        students = new ArrayList<>();
        loadData();
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            students = (List<Student>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Ignore - file will be created when data is saved
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public void addStudent(Student student) {
        students.add(student);
        saveData();
        System.out.println("Student added successfully!");
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
            System.out.println("-------------------");
        }
    }

    public Student findById(int id) {
        return students.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    public List<Student> searchByName(String name) {
        List<Student> matches = new ArrayList<>();
        for (Student s : students) {
            if (s.getName().toLowerCase().contains(name.toLowerCase())) {
                matches.add(s);
            }
        }
        return matches;
    }

    public boolean updateStudent(int id, String name, int age, String course, double grade) {
        Student s = findById(id);
        if (s != null) {
            s.setName(name);
            s.setAge(age);
            s.setCourse(course);
            s.setGrade(grade);
            saveData();
            return true;
        }
        return false;
    }

    public boolean deleteStudent(int id) {
        Student s = findById(id);
        if (s != null) {
            students.remove(s);
            saveData();
            return true;
        }
        return false;
    }

    public double getAverageGrade() {
        return students.stream().mapToDouble(Student::getGrade).average().orElse(0.0);
    }

    public Student getHighestGradeStudent() {
        return students.stream().max(Comparator.comparingDouble(Student::getGrade)).orElse(null);
    }

    public Student getLowestGradeStudent() {
        return students.stream().min(Comparator.comparingDouble(Student::getGrade)).orElse(null);
    }

    public Map<String, List<Student>> getStudentsByCourse() {
        Map<String, List<Student>> map = new HashMap<>();
        for (Student s : students) {
            map.computeIfAbsent(s.getCourse(), k -> new ArrayList<>()).add(s);
        }
        return map;
    }

    public void markAttendance(int id) {
        Student s = findById(id);
        if (s != null) {
            s.markAttendance();
            saveData();
        }
    }

    public void incrementClassCount(String course) {
        for (Student s : students) {
            if (s.getCourse().equalsIgnoreCase(course)) {
                s.incrementTotalClasses();
            }
        }
        saveData();
    }

    public List<Student> getLowAttendance(double threshold) {
        List<Student> low = new ArrayList<>();
        for (Student s : students) {
            if (s.getAttendancePercentage() < threshold) {
                low.add(s);
            }
        }
        return low;
    }
}
