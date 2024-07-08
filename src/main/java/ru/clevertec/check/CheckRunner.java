package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.DAO.DiscountCardDAO;
import main.java.ru.clevertec.check.entity.*;

import java.util.*;

public class CheckRunner {

    public static void main(String[] args) {
        try {
            Check check = initial(args);
            check.printCheck();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Check initial(String[] args) throws Exception {
        if (args.length == 0) {
            throw new Exception("BAD REQUEST");
        }
        HashMap<Integer, Integer> products = new HashMap<>();
        int discountCardNumber = 0;
        int balanceDebitCard = 0;
        for (int i = 0; i < args.length; i++) {
            String[] strings = args[i].split("-");
            if ((args[i].contains("-") && strings.length < 2) || (args[i].contains("=") && args[i].split("=").length < 2)) {
                throw new Exception("BAD REQUEST");
            }
            try {
                if (strings.length > 1) {
                    if (products.containsKey(Integer.parseInt(strings[0]))) {
                        products.put(Integer.parseInt(strings[0]), products.get(Integer.parseInt(strings[0])) + Integer.parseInt(strings[1]));
                    } else {
                        products.put(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
                    }
                } else if (args[i].contains("discountCard")) {
                    discountCardNumber = Integer.parseInt(args[i].split("=")[1]);

                } else if (args[i].contains("balanceDebitCard")) {
                    balanceDebitCard = Integer.parseInt(args[i].split("=")[1]);
                }
            } catch (Exception ex) {
                throw new Exception("BAD REQUEST");
            }
        }
        ArrayList<Product> productArrayList = Product.getProductsList(products);
        DiscountCard discountCard = new DiscountCardDAO().getByNumber(discountCardNumber);
        ArrayList<CheckProduct> checkProducts = new CheckProduct().createCheckProducts(productArrayList, discountCard);
        Check check = new Check(checkProducts, discountCard, balanceDebitCard);
        return check;
    }
}
