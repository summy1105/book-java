package standard.ch13thread;

import lombok.SneakyThrows;

public class ThreadEx1 {
    static public void main(String[] args) {
        Thread t1 = new ThreadEx1_1();

        Runnable r = new ThreadEx1_2();
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();
    }

    static class ThreadEx1_1 extends Thread{
        @SneakyThrows
        public void run() {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(2);
                System.out.println("t1 "+getName());
            }
        }
    }

    static class ThreadEx1_2 implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(2);
                //Thread.currentThread() 현재 실행중인 Thread 반환
                System.out.println("t2 "+Thread.currentThread().getName());
            }
        }
    }
}
