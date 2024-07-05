package main.java.ru.clevertec.check.entity;

public class Product {
    private int id;
    private String description;
    private double price;
    private int quantity;
    private boolean wholesale;

    public Product() {
    }

    public Product(int id, String description, double price, int quantity, boolean wholesale) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.wholesale = wholesale;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", wholesale=" + wholesale +
                '}';
    }
}
