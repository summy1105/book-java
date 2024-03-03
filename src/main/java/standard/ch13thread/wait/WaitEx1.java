package standard.ch13thread.wait;

import java.util.ArrayList;
import java.util.List;

public class WaitEx1 {
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
                try { Thread.sleep(10); } catch (InterruptedException e) {}

                String name = Thread.currentThread().getName();

                if(eatFood()){
                    System.out.printf("%s ate a %s.\n", name, food);
                }else{
                    System.out.printf("%s failed to eat. :( \n", name);
                }
            }
        }

        private boolean eatFood() {
            return table.remove(food);
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

                try { Thread.sleep(1); } catch (InterruptedException e) {}
            }
        }
    }

    public static class Table {
        String[] dishNames = {"donut", "donut", "burger"};
        final int MAX_FOOD = 6; // 테이블에 놓을수 있는 최대 음식의 개수

        private List<String> dishes = new ArrayList<>();

        public void add(String dish) {
            if (dishes.size() >= MAX_FOOD) {
                return;
            }
            dishes.add(dish);
            System.out.println("Dishes:"+dishes);
        }

        public boolean remove(String dishName) {
            for (String dish : dishes) {
                if(dishName.equals(dish)){
                    dishes.remove(dish);
                    return true;
                }
            }
            return false;
        }

        public int dishNum() {
            return dishNames.length;
        }
    }


}
