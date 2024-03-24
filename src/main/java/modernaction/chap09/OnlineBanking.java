package modernaction.chap09;

import java.util.function.Consumer;

public abstract class OnlineBanking {
    public void processCustomer(int id) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy(c);
    }

    abstract void makeCustomerHappy(Customer c);

    // 위의 processCustomer 메소드를 람다 형식으로 변환
    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }


    public static class Customer{
        int id;

        public Customer(int id) {
            this.id = id;
        }

        public String getName() {
            return "Customer";
        }
    }

    public static class Database{
        public static Customer getCustomerWithId(int id) {
            return new Customer(id);
        }
    }
}
