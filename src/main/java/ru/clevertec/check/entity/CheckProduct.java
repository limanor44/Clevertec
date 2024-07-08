package main.java.ru.clevertec.check.entity;

import main.java.ru.clevertec.check.DAO.DiscountCardDAO;

import java.util.ArrayList;

public class CheckProduct {

    private static final int DISCOUNTIFPRODUCTISWHOLESALE = 10;
    private static final int DISCOUNTIFTHEREDISCOUNTCARD = 2;

    private Product product;
    private double discount;
    private double totalPrice;
    private double totalPriceWithDiscount;

    public CheckProduct() {
    }

    public CheckProduct(Product product, double discount, double totalPrice, double totalPriceWithDiscount) {
        this.product = product;
        this.discount = discount;
        this.totalPrice = totalPrice;
        this.totalPriceWithDiscount = totalPriceWithDiscount;
    }

    public ArrayList<CheckProduct> createCheckProducts(ArrayList<Product> products, DiscountCard discountCard) {
        ArrayList<DiscountCard> cards = new DiscountCardDAO().getAll();
        ArrayList<CheckProduct> checkProducts = new ArrayList<>();
        for (Product p : products) {
            if (discountCard != null) {
                if (p.isWholesale()) {
                    if (p.getQuantity() >= 5) {
                        calculationDiscount(p, DISCOUNTIFPRODUCTISWHOLESALE);
                        checkProducts.add(new CheckProduct(
                                p,
                                discount,
                                totalPrice,
                                totalPriceWithDiscount
                        ));
                        continue;
                    }
                }
                if (cards.contains(discountCard)) {
                    calculationDiscount(p, discountCard.getDiscount());
                } else {
                    calculationDiscount(p, DISCOUNTIFTHEREDISCOUNTCARD);
                }
                checkProducts.add(new CheckProduct(
                        p,
                        discount,
                        totalPrice,
                        totalPriceWithDiscount
                ));
            } else {
                calculationDiscount(p, 0);
                checkProducts.add(new CheckProduct(
                        p,
                        discount,
                        totalPrice,
                        totalPriceWithDiscount
                ));
            }

        }
        return checkProducts;
    }

    private void calculationDiscount(Product product, int discountProduct) {
        totalPrice = product.getPrice() * product.getQuantity();
        totalPriceWithDiscount = (double) Math.round(totalPrice * (1 - (double) discountProduct / 100) * 100) / 100;
        discount = (double) Math.round((totalPrice - totalPriceWithDiscount) * 100) / 100;
    }

    public Product getProduct() {
        return product;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getTotalPriceWithDiscount() {
        return totalPriceWithDiscount;
    }

    @Override
    public String toString() {
        return "CheckProduct{" +
                "product=" + product +
                ", discount=" + discount +
                ", totalPrice=" + totalPrice +
                ", totalPriceWithDiscount=" + totalPriceWithDiscount +
                '}';
    }
}
