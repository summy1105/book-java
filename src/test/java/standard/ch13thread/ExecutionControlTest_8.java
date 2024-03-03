package standard.ch13thread;

import org.junit.jupiter.api.Test;

import javax.swing.*;

public class ExecutionControlTest_8 {

    @Test
    public void threadSleepTest() {
        Runnable runnable1 = ()->{
            for (int i = 0; i < 300; i++) {
                System.out.printf("-");
            }
            System.out.printf("<<thread1 종료>>");
        };

        Runnable runnable2 = ()->{
            for (int i = 0; i < 300; i++) {
                System.out.printf("|");
            }
            System.out.printf("<<thread2 종료>>");
        };
        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);

        t1.start();
        t2.start();

        try {
            t1.sleep(100); // instance에 속한 method가 아니라, static으로 main thread에 동작 이렇게 쓰는것을 추천X
            // 무조건 Thread.sleep()-> 형식으로 사용
            // sleep method는 public static native void -> "native" c,c++로 구현된 메소드 -> instance X OS레벨
        } catch (InterruptedException e) { }

        System.out.println("<<main 종료>>");
    }

    class ThreadEx13_1 extends Thread {
        @Override
        public void run() {
            int i=10;

            while (i!=0 && !isInterrupted()){
                System.out.println(i--);
                try{Thread.sleep(1000);}catch (InterruptedException e){
                    System.out.println("interrupted");
                    interrupt();
                }
            }
            System.out.println("카운터가 종료되었습니다.");
        }
    }

    @Test
    public void threadInterruptTest() {
        Thread thread = new ThreadEx13_1();
        thread.start();

        String input = JOptionPane.showInputDialog("아무값이나 입력하세요");
        System.out.printf("입력하신 값은 %s 입니다.\n", input);
        thread.interrupt();
        System.out.println("thread.isInterrupted() = " + thread.isInterrupted());
    }

    @Test
    public void suspendNStopTest() {
        ThreadEx17 thread1 = new ThreadEx17("*");
        ThreadEx17 thread2 = new ThreadEx17("**");
        ThreadEx17 thread3 = new ThreadEx17("***");
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            Thread.sleep(2000);
            thread1.suspend();
            Thread.sleep(2000);
            thread2.suspend();

            Thread.sleep(3000);
            thread1.resume();

            Thread.sleep(3000);
            thread1.stop();
            thread2.stop();

            Thread.sleep(2000);
            thread3.stop();

            Thread.sleep(3000);
        } catch (InterruptedException e){}

    }

    @Test
    public void yieldTest() {
        ThreadEx18 thread1 = new ThreadEx18("*");
        ThreadEx18 thread2 = new ThreadEx18("**");
        ThreadEx18 thread3 = new ThreadEx18("***");
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            Thread.sleep(2000);
            thread1.suspend();
            Thread.sleep(2000);
            thread2.suspend();

            Thread.sleep(3000);
            thread1.resume();

            Thread.sleep(3000);
            thread1.stop();
            thread2.stop();

            Thread.sleep(2000);
            thread3.stop();

            Thread.sleep(3000);
        } catch (InterruptedException e){}
    }

    @Test
    public void joinTest() {
        ThreadEx19.ThreadEx19_1 th1 = new ThreadEx19.ThreadEx19_1();
        ThreadEx19.ThreadEx19_2 th2 = new ThreadEx19.ThreadEx19_2();
        th1.start();
        th2.start();
        long startTime = System.currentTimeMillis();

        try{
            th1.join();
            System.out.println("th1.joined");
            th2.join();
            System.out.println("th2.joined");
        }catch (InterruptedException e){}

        System.out.println("time = " + (System.currentTimeMillis() - startTime));
    }

    @Test
    public void joinTest2() throws InterruptedException {
        ThreadEx20 gc = new ThreadEx20();
        gc.setDaemon(true);
        gc.start();

        int requiredMemory = 0;

        for (int i = 0; i < 20; i++) {
            requiredMemory = (int) (Math.random() * 10)*20;

            if (gc.freeMemory() < requiredMemory
                    || gc.freeMemory() < gc.totalMemory() * 0.4) {
                gc.interrupt(); // 잠자고 있는 쓰레드 gc를 깨운디. Thread waiting -> runnable 에 시간이 걸림
                try{
//                    gc.join(); // 사용X 무한대기
                    Thread.sleep(50);
                }catch (InterruptedException e){}
            }
            gc.usedMemory += requiredMemory;
            System.out.println("gc.usedMemory = " + gc.usedMemory);
        }
    }
}
