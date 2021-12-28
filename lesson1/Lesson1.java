package lesson1;

public class Lesson1 {
    public static void main(String[] args) {
        Course course = new Course();
        Team team = new Team("Мстители");
        System.out.println(team);
        course.doIt(team);
        System.out.println();
        team.showResults();
    }
}
