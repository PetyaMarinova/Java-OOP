package p03ShoppingSpree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.products = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(this.products);
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }

    public void buyProduct(Product product) {
        if (product.getCost() > this.money) {
            throw new IllegalArgumentException(String.format("%s can't afford %s%n", this.name, product.getName()));
        }
        this.products.add(product);
        this.money -= product.getCost();
    }
}
