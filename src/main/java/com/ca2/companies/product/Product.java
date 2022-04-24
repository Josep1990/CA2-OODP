/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca2.companies.product;

/**
 *
 * @author josep
 */
public class Product {

    private String code;
    private int price;
    private boolean isNative;

    public Product() {
    }

    public Product(String code, int price, boolean isNative) {
        this.code = code;
        this.price = price;
        this.isNative = isNative;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isIsNative() {
        return isNative;
    }

    public void setIsNative(boolean isNative) {
        this.isNative = isNative;
    }

    @Override
    public String toString() {
        return "Product Code: " + code + " | price: " + price + " | Native Product: " + isNative;
    }

}
