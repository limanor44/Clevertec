package main.java.ru.clevertec.check.entity;

import java.util.List;

public class Person implements Buyer {
    private DebitCard debitCard;
    private DiscountCard discountCard;
    private List<Product> products;

    public Person(){

    }

    public Person(DebitCard debitCard, DiscountCard discountCard, List<Product> products) {
        this.debitCard = debitCard;
        this.discountCard = discountCard;
        this.products = products;
    }

    @Override
    public void buy() {

    }
}
