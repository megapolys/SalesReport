package com.db;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

public class DB {


    private static ArrayList<Product> products = new ArrayList<Product>();
    private static HashSet<String> names = new HashSet<String>();

    static void newProduct(String name) throws App.MyError {
        if (!names.add(name)){
            throw new App.MyError();
        }
    }

    static void purchase(String name, int amount, double price, Calendar date) throws App.MyError {
        if (!names.contains(name) || amount < 1 || price <= 0){
            throw new App.MyError();
        }
        Product p = new Product(name, amount, price, date, Product.PURCHASE);
        addProduct(p);
    }

    static void demand(String name, int amount, double price, Calendar date) throws App.MyError{
        if (!names.contains(name) || amount < 1 || price <= 0){
            throw new App.MyError();
        }
        int allAmount = 0;
        for (int i = 0; i < products.size() && products.get(i).getDate().compareTo(date) <= 0 ; i++) {
            Product p = products.get(i);
            if (p.getName().equals(name)){
                if (p.isType() == Product.PURCHASE){
                    allAmount += p.getAmount();
                } else {
                    allAmount -= p.getAmount();
                }
            }
        }
        if (allAmount - amount >= 0){
            Product p = new Product(name, amount, price, date, Product.DEMAND);
            addProduct(p);
        } else {
            throw new App.MyError();
        }
    }

    private static void addProduct(Product p){
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getDate().compareTo(p.getDate()) > 0){
                products.add(i, p);
                return;
            }
        }
        products.add(p);
    }

    static double salesReport(String name, Calendar date) throws App.MyError{
        if (!names.contains(name)){
            throw new App.MyError();
        }
        int amount = 0;
        double res = 0;
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p.getDate().compareTo(date) > 0){
                break;
            }
            if (p.isType() == Product.DEMAND){
                amount += p.getAmount();
                res += p.getPrice() * p.getAmount();
            }
        }
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p.getDate().compareTo(date) > 0){
                break;
            }
            if (p.isType() == Product.PURCHASE){
                amount -= p.getAmount();
                if (amount <= 0){
                    res -= p.getPrice() * (p.getAmount() + amount);
                    return res;
                } else {
                    res -= p.getPrice() * p.getAmount();
                }
            }
        }
        return res;
    }

    static void clear() {
        products = new ArrayList<Product>();
        names = new HashSet<String>();
    }

}
