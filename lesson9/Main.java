package lesson9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Course testing = new Course("Testing");
        Course java = new Course("Java");
        Course python = new Course("Python");
        Course web = new Course("Web");
        Course sql = new Course("SQL");

        List<Student> students = new ArrayList<>();
        students.add(new Student("Александр", Arrays.asList(testing)));
        students.add(new Student("Анастасия", Arrays.asList(sql, java)));
        students.add(new Student("Игорь", Arrays.asList(python)));
        students.add(new Student("Антон", Arrays.asList(sql, java, python)));
        students.add(new Student("Петр", Arrays.asList(java, testing)));

        System.out.println("Список посещаемых курсов:");
        System.out.println(visitedCourses(students));

        System.out.println("\nСписок самых любознательных студентов:");
        System.out.println(mostCuriousStudent(students));

        Course visitedCourse = testing;
        System.out.println("\nСписок студентов, посещающих курс " + visitedCourse.getName());
        System.out.println(studentVisitingCourse(students, visitedCourse));
    }

    public static List<Course> visitedCourses(List<Student> student) {
        return student.stream()
                .map(s -> s.getCourses())
                .flatMap(c -> c.stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<Student> mostCuriousStudent(List<Student> student) {
        return student.stream()
                .sorted((s1, s2) -> s2.getCoursesNumber() - s1.getCoursesNumber())
                .limit(3)
                .collect(Collectors.toList());
    }

    public static List<Student> studentVisitingCourse(List<Student> student, Course course) {
        return student.stream()
                .filter(s -> s.getCourses().contains(course))
                .collect(Collectors.toList());
    }
}
