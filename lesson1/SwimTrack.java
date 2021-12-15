package lesson1;

public class SwimTrack extends Track {
    public SwimTrack(int distance) {
        super(distance);
    }

    @Override
    void passIt(Person person) {
        if (person.getSwimDistance() >= distance) {
            System.out.println(person.getName() + " проплыл(а) дистанцию " + distance + " метров");
        } else {
            System.out.println(person.getName() + " не смог(ла) проплыть дистанцию " + distance + " метров");
            person.setOvercameCourse(false);
        }
    }
}
