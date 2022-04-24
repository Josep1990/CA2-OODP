/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca2;

import com.ca2.companies.BigAlpha;
import com.ca2.companies.BigBeta;
import com.ca2.companies.BigCappa;
import com.ca2.companies.Company;
import com.ca2.companies.depot.Depot;
import com.ca2.companies.product.Product;
import com.ca2.factory.CompanyFactory;
import com.ca2.view.View;
import java.util.List;

/**
 *
 * @author josep
 */
public class Main {

    public static void main(String[] args) {

//        bigAlpha.setName("BIG-ALPHA");
//        bigAlpha.setLocation("71 Bath Av");
//        int x = bigAlpha.getDepots().length;
//        
        CompanyFactory factory = new CompanyFactory();
        factory.init();
        Company bigAlpha = factory.getCompany("BIG-ALPHA"); // BigAlpha.getInstance();
        Company bigBeta = BigBeta.getInstance();
        Company bigCappa = BigCappa.getInstance();
        
          bigCappa.createTransactions(bigAlpha, bigBeta);
        bigAlpha.createTransactions(bigBeta, bigCappa);
        bigBeta.createTransactions(bigCappa, bigAlpha);
      
        
        
        View v = new View();
        v.mainMenu();
        

//        
//        System.out.println(bigAlpha.getDepots()[6].getExternalProducts().size());
//         System.out.println(bigAlpha.getDepots()[1].getExternalProducts().size());
//        System.out.println(bigCappa.getDepots()[1].getTransactions());
//         System.out.println(bigCappa.getDepots()[1].getTransactions().size());
        //  System.out.println(bigCappa.getDepots()[1].getAllowance());
//          System.out.println(bigCappa.getDepots()[0]);
//            System.out.println(bigCappa.getDepots()[1]);
//              System.out.println(bigCappa.getDepots()[2]);
//          
//         System.out.println(bigCappa.getDepots()[5].getTransactions());
//            System.out.println(bigCappa.getDepots()[6].getTransactions());
//        System.out.println(bigBeta.getDepots()[8].getAllowance());
//         System.out.println(bigCappa.getDepots()[1].getExternalProducts().size());
//          System.out.println(bigBeta.getDepots()[1].getExternalProducts().size());
        //  Depot test = Depot.getInstance();
//        
//        System.out.println(bigAlpha.getDepots()[0] + "\n" + bigAlpha.getDepots()[10] + "\n " + bigAlpha.getName());
//        System.out.println(bigBeta.getDepots()[1] + "\n" + bigBeta.getDepots()[10] + "\n " + bigBeta.getName());
//        System.out.println(bigAlpha.getDepots()[2].getNativeProducts());
//        System.out.println(bigBeta.getDepots()[2].getNativeProducts());
//        System.out.println(bigCappa.getDepots()[2].getNativeProducts());
//        System.out.println(bigAlpha.getDepots()[1]);
//
//        System.out.println(bigAlpha.getName());
//        System.out.println(bigBeta.getName());
//        System.out.println(bigCappa.getName());
    }

}
