package standard.ch13thread;

public class ThreadEx17 implements Runnable {
    boolean suspend = false;
    boolean stopped = false;

    Thread th;

    public ThreadEx17(String name) {
        th = new Thread(this, name);
    }

    @Override
    public void run() {
        while (!stopped) {
            if (!suspend) {
                System.out.println(Thread.currentThread().getName());
            }
            try{Thread.sleep(1000);} catch (InterruptedException e){} // sleep 위치가 중요?? suspend 값이 변경안됨 lock?
        }
        System.out.println(Thread.currentThread().getName()+"- stopped");
    }

    public void suspend() { suspend=true; }
    public void resume() { suspend=false; }
    public void stop() { stopped=true; }
    public void start() { th.start(); }
}
