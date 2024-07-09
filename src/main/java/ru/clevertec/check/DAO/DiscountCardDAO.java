package main.java.ru.clevertec.check.DAO;

import main.java.ru.clevertec.check.entity.DiscountCard;
import main.java.ru.clevertec.check.interfaces.Crud;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DiscountCardDAO implements Crud<DiscountCard> {

    private final static String DISCOUNTCARDPATH = "./src/main/resources/discountCards.csv";

    public DiscountCard getById(int id) {
        return getById(id, DISCOUNTCARDPATH);
    }

    @Override
    public DiscountCard getById(int id, String path) {
        Scanner scanner = null;
        DiscountCard discountCard = null;
        try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        scanner.skip("id;number;discount amount, %;");
        scanner.useDelimiter(";");
        while (scanner.hasNext()) {
            int idCard = Integer.parseInt(scanner.next().trim());
            if (idCard == id) {
                discountCard = new DiscountCard(
                        idCard,
                        Integer.parseInt(scanner.next().trim()),
                        Integer.parseInt(scanner.next().trim())
                );
            } else {
                scanner.next();
                scanner.next();
            }

        }
        return discountCard;
    }

    public ArrayList<DiscountCard> getAll(){
        return getAll(DISCOUNTCARDPATH);
    }

    @Override
    public ArrayList<DiscountCard> getAll(String path) {
        Scanner scanner = null;
        ArrayList<DiscountCard> discountCards = new ArrayList<>();
        try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        scanner.skip("id;number;discount amount, %;");
        scanner.useDelimiter(";");
        while (scanner.hasNext()) {
            discountCards.add(
                    new DiscountCard(
                            Integer.parseInt(scanner.next().trim()),
                            Integer.parseInt(scanner.next().trim()),
                            Integer.parseInt(scanner.next().trim())
                    )
            );
        }
        return discountCards;
    }

    public DiscountCard getByNumber(int number) {
        return getByNumber(number, DISCOUNTCARDPATH);
    }

    public DiscountCard getByNumber(int number, String path) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        scanner.skip("id;number;discount amount, %;");
        scanner.useDelimiter(";");
        while (scanner.hasNext()) {
            int idCard = Integer.parseInt(scanner.next().trim());
            int numberCard = Integer.parseInt(scanner.next().trim());
            if (number == numberCard) {
                return new DiscountCard(
                        idCard,
                        number,
                        Integer.parseInt(scanner.next().trim())
                );
            } else {
                scanner.next();
                scanner.next();
            }

        }
        return null;
    }
}
