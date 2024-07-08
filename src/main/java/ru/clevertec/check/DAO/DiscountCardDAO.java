package main.java.ru.clevertec.check.DAO;

import main.java.ru.clevertec.check.entity.DiscountCard;
import main.java.ru.clevertec.check.interfaces.Crud;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DiscountCardDAO implements Crud<DiscountCard> {

    private final static String DISCOUNTCARDPATH = "./src/main/resources/discountCards.csv";

    @Override
    public DiscountCard getById(int id) {
        Scanner scanner = null;
        DiscountCard discountCard = null;
        try {
            scanner = new Scanner(new File(DISCOUNTCARDPATH));
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

    @Override
    public ArrayList<DiscountCard> getAll() {
        Scanner scanner = null;
        ArrayList<DiscountCard> discountCards = new ArrayList<>();
        try {
            scanner = new Scanner(new File(DISCOUNTCARDPATH));
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
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(DISCOUNTCARDPATH));
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
