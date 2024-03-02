package standard.ch13thread;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThreadTest {

    @Test
    public void threadExceptionTest() {
        ThreadEx1.ThreadEx1_1 t1 = new ThreadEx1.ThreadEx1_1();
        assertThrowsExactly(IllegalThreadStateException.class, ()->{
            t1.start();
            t1.start();
        });
    }

    @Test
    public void thread2Test() {
        ThreadEx2 threadEx2 = new ThreadEx2();
        threadEx2.start(); // 스레드 실행 print stack에 thread2Test 없음
        try{
            throw new Exception("testCode");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void thread3Test() {
        ThreadEx2 threadEx3 = new ThreadEx2();
        threadEx3.run(); // 스레드 실행이 아니라, 메소드 호출
    }

    @Test
    public void threadGroupTest() {
        ThreadGroup main = Thread.currentThread().getThreadGroup();
        ThreadGroup group1 = new ThreadGroup("Group1");
        ThreadGroup group2 = new ThreadGroup("Group2");

        ThreadGroup subGroup1 = new ThreadGroup(group1, "SubGroup1");

        group1.setMaxPriority(3); // Thread group1의 최대 우선순위를 3으로 변경

        Runnable r = () -> {
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e) {}
        };

        new Thread(group1, r, "th1").start();
        new Thread(subGroup1, r, "th2").start();
        new Thread(group2, r, "th3").start();
        System.out.println(">>List of ThreadGroup : "+main.getName()
                +", Active ThreadGroup: "+main.activeGroupCount()
                +", Active Thread: "+main.activeCount() );

        main.list();
    }

    @Test
    public void DemonThreadTest() {
        // demon thread는 보조적인 쓰레드, 일반 쓰레드가 모두 종료되면 강제적으로 종료
        // 데몬쓰레드가 생성한 쓰레드는 자동 데몬쓰레드
        Thread t = new Thread(new ThreadEx10());
        t.setDaemon(true); // 이부분이 없으면 종료되지 않는다.
        t.start();

        for (int i = 0; i < 10; i++) {
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e) {}

            System.out.println(i);
            if(i==5) ThreadEx10.autoSave = true;
        }
        System.out.println("프로그램을 종료합니다.");
    }

    @Test
    public void printAllStackThread() throws InterruptedException {
        ThreadEx11.ThreadEx11_1 thread1 = new ThreadEx11.ThreadEx11_1("thread1");
        ThreadEx11.ThreadEx11_2 thread2 = new ThreadEx11.ThreadEx11_2("thread2");
        thread1.start();
        thread2.start();
    }
}