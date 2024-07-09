package main.java.ru.clevertec.check.DAO;

import main.java.ru.clevertec.check.entity.Product;
import main.java.ru.clevertec.check.interfaces.Crud;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProductDAO implements Crud<Product> {
    private final static String PRODUCTPATH = "./src/main/resources/products.csv";


    public Product getById(int id) throws Exception{
        return getById(id,PRODUCTPATH);
    }

    @Override
    public Product getById(int id, String path) throws Exception {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            throw new Exception("BAD REQUEST");
        }
        sc.skip("id;description price,\\$;quantity in stock;wholesale product;");
        sc.useDelimiter(";");
        Product product = null;
        while (sc.hasNext()) {
            int idProd = Integer.parseInt(sc.next().trim());
            if (idProd == id) {
                product = new Product(
                        idProd,
                        sc.next().trim(),
                        Double.parseDouble(sc.next().trim().replace(',', '.')),
                        Integer.parseInt(sc.next().trim()),
                        sc.next().trim().equals("+")
                );
            } else {
                sc.next();
                sc.next();
                sc.next();
                sc.next();
            }

        }
        sc.close();
        return product;
    }

    public ArrayList<Product> getAll() {
        return getAll(PRODUCTPATH);
    }

    @Override
    public ArrayList<Product> getAll(String path) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        sc.skip("id;description price,\\$;quantity in stock;wholesale product;");
        sc.useDelimiter(";");
        ArrayList<Product> products = new ArrayList<>();
        while (sc.hasNext()) {
            products.add(
                    new Product(
                            Integer.parseInt(sc.next().trim()),
                            sc.next().trim(),
                            Double.parseDouble(sc.next().trim().replace(',', '.')),
                            Integer.parseInt(sc.next().trim()),
                            sc.next().trim() == "+" || sc.next().trim() == "true"
                    )
            );
        }
        sc.close();
        return products;
    }
}
