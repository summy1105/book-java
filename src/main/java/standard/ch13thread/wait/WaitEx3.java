package standard.ch13thread.wait;

import java.util.ArrayList;
import java.util.List;

public class WaitEx3 {
    public static class Customer implements Runnable {
        private Table table;
        private String food;

        public Customer(Table table, String food) {
            this.table = table;
            this.food = food;
        }

        @Override
        public void run() {
            while (true) {
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                String name = Thread.currentThread().getName();

                table.remove(food);
                System.out.printf("%s ate a %s.\n", name, food);
            }
        }
    }

    public static class Cook implements Runnable {
        private Table table;

        public Cook(Table table) {
            this.table = table;
        }

        @Override
        public void run() {
            while (true) {
                int idx = (int) (Math.random() * table.dishNum());
                table.add(table.dishNames[idx]);

                try { Thread.sleep(50); } catch (InterruptedException e) {}
            }
        }
    }

    public static class Table {
        String[] dishNames = {"donut", "donut", "burger"};
        final int MAX_FOOD = 6; // 테이블에 놓을수 있는 최대 음식의 개수

        private List<String> dishes = new ArrayList<>();

        public synchronized void add(String dish) {
            if (dishes.size() >= MAX_FOOD) {
                String name = Thread.currentThread().getName();
                System.out.println(name + " is waiting.");
                try{
                    wait(); //COOK 기다림.
                    Thread.sleep(500);
                }catch (InterruptedException e){}
            }
            dishes.add(dish);
            notify(); //기다리고 있는 customer 깨우기
            System.out.println("Dishes:"+dishes);
        }

        public void remove(String dishName) {
            synchronized (this){
                String name = Thread.currentThread().getName();
                while(dishes.size() == 0){
                    System.out.println(name + " is waiting.");
                    try {
                        wait(); // customer 쓰레드 기다리게
                        Thread.sleep(500);
                    } catch (InterruptedException e) {}
                }
                while(true){
                    for (String dish : dishes) {
                        if(dishName.equals(dish)){
                            dishes.remove(dish);
                            notify(); //cook 깨우기
                            return;
                        }
                    }// end for

                    try{
                        System.out.println(name+" is waiting.");
                        wait(); //원하는 음식이 없으면, customer를 기다리게
                        Thread.sleep(500);
                    } catch (InterruptedException e) {}
                }
            } //synchronized
        }

        public int dishNum() {
            return dishNames.length;
        }
    }


}
