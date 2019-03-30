package com.db;

import java.util.Calendar;

public class Product {

    public static final boolean DEMAND = true;
    public static final boolean PURCHASE = false;

    private String name;
    private int amount;
    private double price;
    private Calendar date;
    private boolean type;

    public Product(String name, int amount, double price, Calendar date, boolean type) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.date = date;
        this.type = type;
    }



    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public Calendar getDate() {
        return date;
    }

    public boolean isType() {
        return type;
    }

}
