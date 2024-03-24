package modernaction.chap09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OnlineBankingTest {

    @Test
    void lambdaProcessCustomerTest() {
        OnlineBanking onlineBanking = new OnlineBanking() {
            @Override
            void makeCustomerHappy(Customer c) {
                System.out.println("abstract: Hello "+c.getName());
            }
        };
        onlineBanking.processCustomer(1337);

        onlineBanking.processCustomer(1337, (OnlineBanking.Customer c)-> System.out.println("lambda:Hello "+c.getName()));
    }
}