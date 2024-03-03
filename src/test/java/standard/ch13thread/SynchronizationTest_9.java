package standard.ch13thread;

import org.junit.jupiter.api.Test;
import standard.ch13thread.forkjoin.ForkJoinEx1;
import standard.ch13thread.wait.WaitEx1;
import standard.ch13thread.wait.WaitEx2;
import standard.ch13thread.wait.WaitEx3;
import standard.ch13thread.wait.WaitEx4;


public class SynchronizationTest_9 {

    @Test
    public void synchronizedTest() throws InterruptedException {
        ThreadRunnableEx21 threadEx20 = new ThreadRunnableEx21();
        Thread th1 = new Thread(threadEx20);
        th1.start();
        Thread th2 = new Thread(threadEx20);
        th2.start();
        th1.join();
        th2.join();
    }

    @Test
    public void waitTest_1() throws InterruptedException {
        WaitEx1.Table table = new WaitEx1.Table();

        new Thread(new WaitEx1.Cook(table), "COOK1").start();
        new Thread(new WaitEx1.Customer(table, "donut"), "CUSTOMER1").start();
        new Thread(new WaitEx1.Customer(table, "burger"), "CUSTOMER2").start();

        Thread.sleep(100);
        System.exit(0);// 프로그램 종료
    }

    @Test
    public void waitTest_2() throws InterruptedException {
        WaitEx2.Table table = new WaitEx2.Table();//여러 쓰레드가 공유하는 객체

        new Thread(new WaitEx2.Cook(table), "COOK1").start();
        new Thread(new WaitEx2.Customer(table, "donut"), "CUSTOMER1").start();
        new Thread(new WaitEx2.Customer(table, "burger"), "CUSTOMER2").start();

        Thread.sleep(5000);
        System.exit(0);// 프로그램 종료
    }

    @Test
    public void waitTest_3() throws InterruptedException {
        WaitEx3.Table table = new WaitEx3.Table();//여러 쓰레드가 공유하는 객체

        new Thread(new WaitEx3.Cook(table), "COOK1").start();
        new Thread(new WaitEx3.Customer(table, "donut"), "CUSTOMER1").start();
        new Thread(new WaitEx3.Customer(table, "burger"), "CUSTOMER2").start();

        Thread.sleep(5000);
        System.exit(0);// 프로그램 종료
    }

    @Test
    public void waitLockConditionTest() throws InterruptedException {
        WaitEx4.Table table = new WaitEx4.Table();//여러 쓰레드가 공유하는 객체

        new Thread(new WaitEx4.Cook(table), "COOK1").start();
        new Thread(new WaitEx4.Customer(table, "donut"), "CUSTOMER1").start();
        new Thread(new WaitEx4.Customer(table, "burger"), "CUSTOMER2").start();

        Thread.sleep(5000);
        System.exit(0);// 프로그램 종료
    }

    @Test
    public void forkJoinTest() {
        long from = 1L;
        long to = 10_000_000L;

        ForkJoinEx1.SumTask task = new ForkJoinEx1.SumTask(from, to);

        long start = System.currentTimeMillis();

        Long result = ForkJoinEx1.POOL.invoke(task);
        System.out.println((System.currentTimeMillis() - start));

        System.out.printf("sum of %d~%d=%d\n", from, to, result);
        System.out.println();

        result = 0L;
        start = System.currentTimeMillis();
        for (long i = from; i <=to ; i++) {
            result +=i;
        }
        System.out.println((System.currentTimeMillis() - start));
        System.out.printf("sum of %d~%d=%d\n", from, to, result);
        System.out.println();
    }
}
