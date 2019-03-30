package com.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class App {

    static class MyError extends Exception {}

    public App(){
        System.out.println("To exit print \"exit\"");
        cycle();
    }

    private static void cycle(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while (true){
            try {
                line = br.readLine().trim().toUpperCase();
                System.out.println(executeMethod(line));
            } catch (IOException e) {
                System.out.println("ERROR");
            }
        }
    }

    public static String executeMethod(String line){
        try {
            String[] split = line.split(" +");
            if (split.length == 0) {
                throw new MyError();
            } else if (split[0].equals("NEWPRODUCT")) {
                if (split.length != 2) {
                    throw new MyError();
                }
                DB.newProduct(split[1]);
                return "OK";
            } else if (split[0].equals("PURCHASE")) {
                if (split.length != 5) {
                    throw new MyError();
                }
                DB.purchase(split[1], checkInt(split[2]), checkDouble(split[3]), checkCalendar(split[4]));
                return "OK";
            } else if (split[0].equals("DEMAND")) {
                if (split.length != 5) {
                    throw new MyError();
                }
                DB.demand(split[1], checkInt(split[2]), checkDouble(split[3]), checkCalendar(split[4]));
                return "OK";
            } else if (split[0].equals("SALESREPORT")) {
                if (split.length != 3) {
                    throw new MyError();
                }
                Double salesReport = DB.salesReport(split[1], checkCalendar(split[2]));
                return salesReport.toString();
            } else if (split[0].equals("EXIT")) {
                System.exit(0);
            } else {
                throw new MyError();
            }
        } catch (MyError e) {
            return "ERROR";
        }
        return "ERROR";
    }

    private static int checkInt(String s) throws MyError{
        int i = 0;
        try {
            i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new MyError();
        }
        return i;
    }

    private static double checkDouble(String s) throws MyError{
        double d = 0.0;
        try {
            d = Double.parseDouble(s);
        } catch (NumberFormatException e) {
            throw new MyError();
        }
        return d;
    }

    private static Calendar checkCalendar(String s) throws MyError{
        String[] split = s.split("[.:]");
        Calendar calendar = Calendar.getInstance();
        int date = checkInt(split[0]);
        int month = checkInt(split[1]) - 1;
        int year = checkInt(split[2]);
        if (year < 1960 || month > 11 || month < 0 || date < 1 || date > 31){
            throw new MyError();
        }
        calendar.set(year, month, date);
        return calendar;
    }

}
