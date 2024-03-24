package modernaction.chap09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductFactoryTest {

    @Test
    public void createProductTest() {
        ProductFactory.Product loan = ProductFactory.createProduct("loan");
        assertEquals(ProductFactory.Loan.class, loan.getClass());
    }

    @Test
    public void getProductTest() {
        ProductFactory.Product bond = ProductFactory.getProduct("bond");
        assertEquals(ProductFactory.Bond.class, bond.getClass());
    }
}