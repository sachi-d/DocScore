package com.cfscr;

import java.math.BigDecimal;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        BigDecimal bd = new BigDecimal("3");
        bd.setScale(2);
        float m = bd.floatValue();
        System.out.println(m);
    }
}
