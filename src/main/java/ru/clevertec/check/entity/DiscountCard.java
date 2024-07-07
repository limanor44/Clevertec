package main.java.ru.clevertec.check.entity;

import java.util.Objects;

public class DiscountCard {

    private int id;
    private int number;
    private int discount;

    public DiscountCard(int id, int number, int discount) {
        this.id = id;
        this.number = number;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountCard that = (DiscountCard) o;
        return id == that.id && number == that.number && discount == that.discount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, discount);
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
