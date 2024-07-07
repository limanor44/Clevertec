package main.java.ru.clevertec.check.entity;

import main.java.ru.clevertec.check.DAO.DiscountCardDAO;

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

    public void createCheck(){

    }
}
