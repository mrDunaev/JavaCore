package lesson1;

import java.util.Arrays;

public class Team {
    private String name;
    private Person[] members;

    public Team(String name) {
        this.name = name;
        members = new Person[]{
                new Person("Петр", 600, 10000, 20000),
                new Person("Александр", 1000, 10000, 30000),
                new Person("Анастасия", 500, 4000, 20000),
                new Person("Данила", 100, 1000, 5000)
        };
    }

    public Person[] getMembers() {
        return members;
    }

    @Override
    public String toString() {
        String teamInfo = "Команда " + name + ":\n";
        for (Person member : members) {
            teamInfo += member.toString() + '\n';
        }

        return teamInfo;
    }

    public void showResults() {
        String results = "Полосу препятствий прошли:\n";
        for (Person member : members) {
            if (member.isOvercameCourse())
                results += member.getName() + '\n';
        }

        System.out.println(results);
    }
}
