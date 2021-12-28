package lesson1;

public class CycleTrack extends Track {
    public CycleTrack(int distance) {
        super(distance);
    }

    @Override
    void passIt(Person person) {
        if (person.getCycleDistance() >= distance) {
            System.out.println(person.getName() + " проехал(а) на велосипеде дистанцию " + distance + " метров");
        } else {
            System.out.println(person.getName() + " не смог(ла) проехать на велосипеде дистанцию " + distance + " метров");
            person.setOvercameCourse(false);
        }
    }
}
