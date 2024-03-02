package standard.ch13thread;

import java.util.Iterator;
import java.util.Map;

public class ThreadEx11 {
    static class ThreadEx11_1 extends Thread {
        public ThreadEx11_1(String name) {
            super(name);
        }

        @Override
        public void run() {
            try{ sleep(5*1000); }
            catch (InterruptedException e) {}
        }
    }

    static class ThreadEx11_2 extends Thread {
        public ThreadEx11_2(String name) {
            super(name);
        }

        @Override
        public void run() {
            Map map = getAllStackTraces();
            Iterator it = map.keySet().iterator();

            int x = 0;
            while (it.hasNext()) {
                Object obj = it.next();
                Thread t = (Thread) obj;
                StackTraceElement[] stackTraceElements = (StackTraceElement[]) (map.get(obj));

                String message = String.format("[%d] name: %s, group: %s, daemon: %b"
                        , ++x, t.getName(), t.getThreadGroup().getName(), t.isDaemon());
                System.out.println(message);

                for (StackTraceElement element : stackTraceElements) {
                    System.out.println("element = " + element);
                }
                System.out.println();
            }
        }
    }
}
