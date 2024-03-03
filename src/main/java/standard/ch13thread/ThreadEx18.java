package standard.ch13thread;

public class ThreadEx18 implements Runnable {
    boolean suspend = false;
    boolean stopped = false;

    Thread th;

    public ThreadEx18(String name) {
        th = new Thread(this, name);
    }

    @Override
    public void run() {
        String threadName = th.getName();
        while (!stopped) {
            if (!suspend) {
                System.out.println(threadName);
                // stop(), suspend()에서 interrupt()가 호출되면,예외가발생된다. Thread 가 1초 대기X
                try{Thread.sleep(1000);} catch (InterruptedException e){
                    System.out.println(threadName+" - interrupted");
                }
            }else {
                Thread.yield(); // 남은 실행시간을 while문에 낭비X하지 않고, 다른 쓰레드에 양보
            }
        }
        System.out.println(Thread.currentThread().getName()+"- stopped");
    }

    public void suspend() {
        suspend=true;
        th.interrupt();
        System.out.println (th.getName() + "- interrupt () by suspend ()") ;
    }
    public void resume() { suspend=false; }
    public void stop() {
        stopped=true;
        th.interrupt();
        System.out.println (th.getName() + "- interrupt () by stop ()") ;
    }
    public void start() { th.start(); }
}
