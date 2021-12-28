package lesson1;

public class Person {
    final private String name;
    final private int swimDistance;
    final private int runDistance;
    final private int cycleDistance;
    private boolean overcameCourse;

    public Person(String name, int swimDistance, int runDistance, int cycleDistance) {
        this.name = name;
        this.swimDistance = swimDistance;
        this.runDistance = runDistance;
        this.cycleDistance = cycleDistance;
        overcameCourse = true;
    }

    @Override
    public String toString() {
        return name +
                " (плавает " + swimDistance +
                "м, бежит " + runDistance +
                "м, проезжает " + cycleDistance +
                "м)";
    }

    public int getSwimDistance() {
        return swimDistance;
    }

    public int getRunDistance() {
        return runDistance;
    }

    public int getCycleDistance() {
        return cycleDistance;
    }

    public String getName() {
        return name;
    }

    public boolean isOvercameCourse() {
        return overcameCourse;
    }

    public void setOvercameCourse(boolean overcameCourse) {
        this.overcameCourse = overcameCourse;
    }
}
