/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca2.factory;

import com.ca2.companies.BigAlpha;
import com.ca2.companies.BigBeta;
import com.ca2.companies.BigCappa;
import com.ca2.companies.Company;
import com.ca2.companies.depot.Depot;
import com.ca2.companies.product.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author josep
 */
public class CompanyFactory {

    private final String bigAlphaName = "BIG-ALPHA";
    private final String alphaLocation = "75, Oxford - Industrial District";
    private final String alphaNativeProduct = "WIDGETS";
    
    private final String bigBetaName = "BIG-BETA";
    private final String betaLocation = "35, Austing Street, Mullingar - M5 CD87";
    private final String betaNativeProduct = "BRACES";
    
    private final String bigCappaName = "BIG-CAPPA";
    private final String cappaLocation = "98, Woodburn Park, Derry";
    private final String cappaNativeProduct = "CRATES";

    public void init() {

        startCompany(getCompany(bigAlphaName), bigAlphaName, alphaLocation, alphaNativeProduct);
        startCompany(getCompany(bigBetaName), bigBetaName, betaLocation, betaNativeProduct);
        startCompany(getCompany(bigCappaName), bigCappaName, cappaLocation, cappaNativeProduct);

    }

    public Company getCompany(String company) {

        if (company == null) {
            return null;
        }
        if (company.equalsIgnoreCase(bigAlphaName)) {
            return BigAlpha.getInstance();

        } else if (company.equalsIgnoreCase(bigBetaName)) {
            return BigBeta.getInstance();

        } else if (company.equalsIgnoreCase(bigCappaName)) {
            return BigCappa.getInstance();
        }

        return null;

    }

    private void startCompany(Company company, String companyName, String companyLocation, String nativeProduct) {

        int length = company.getDepots().length;
        Depot[] depots = new Depot[length];
        company.setName(companyName);
        company.setLocation(companyLocation);
        company.setNativeProduct(nativeProduct);

        for (int i = 0; i < length; i++) { //load each depot with products and generate price and allowance for it.

            depots[i] = createDepot(company.getNativeProduct());
            depots[i].setDepotCode(i + 1);
        }

        company.setDepots(depots);//set company depot            

    }

    private Depot createDepot(String productCode) {
        Depot depot = new Depot();
        Random r = new Random();
        int maxAllowance = 100;
        int minAllowance = 50;
        int maxDeliveryPrice = 10;
        int minDeliveryPrice = 1;
        int maxDepotNativeProductLimit = depot.getNATIVE_PRODUCT_MAX_LIMIT();
        int minDepotNativeProductLimit = depot.getNATIVE_PRODUCT_MIN_LIMIT();
        int depotAllowance = r.nextInt(maxAllowance - minAllowance) + minAllowance; //allowance for depot
        int deliveryPrice = r.nextInt(maxDeliveryPrice - minDeliveryPrice) + minDeliveryPrice; //delivery price    
        depot.setAllowance(depotAllowance);
        depot.setDeliveryPrice(deliveryPrice);
        depot.setNativeProducts(createNativeProducts(productCode, maxDepotNativeProductLimit, minDepotNativeProductLimit));// create the native product for each depot
        depot.setNativeProductAmount(depot.getNativeProducts().size());

        return depot;
    }

    //create native products in between the max and min depot capacity
    private List<Product> createNativeProducts(String productCode, int maxDepotNativeProductLimit, int minDepotNativeProductLimit) {
        Random r = new Random();
        List<Product> products = new ArrayList<>();
        int maxProductPrice = 10;
        int minProductPrice = 1;
        int production = r.nextInt(maxDepotNativeProductLimit - minDepotNativeProductLimit) + minDepotNativeProductLimit; //depot inital stock
        int productPrice = r.nextInt(maxProductPrice - minProductPrice) + minProductPrice; //product price

        for (int i = 0; i < production; i++) {

            Product product = new Product(productCode, productPrice, true);
            products.add(product);
        }

        return products;
    }
}
