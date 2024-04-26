package modernaction.chap13;

public interface A {
    default void hello() {
        System.out.println("Hello from A");
    }
}
