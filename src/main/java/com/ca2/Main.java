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

        View v = View.getInstance(); //get instance of View class and start menu
        v.mainMenu();//start the main menu

    }

}
