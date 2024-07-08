package main.java.ru.clevertec.check.entity;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Check {

    private ArrayList<CheckProduct> products;
    private DiscountCard discountCard;
    private double balanceDebitCard;

    public Check(ArrayList<CheckProduct> products, DiscountCard discountCard, double balanceDebitCard) {
        this.products = products;
        this.discountCard = discountCard;
        this.balanceDebitCard = balanceDebitCard;
    }

    public void printCheck() throws Exception {
        double totalPrice = 0;
        double totalDiscount = 0;
        double totalWithDiscount = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Date;Time\n"
                + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + ";"
                + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n\n"
                + "QTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL\n");
        for (CheckProduct p : products) {
            totalPrice += p.getTotalPrice();
            totalDiscount += p.getDiscount();
            totalWithDiscount += p.getTotalPriceWithDiscount();
            stringBuilder.append(p.getProduct().getQuantity() + ";"
                    + p.getProduct().getDescription() + ";"
                    + p.getProduct().getPrice() + "$;"
                    + p.getDiscount() + "$;"
                    + p.getTotalPriceWithDiscount() + "$;\n");
        }
        if (totalWithDiscount > balanceDebitCard) {
            throw new Exception("NOT ENOUGH MONEY");
        }
        stringBuilder.append("\nDISCOUNT CARD;DISCOUNT PERCENTAGE\n");
        stringBuilder.append(discountCard.getNumber() + ";" + discountCard.getDiscount() + "%\n\n");
        stringBuilder.append("TOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT\n");
        stringBuilder.append(totalPrice + "$;"
                + totalDiscount + "$;"
                + totalWithDiscount + "$");
        System.out.print(stringBuilder);
        try (FileOutputStream fileOutputStream = new FileOutputStream("./src/main/resources/check.csv")) {
            byte[] buffer = stringBuilder.toString().getBytes();
            fileOutputStream.write(buffer, 0, buffer.length);
        } catch (Exception ex) {
            System.out.println("Error" + ex.getMessage());
        }

    }

    @Override
    public String toString() {
        return "Check{" +
                "products=" + products +
                ", discountCard=" + discountCard +
                ", balanceDebitCard=" + balanceDebitCard +
                '}';
    }
}
