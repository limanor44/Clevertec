package main.java.ru.clevertec.check.entity;

import jdk.swing.interop.SwingInterOpUtils;
import main.java.ru.clevertec.check.DAO.ProductDAO;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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

    public static ArrayList<Product> getProductsList(HashMap<Integer, Integer> products) throws Exception {
        return getProductsList(products, null);
    }

    public static ArrayList<Product> getProductsList(HashMap<Integer, Integer> products, String path) throws Exception {
        try {
            Set<Integer> set = products.keySet();
            ProductDAO productDAO = new ProductDAO();
            ArrayList<Product> list = new ArrayList<>();
            for (int o : set) {
                Product product = path == null ? productDAO.getById(o) : productDAO.getById(o, path);
                if (product == null || product.quantity < products.get(o)) {
                    throw new Exception("BAD REQUEST");
                }
                product.quantity = products.get(o);
                list.add(product);
            }
            return list;
        }catch (FileNotFoundException ex){
            throw new Exception("LOL");
        }
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isWholesale() {
        return wholesale;
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
