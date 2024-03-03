package standard.ch13thread;

import lombok.SneakyThrows;

import java.util.Iterator;
import java.util.Map;

public class ThreadEx19 {
    static class ThreadEx19_1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 300; i++) {
                System.out.printf("-");
            }
        }
    }

    static class ThreadEx19_2 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 600; i++) {
                System.out.printf("|");
            }
        }
    }
}
