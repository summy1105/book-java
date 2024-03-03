package standard.ch13thread;

public class ThreadEx20 extends Thread{
    final static int MAX_MEMORY = 1000;
    int usedMemory = 0;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                System.out.println("Awaken by interrupt().");
            }
            gc(); // garbage collection 수행
            System.out.println("Garbage Collected. Free Memory :"+ freeMemory());
        }
    }

    public int freeMemory() {
        return MAX_MEMORY - usedMemory;
    }
    public int totalMemory () { return MAX_MEMORY; }

    public void gc() {
        usedMemory = (usedMemory-300 < 0)? 0 : usedMemory-300;
    }
}
