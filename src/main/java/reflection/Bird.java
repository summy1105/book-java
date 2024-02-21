package reflection;

public class Bird extends Animal{
    private boolean walks;

    public Bird() {
        super("bird");
    }

    public Bird(String name, boolean walks) {
        super(name);
        setWalks(walks);
    }

    public Bird(String name) {
        super(name);
    }

    public void setWalks(boolean walks) {
        this.walks = walks;
    }

    public boolean walks() {
        return walks;
    }

    @Override
    protected String getSound() {
        return null;
    }

    @Override
    public String eats() {
        return null;
    }
}
