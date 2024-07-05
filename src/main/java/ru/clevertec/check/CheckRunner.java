package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.DAO.DiscountCardDAO;
import main.java.ru.clevertec.check.DAO.ProductDAO;
import main.java.ru.clevertec.check.entity.DebitCard;
import main.java.ru.clevertec.check.entity.DiscountCard;
import main.java.ru.clevertec.check.entity.Person;
import main.java.ru.clevertec.check.entity.Product;

import java.io.*;
import java.util.*;

public class CheckRunner {


    public static void main(String[] args) {
        run(args);
    }

    public static void run(String[] args) {
        initial(args);
    }

    public static Person initial(String[] args) {
        HashMap<Integer, Integer> products = new HashMap<>();
        int discountCardNumber;
        int balanceDebitCard;
        for (int i = 0; i < args.length; i++) {
            String[] strings = args[i].split("-");
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
        }
        /*Person person = new Person(
                new DebitCard(balanceDebitCard),
                new DiscountCardDAO().getByNumber(discountCardNumber),

        );*/
        return null;
    }
}
