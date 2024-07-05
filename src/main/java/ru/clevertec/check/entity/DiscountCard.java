package main.java.ru.clevertec.check.entity;

public class DiscountCard {

    private int id;
    private int number;
    private int discount;

    public DiscountCard(int id, int number, int discount) {
        this.id = id;
        this.number = number;
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "id=" + id +
                ", number=" + number +
                ", discount=" + discount +
                '}';
    }
}
