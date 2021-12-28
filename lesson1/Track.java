package lesson1;

public abstract class Track {
    protected int distance;

    public Track(int distance) {
        this.distance = distance;
    }

    abstract void passIt(Person person);
}
