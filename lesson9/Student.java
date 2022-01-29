package lesson9;

import java.util.List;

public class Student {
    private String name;
    private List<Course> courses;

    public Student(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public int getCoursesNumber() {
        return courses.size();
    }

    @Override
    public String toString() {
        return name + " посещает курсы: " + courses;
    }
}
