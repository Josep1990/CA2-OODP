/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca2.companies.depot;

import com.ca2.companies.product.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josep
 */
public class Depot {

    private final int NATIVE_PRODUCT_MAX_LIMIT = 40;
    private final int NATIVE_PRODUCT_MIN_LIMIT = 15;
    private final int EXTERNAL_PRODUCT_MAX_LIMIT = 30;
    private final int EXTERNAL_PRODUCT_MIN_LIMIT = 2;
    private int depotCode;
    private int nativeProductAmount;
    private int externalProductAmount;
    private int deliveryPrice;
    private int allowance;
    private List<Product> nativeProducts;
    private List<Product> externalProducts;
    private List<String> transactions = new ArrayList<>();

    public Depot() {
    }

    public Depot(int depotCode, int nativeProductAmount, int externalProductAmount, int deliveryPrice, int allowance) {
        this.depotCode = depotCode;
        this.nativeProductAmount = nativeProductAmount;
        this.externalProductAmount = externalProductAmount;
        this.deliveryPrice = deliveryPrice;
        this.allowance = allowance;
    }

    public int getNATIVE_PRODUCT_MAX_LIMIT() {
        return NATIVE_PRODUCT_MAX_LIMIT;
    }

    public int getNATIVE_PRODUCT_MIN_LIMIT() {
        return NATIVE_PRODUCT_MIN_LIMIT;
    }

    public int getEXTERNAL_PRODUCT_MAX_LIMIT() {
        return EXTERNAL_PRODUCT_MAX_LIMIT;
    }

    public int getEXTERNAL_PRODUCT_MIN_LIMIT() {
        return EXTERNAL_PRODUCT_MIN_LIMIT;
    }

    public int getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(int depotCode) {
        this.depotCode = depotCode;
    }

    public int getNativeProductAmount() {
        return nativeProductAmount;
    }

    public void setNativeProductAmount(int nativeProductAmount) {
        this.nativeProductAmount = nativeProductAmount;
    }

    public int getExternalProductAmount() {
        return externalProductAmount;
    }

    public void setExternalProductAmount(int externalProductAmount) {
        this.externalProductAmount = externalProductAmount;
    }

    public int getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(int deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public int getAllowance() {
        return allowance;
    }

    public void setAllowance(int allowance) {
        this.allowance = allowance;
    }

    public List<Product> getNativeProducts() {
        return nativeProducts;
    }

    public void setNativeProducts(List<Product> nativeProducts) {
        this.nativeProducts = nativeProducts;
    }

    public List<Product> getExternalProducts() {
        return externalProducts;
    }

    public void setExternalProducts(List<Product> externalProducts) {
        this.externalProducts = externalProducts;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<String> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Depot Code : " + depotCode
                + "\nNative Product Amount: " + nativeProductAmount
                + "\nExternal Product Amount: " + externalProductAmount
                + "\nDelivery Price: " + deliveryPrice
                + "\nAllowance: " + allowance;
    }

}
