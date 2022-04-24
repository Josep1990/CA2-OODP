/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca2.view;

import com.ca2.companies.Company;
import com.ca2.companies.depot.Depot;
import com.ca2.factory.CompanyFactory;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author josep
 */
public class View {

    private final CompanyFactory factory = new CompanyFactory();
    private final Company bigAlpha = factory.getCompany("BIG-ALPHA");
    private final Company bigBeta = factory.getCompany("BIG-BETA");
    private final Company bigCappa = factory.getCompany("BIG-CAPPA");

    public void mainMenu() {

        System.out.println("*** MANAGEMENT SYSTEM ***");

        initMainMenu();

    }

    private void initMainMenu() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Use the menu bellow to navigate through our system.");
            System.out.println("\n");
            System.out.println("1 - See all transactions.");
            System.out.println("2 - See transactions for a particular company.");
            System.out.println("3 - Exit");
            System.out.println("Please enter your option: ");
            int input1 = Integer.parseInt(sc.nextLine());
            switch (input1) {

                case 1:
                    menuOne();

                case 2:
                    menuTwo();

                case 3:
                    System.out.println("Thank you for using our System.");
                    System.exit(0);
            }
        } catch (IllegalArgumentException e) {

            System.out.println("Input not valid, please try again.");
        }
    }

    private void menuOne() {

        System.out.println("Find All Transactios bellow");
        showAllTransactions(bigAlpha);
        showAllTransactions(bigBeta);
        showAllTransactions(bigCappa);
        initMainMenu();

    }

    private void menuTwo() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Choose one company in the list bellow to see it's transactions");
            System.out.println("1 - " + bigAlpha.getName());
            System.out.println("2 - " + bigBeta.getName());
            System.out.println("3 - " + bigCappa.getName());
            System.out.println("4 - Go back to Main Menu.");
            System.out.println("Please enter the Code for the company: ");
            int input1 = Integer.parseInt(sc.nextLine());
            switch (input1) {

                case 1:
                    innerMenuTwo(bigAlpha);

                case 2:
                    innerMenuTwo(bigBeta);

                case 3:
                    innerMenuTwo(bigCappa);

                case 4:
                    initMainMenu();

            }
        } catch (IllegalArgumentException e) {
            
           System.out.println("Input not valid, please try again.");

        }

    }

    private void innerMenuTwo(Company company) {

    }

    private void showAllTransactions(Company company) {

        System.out.println("Transactios for company: \n" + company.getName() + "\nLocation: " + company.getLocation());
        for (Depot depot : company.getDepots()) {

            System.out.println("Depot: " + depot.getDepotCode());
            Collections.sort(depot.getTransactions());
            for (String transaction : depot.getTransactions()) {
                System.out.println(transaction);
            }
        }
        System.out.println("********** END **********\n");

    }

}
