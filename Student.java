import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student implements Serializable {
    @SuppressWarnings("FieldMayBeFinal")
    private int id;
    private String name;
    private int age;
    private String course;
    private double grade;
    @SuppressWarnings("FieldMayBeFinal")
    private List<Date> attendanceDates;
    private int totalClasses;

    public Student(int id, String name, int age, String course, double grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.grade = grade;
        this.attendanceDates = new ArrayList<>();
        this.totalClasses = 0;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCourse() { return course; }
    public double getGrade() { return grade; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setCourse(String course) { this.course = course; }
    public void setGrade(double grade) { this.grade = grade; }

    public void markAttendance() {
        attendanceDates.add(new Date());
    }

    public void incrementTotalClasses() {
        totalClasses++;
    }

    public double getAttendancePercentage() {
        return totalClasses == 0 ? 0.0 : (attendanceDates.size() * 100.0) / totalClasses;
    }

    @Override
    public String toString() {
        return "ID: " + id +
               "\nName: " + name +
               "\nAge: " + age +
               "\nCourse: " + course +
               "\nGrade: " + grade +
               "\nAttendance: " + String.format("%.2f%%", getAttendancePercentage()) +
               "\nClasses Attended: " + attendanceDates.size() +
               "\nTotal Classes: " + totalClasses;
    }
}
