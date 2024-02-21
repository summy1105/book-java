package reflection;

public class Goat extends Animal implements Locomotion{

    public Goat(String name) {
        super(name);
    }

    @Override
    protected String getSound() {
        return "bleat";
    }

    @Override
    public String eats() {
        return "walks";
    }

    @Override
    public String getLocomotion() {
        return "grass";
    }
}
