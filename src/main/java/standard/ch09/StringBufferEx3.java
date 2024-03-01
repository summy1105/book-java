package standard.ch09;

public class StringBufferEx3 implements Runnable {
    StringBuilder sBuilder;
    StringBuffer sBuffer;

    public StringBufferEx3(){
        sBuilder = new StringBuilder();
        sBuffer = new StringBuffer();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            sBuilder.append("A0");
            System.out.println(Thread.currentThread().getName()+" "+sBuilder);
            sBuffer.append("B0");
            System.out.println(Thread.currentThread().getName()+" "+sBuffer);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        StringBufferEx3 ex3 = new StringBufferEx3();

        Thread th1 = new Thread(ex3);
        Thread th2 = new Thread(ex3);

        th1.start();
        th2.start();

        th1.join();
        th2.join();

        System.out.println();
        System.out.println(ex3.sBuilder.length()+" "+ex3.sBuilder.toString()); // thread not safe (not lock)
        System.out.println(ex3.sBuffer.length()+" "+ex3.sBuffer.toString()); // thread safe (lock)
    }
}
