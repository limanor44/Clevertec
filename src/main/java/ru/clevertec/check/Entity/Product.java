package main.java.ru.clevertec.check.Entity;

public class Product {

    private int id;
    private String description;
    private int quantity;
    private boolean wholesale;

    public Product(int id, String description, int quantity, boolean wholesale) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.wholesale = wholesale;
    }
}
