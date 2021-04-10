package store;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class StoreTests {
    private Store store;
    private Product product1;
    private Product product2;

    @Before
    public void setUp(){
        store = new Store();
        product1 = new Product("Tomato", 2, 1.20);
        product2 = new Product("Orange", 1, 0.20);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiable(){
        this.store.getProducts().add(product1);
    }

    @Test
    public void testGetCountEqualsZero(){
        assertEquals(0,store.getCount());
    }

    @Test
    public void testGetCountReturnCorrectCount(){
        this.store.addProduct(product1);
        this.store.addProduct(product1);
        assertEquals(this.store.getProducts().size(),this.store.getCount());
    }

    @Test
    public void testGetCountIsEmpty(){
        List<Product> list = new ArrayList<>(this.store.getCount());
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullProduct(){
        Product product = null;
        this.store.addProduct(product);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddProductWithQuantityLessThat0(){
        Product product = new Product("Rice", 0, 2.00);
        this.store.addProduct(product);
    }

    @Test
    public void testAddCorrectProduct(){
        this.store.addProduct(product1);
        this.store.addProduct(product2);
        assertEquals(2,this.store.getProducts().size());
        assertEquals(product1.getName(),this.store.getProducts().get(0).getName());
        assertEquals(this.store.getProducts().get(0).getPrice(),product1.getPrice(),0.00);
        assertEquals(this.store.getProducts().get(0).getQuantity(),product1.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuyNullProduct(){
        this.store.addProduct(product1);
        this.store.addProduct(product2);
        this.store.buyProduct("Apple",2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuyProductWithNotEnoughQuantity(){
        this.store.addProduct(product1);
        this.store.addProduct(product2);
        int quantityToBuy = 15;
        this.store.buyProduct("Tomato",quantityToBuy);
    }

    @Test
    public void testBuyProductReturnCorrectFinalPrice(){
        this.store.addProduct(product1);
        this.store.addProduct(product2);

        assertEquals(product1.getQuantity(),product1.getQuantity());
        assertEquals(product1.getPrice(),product1.getPrice(), 0.00);
        assertEquals(product1.getName(),product1.getName());

        double finalPrice = product1.getPrice() * 2;
        assertEquals(finalPrice,this.store.buyProduct("Tomato",2), 0.00);
    }

    @Test
    public void testGetTheMostExpensiveProduct(){
        this.store.addProduct(product1);
        this.store.addProduct(product2);

        assertNotNull(this.store.getTheMostExpensiveProduct());
        assertEquals(product1, this.store.getTheMostExpensiveProduct());
        assertEquals(product1.getName(),this.store.getTheMostExpensiveProduct().getName());
        assertEquals(product1.getPrice(),this.store.getTheMostExpensiveProduct().getPrice(),0.00);
        assertEquals(product1.getQuantity(),this.store.getTheMostExpensiveProduct().getQuantity());

    }
}