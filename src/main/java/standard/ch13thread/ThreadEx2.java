package standard.ch13thread;

public class ThreadEx2 extends Thread {
    public void run() {
        throwException();
    }

    private void throwException() {
        try{
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
