package lesson1;

public class RunTrack extends Track {
    public RunTrack(int distance) {
        super(distance);
    }

    @Override
    void passIt(Person person) {
        if (person.getRunDistance() >= distance) {
            System.out.println(person.getName() + " пробежал(а) дистанцию " + distance + " метров");
        } else {
            System.out.println(person.getName() + " не смог(ла) пробежать дистанцию " + distance + " метров");
            person.setOvercameCourse(false);
        }
    }
}
