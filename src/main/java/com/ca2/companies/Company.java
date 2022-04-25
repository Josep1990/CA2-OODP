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
public class Company { //this is the super class of all companies
    
    //common fields for all the companies that will be inherited
    private Depot[] depots = new Depot[40];
    private String nativeProduct;
    private String name;
    private String location;
    
    //below we have getters and setters 
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
    
    //this method start all the transactons, here I decided to initialize the 3 companies transactions in one method due the limits and values required
    public void createTransactions(Company suplier1, Company suplier2) {
      
        Random rd = new Random();

        for (int i = 0; i < depots.length; i++) { //this for loop through the depots and getting the information
            List<Product> externalProduct = new ArrayList<>(); //this list will hold all the external products           
            int maxExternalProductLimit = depots[i].getEXTERNAL_PRODUCT_MAX_LIMIT();//this filds hold the limit for each product
            int minExternalProductLimit = depots[i].getEXTERNAL_PRODUCT_MIN_LIMIT();
            int externalProcuctAmount = depots[i].getExternalProductAmount();
            int minNativeProductLimit = suplier1.depots[i].getNATIVE_PRODUCT_MIN_LIMIT();
            
            //buyProdcuct generate a integer between the min and mas limit and it will be used to limit the trnasactions
            int buyProdocut = (rd.nextInt(maxExternalProductLimit - minExternalProductLimit) + minExternalProductLimit) / 2;

            for (int j = 0; j < buyProdocut; j++) { //this loop is responsible for buy and sell products inside each depot
                //this if statment checks the conditos to procede to the tradings between the companies
                if (depots[i].getAllowance() > 0 
                        || suplier1.depots[i].getNativeProductAmount() > minNativeProductLimit
                        || externalProcuctAmount <= maxExternalProductLimit) {
                    //this variable will hold the values for each product and the product object itself
                    Product externalProduct1 = null;
                    String product1Code = "";
                    int product1Price = 0, product1DepotDeliveryPrice = 0, product1Cost = 0, product1DepotCode = 0;

                    Product externalProduct2 = null;
                    String product2Code = "";
                    int product2Price = 0, product2DepotDeliveryPrice = 0, product2Cost = 0, product2DepotCode = 0;

                    if (j < suplier1.depots[i].getNativeProducts().size()) {
                        externalProduct1 = suplier1.depots[i].getNativeProducts().remove(j); //removes one product from the suplier stock
                        product1DepotCode = suplier1.depots[i].getDepotCode();//get which depot it comes from
                        product1Price = externalProduct1.getPrice(); //get the product price
                        product1DepotDeliveryPrice = suplier1.depots[i].getDeliveryPrice();//delivery price
                        product1Cost = product1Price + product1DepotDeliveryPrice; //this is the cost of the product with delivery
                        product1Code = externalProduct1.getCode(); //get the name of the product
                        externalProduct1.setIsNative(false);//set the product as non native when gettin into the customer depot
                        if (product1Cost < depots[i].getAllowance()) { //if the depot has money to buy the prouducts it proced with operaton
                            externalProduct.add(externalProduct1);//add the product to an array list to be added to a depot
                            String transactionSuplier1 = "Bought products from : " + suplier1.getName() //create a string transaction type buy to be stored in the transactios property
                                    + ", Depot Code: " + product1DepotCode
                                    + ", Product: " + product1Code
                                    + ", Price: " + product1Price
                                    + ", Delivery Price: " + product1DepotDeliveryPrice
                                    + ", Total Cost: " + product1Cost;
                            String transactionSuplier1sell = "Sold product to : " + this.name //create an string transaction type sell to be stored in the transaction
                                    + ", Depot Code: " + depots[i].getDepotCode()
                                    + ", Product: " + product1Code
                                    + ", Price: " + product1Price
                                    + ", Delivery Price: " + product1DepotDeliveryPrice
                                    + ", Total Cost: " + product1Cost;
                            suplier1.depots[i].getTransactions().add(transactionSuplier1sell); //add the transaction to a list for record purposes
                            depots[i].getTransactions().add(transactionSuplier1); //add the transaction type buy to the customer for record                          
                            depots[i].setAllowance(depots[i].getAllowance() - product1Cost); //it takes the cost of the product from the depot allowance

                        } else {
                            suplier1.depots[i].getNativeProducts().add(externalProduct1); //in case the depot has no money to buy the product the item is returned to its producer

                        }

                    }
                    if (j < suplier2.depots[i].getNativeProducts().size()) { //this if statment does the exactly same thing as the above, it is a code duplication, but because the values
                        externalProduct2 = suplier2.depots[i].getNativeProducts().remove(j);//and restrictions of each depot it was the best way to make sure every depot can make at least one trade
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
                            suplier2.depots[i].getNativeProducts().add(externalProduct2);//in case the depot has no money to buy the product the item is returned to its producer
                            break;//breaks out of the loop is the depot has no money left going to start transaction with a new depot
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
