/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca2.companies;

import com.ca2.companies.depot.Depot;
import com.ca2.companies.product.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author josep
 */
public class Company {

    private Depot[] depots = new Depot[40];
    private String nativeProduct;
    private String name;
    private String location;

    public Depot[] getDepots() {
        return depots;
    }

    public void setDepots(Depot[] depots) {
        this.depots = depots;
    }

    public String getNativeProduct() {
        return nativeProduct;
    }

    public void setNativeProduct(String nativeProduct) {
        this.nativeProduct = nativeProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void createTransactions(Company suplier1, Company suplier2) {
        int test = 0;
        Random rd = new Random();

        for (int i = 0; i < depots.length; i++) {
            List<Product> externalProduct = new ArrayList<>();           
            int maxExternalProductLimit = depots[i].getEXTERNAL_PRODUCT_MAX_LIMIT();
            int minExternalProductLimit = depots[i].getEXTERNAL_PRODUCT_MIN_LIMIT();
            int externalProcuctAmount = depots[i].getExternalProductAmount();
            int minNativeProductLimit = suplier1.depots[i].getNATIVE_PRODUCT_MIN_LIMIT();

            int buyProdocut = (rd.nextInt(maxExternalProductLimit - minExternalProductLimit) + minExternalProductLimit) / 2;

            for (int j = 0; j < buyProdocut; j++) {
                if (depots[i].getAllowance() > 0
                        || suplier1.depots[i].getNativeProductAmount() > minNativeProductLimit
                        || externalProcuctAmount <= maxExternalProductLimit) {

                    Product externalProduct1 = null;
                    String product1Code = "";
                    int product1Price = 0, product1DepotDeliveryPrice = 0, product1Cost = 0, product1DepotCode = 0;

                    Product externalProduct2 = null;
                    String product2Code = "";
                    int product2Price = 0, product2DepotDeliveryPrice = 0, product2Cost = 0, product2DepotCode = 0;

                    if (j < suplier1.depots[i].getNativeProducts().size()) {
                        externalProduct1 = suplier1.depots[i].getNativeProducts().remove(j);
                        product1DepotCode = suplier1.depots[i].getDepotCode();
                        product1Price = externalProduct1.getPrice();
                        product1DepotDeliveryPrice = suplier1.depots[i].getDeliveryPrice();
                        product1Cost = product1Price + product1DepotDeliveryPrice;
                        product1Code = externalProduct1.getCode();
                        externalProduct1.setIsNative(false);
                        if (product1Cost < depots[i].getAllowance()) {
                            externalProduct.add(externalProduct1);
                            String transactionSuplier1 = "Bought products from : " + suplier1.getName()
                                    + ", Depot Code: " + product1DepotCode
                                    + ", Product: " + product1Code
                                    + ", Price: " + product1Price
                                    + ", Delivery Price: " + product1DepotDeliveryPrice
                                    + ", Total Cost: " + product1Cost;
                            String transactionSuplier1sell = "Sold product to : " + this.name
                                    + ", Depot Code: " + depots[i].getDepotCode()
                                    + ", Product: " + product1Code
                                    + ", Price: " + product1Price
                                    + ", Delivery Price: " + product1DepotDeliveryPrice
                                    + ", Total Cost: " + product1Cost;
                            suplier1.depots[i].getTransactions().add(transactionSuplier1sell);
                            depots[i].getTransactions().add(transactionSuplier1);                          
                            depots[i].setAllowance(depots[i].getAllowance() - product1Cost);

                        } else {
                            suplier1.depots[i].getNativeProducts().add(externalProduct1);

                        }

                    }
                    if (j < suplier2.depots[i].getNativeProducts().size()) {
                        externalProduct2 = suplier2.depots[i].getNativeProducts().remove(j);
                        product2DepotCode = suplier2.depots[i].getDepotCode();
                        product2Price = externalProduct2.getPrice();
                        product2DepotDeliveryPrice = suplier2.depots[i].getDeliveryPrice();
                        product2Cost = product2Price + product2DepotDeliveryPrice;
                        product2Code = externalProduct2.getCode();
                        externalProduct2.setIsNative(false);
                        if (product2Cost < depots[i].getAllowance()) {
                            externalProduct.add(externalProduct2);
                            String transactionSuplier2 = "Bought products from : " + suplier2.getName()
                                    + ", Depot Code: " + product2DepotCode
                                    + ", Product: " + product2Code
                                    + ", Price: " + product2Price
                                    + ", Delivery Price: " + product2DepotDeliveryPrice
                                    + ", Total Cost: " + product2Cost;
                              String transactionSuplier2sell = "Sold product to : " + this.name
                                    + ", Depot Code: " + depots[i].getDepotCode()
                                    + ", Product: " + product2Code
                                    + ", Price: " + product2Price
                                    + ", Delivery Price: " + product2DepotDeliveryPrice
                                    + ", Total Cost: " + product2Cost;
                            suplier2.depots[i].getTransactions().add(transactionSuplier2sell);
                            depots[i].getTransactions().add(transactionSuplier2);                           
                            depots[i].setAllowance(depots[i].getAllowance() - product2Cost);
                        } else {
                            suplier2.depots[i].getNativeProducts().add(externalProduct2);
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
            depots[i].setExternalProducts(externalProduct);
            depots[i].setExternalProductAmount(externalProduct.size());
          //  depots[i].setTransactions(transactions);

        }

    }

    @Override
    public String toString() {
        return "Company Name: " + name + "\nLocation: " + location;
    }

}
