package lesson1;

public class Course {
    Track[] tracks;

    public Course() {
        tracks = new Track[]{
                new SwimTrack(500),
                new RunTrack(5000),
                new CycleTrack(20000)
        };
    }

    void doIt(Team team) {
        for (Person person : team.getMembers()) {
            for (Track track : tracks) {
                track.passIt(person);
            }
        }
    }
}
