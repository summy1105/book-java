package standard.ch13thread.wait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class WaitEx4 {
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
                try { Thread.sleep(1000); } catch (InterruptedException e) {
                    System.out.printf("aur ");
                }

                String name = Thread.currentThread().getName();

                table.remove(food);
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

                try { Thread.sleep(10); } catch (InterruptedException e) {}
            }
        }
    }

    public static class Table {
        String[] dishNames = {"donut", "donut", "burger"};
        final int MAX_FOOD = 6;
        private List<String> dishes = new ArrayList<>();

        private ReentrantLock lock = new ReentrantLock();
        private Condition forCook = lock.newCondition();
        private Condition forCustomer = lock.newCondition();

        public synchronized void add(String dish) {
            lock.lock();
            String name = Thread.currentThread().getName();
            System.out.printf("%s lock ", name);
            try{
                while(dishes.size() >= MAX_FOOD){
                    System.out.println(name + " is waiting.");
                    try{
                        forCook.await(); //COOK 기다림.
                        Thread.sleep(500);
                    }catch (InterruptedException e){}
                }
                dishes.add(dish);
                forCustomer.signal();//기다리고 있는 customer 깨우기
                System.out.println("Dishes:"+dishes);
            }finally{
                lock.unlock();
                System.out.printf("%s unlock\n", name);
            }
        }

        public void remove(String dishName) {
            lock.lock();
            String name = Thread.currentThread().getName();
            System.out.printf("%s lock ", name);
            try{
                while(dishes.size() == 0){
                    System.out.println(name + " is waiting. size");
                    try {
                        forCustomer.await(); // customer 쓰레드 기다리게
                        Thread.sleep(500);
                    } catch (InterruptedException e) {}
                }
                while(true){
                    for (String dish : dishes) {
                        if(dishName.equals(dish)){
                            dishes.remove(dish);
                            System.out.printf("%s ate a %s.\n", name, dishName);
                            forCook.signal(); //cook 깨우기
                            return;
                        }
                    }// end for

                    try{
                        System.out.println(name+" is waiting. not eat");
                        forCustomer.await(); //원하는 음식이 없으면, customer를 기다리게
                        Thread.sleep(500);
                    } catch (InterruptedException e) {}
                }
            }finally {
                lock.unlock();
                System.out.printf("%s unlock\n", name);
            }
        }

        public int dishNum() {
            return dishNames.length;
        }
    }


}
