package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.DAO.DiscountCardDAO;
import main.java.ru.clevertec.check.entity.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;

public class CheckRunner {

    public static void main(String[] args) {
        try {
            initial(args);
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
        double balanceDebitCard = 0;
        String pathToFile = null;
        String saveToFile = null;
        try {
            for (int i = 0; i < args.length; i++) {
                String[] strings = args[i].split("-");
                if ((args[i].contains("-") && strings.length < 2) || (args[i].contains("=") && args[i].split("=").length < 2)) {
                    throw new Exception("BAD REQUEST");
                }
                if (strings.length > 1) {
                    if (products.containsKey(Integer.parseInt(strings[0]))) {
                        products.put(Integer.parseInt(strings[0]), products.get(Integer.parseInt(strings[0])) + Integer.parseInt(strings[1]));
                    } else {
                        products.put(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
                    }
                } else if (args[i].contains("discountCard")) {
                    discountCardNumber = Integer.parseInt(args[i].split("=")[1]);

                } else if (args[i].contains("balanceDebitCard")) {
                    balanceDebitCard = Double.parseDouble(args[i].split("=")[1].replace(',', '.'));
                } else if (args[i].contains("pathToFile")) {
                    pathToFile = args[i].split("=")[1];
                } else if (args[i].contains("saveToFile")) {
                    saveToFile = args[i].split("=")[1];
                }
            }
        } catch (Exception ex) {
            throw new Exception("BAD REQUEST");
        }
        ArrayList<Product> productArrayList = null;
        DiscountCard discountCard = null;
        if(pathToFile!= null && saveToFile != null){
            productArrayList = Product.getProductsList(products, pathToFile);
            discountCard = new DiscountCardDAO().getByNumber(discountCardNumber);

        } else if(pathToFile == null && saveToFile == null){
            productArrayList = Product.getProductsList(products);
            discountCard = new DiscountCardDAO().getByNumber(discountCardNumber);
        }else if(pathToFile == null){
            saveError(saveToFile);
        }else{
            saveError("./result.csv");
        }
        ArrayList<CheckProduct> checkProducts = new CheckProduct().createCheckProducts(productArrayList, discountCard);
        Check check = new Check(checkProducts, discountCard, balanceDebitCard);
        check.printCheck(saveToFile);
        return check;
    }

    public static void saveError(String path) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        byte[] bytes = ("ERROR\nBAD REQUEST").getBytes();
        fileOutputStream.write(bytes);
        throw new Exception("BAD REQUEST");
    }
}
