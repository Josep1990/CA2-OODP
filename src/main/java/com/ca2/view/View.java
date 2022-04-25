/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca2.view;

import com.ca2.companies.Company;
import com.ca2.companies.depot.Depot;
import com.ca2.factory.CompanyFactory;
import com.ca2.filemanager.FileManager;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author josep
 * this class is responsible for the elements of the menu
 */
public class View {
    //here we get the companies for the menu
    private static View instance = null;
    private final CompanyFactory factory = new CompanyFactory();
    private final Company bigAlpha = factory.getCompany("BIG-ALPHA");
    private final Company bigBeta = factory.getCompany("BIG-BETA");
    private final Company bigCappa = factory.getCompany("BIG-CAPPA");
    
    //mainMenu method call all the other methods to generate transactions and then start the main menu for navigation
    public void mainMenu() {

        startTrnsactions();
        System.out.println("*** MANAGEMENT SYSTEM ***");

        initMainMenu();

    }

    //this method start all transactions buy and sell items between the companies and also save it to a file with time stamp for later stage
    private void startTrnsactions() {
        factory.init();
        bigCappa.createTransactions(bigAlpha, bigBeta);
        bigAlpha.createTransactions(bigBeta, bigCappa);
        bigBeta.createTransactions(bigCappa, bigAlpha);
        //this methods save the current state of each depot and rnsaction to a file
        //creting a new file every time the companies sell and buy items
        FileManager writeFile = FileManager.getInstance();
        writeFile.writeTransactionsToFile(bigAlpha);
        writeFile.writeDepotsToFile(bigAlpha);
        writeFile.writeTransactionsToFile(bigBeta);
        writeFile.writeDepotsToFile(bigBeta);
        writeFile.writeTransactionsToFile(bigCappa);
        writeFile.writeDepotsToFile(bigCappa);
    }
    //this method launch the first menu option calling diffent methid accordingly
    private void initMainMenu() {
        while (true) { //this loop runs the meu until the user press exit or close the program
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Use the menu bellow to navigate through our system.");
                System.out.println("\n");
                System.out.println("1 - See all transactions.");
                System.out.println("2 - See transactions for a particular company.");
                System.out.println("0 - Exit");
                System.out.println("Please enter your option: ");
                int input = Integer.parseInt(sc.nextLine());
                switch (input) {

                    case 1:
                        menuOne();

                    case 2:
                        menuTwo();

                    case 0:
                        System.out.println("Thank you for using our System.");
                        System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("Input not valid, please try again.");
            }
        }
    }
    //this method execute the first option of the menu and initilize the main menu again
    private void menuOne() { 

        System.out.println("Find All Transactios bellow");
        showAllTransactions(bigAlpha);
        showAllTransactions(bigBeta);
        showAllTransactions(bigCappa);
        initMainMenu();

    }
    //this method start the option number 2 in the menu the gives the user the opportunity to see each company in more details
    private void menuTwo() {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Choose one company in the list bellow to see it's transactions");
                System.out.println("1 - " + bigAlpha.getName());
                System.out.println("2 - " + bigBeta.getName());
                System.out.println("3 - " + bigCappa.getName());
                System.out.println("4 - Go back to Main Menu.");
                System.out.println("0 - Exit");
                System.out.println("Please enter the Code for the company: ");
                int input = Integer.parseInt(sc.nextLine());
                switch (input) {

                    case 1:
                        innerMenuTwo(bigAlpha);

                    case 2:
                        innerMenuTwo(bigBeta);

                    case 3:
                        innerMenuTwo(bigCappa);

                    case 4:
                        initMainMenu();

                    case 0:
                        System.out.println("Thank you for using our System.");
                        System.exit(0);

                }
            } catch (Exception e) {

                System.out.println("Input not valid, please try again.");

            }
        }

    }
    //this method start a inner menu on the option number 2 for user navigation inside each company
    private void innerMenuTwo(Company company) {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("In this menu you can navigate through: " + company.getName() + " transactions");
                System.out.println("Please choose one of the options below:");
                System.out.println("1 - See all the depots.");
                System.out.println("2 - See an indivdual Depot and it's transactions using the Depot code:");
                System.out.println("3 - See all transactions in all depots of: " + company.getName());
                System.out.println("4 - Go back and choose other company.");
                System.out.println("5 - Go back to Main Menu.");
                System.out.println("0 - Exit");
                System.out.println("Please enter the Code for the selected option: ");
                int input = Integer.parseInt(sc.nextLine());
                switch (input) {

                    case 1:
                        showAllDepots(company);
                        innerMenuTwo(company);

                    case 2:
                        depotCodeList(company);
                        innerMenuTwoDepots(company);

                    case 3:
                        showAllTransactions(company);
                        innerMenuTwo(company);

                    case 4:
                        menuTwo();

                    case 5:
                        initMainMenu();

                    case 0:
                        System.out.println("Thank you for using our System.");
                        System.exit(0);

                }
            } catch (Exception e) {

                System.out.println("Input not valid, please try again.");

            }
        }
    }
    //this method show a list of depots codes when user requires retrival of depot information
    private void depotCodeList(Company company) {
        for (Depot depot : company.getDepots()) {
            System.out.print(depot.getDepotCode() + ", ");
        }
        System.out.println("\n");

    }
//this method show all the information related to a single depot from a specif compnay
    private void innerMenuTwoDepots(Company company) {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Please enter the depot code: ");
                int depotCode = sc.nextInt();
                Depot depot = getDepotByCode(depotCode, company);
                System.out.println(depot);
                System.out.println("\nTransactions for this depot. \n");
                depot.getTransactions().forEach(transaction -> {
                    System.out.println(transaction);
                });
                System.out.println("\n");
                showAllProducts(company, depot);
                System.out.println("********** END **********\n");

            } catch (Exception e) {

                System.out.println("Input not valid, please try again.");

            }
            innerMenuTwo(company);
        }

    }
    //this method retuen the depot based on its code
    private Depot getDepotByCode(int depotCode, Company company) {

        for (Depot depot : company.getDepots()) {
            if (depot.getDepotCode() == depotCode) {
                return depot;
            }
        }
        return null;

    }
    //this method shows the products inside a depot a depot
    private void showAllProducts(Company company, Depot depot) {

        System.out.println("Native Product Stock");
        depot.getNativeProducts().forEach(product -> {
            System.out.println(product);
        });
        System.out.println("\n");
        System.out.println("External Product Stock");
        depot.getExternalProducts().forEach(product -> {
            System.out.println(product);
        });

    }
    //this method show all depots in a company
    private void showAllDepots(Company company) {
        System.out.println("List of Depots: \n" + company.getName() + "\nLocation: " + company.getLocation());
        for (Depot depot : company.getDepots()) {
            System.out.println(depot);
            System.out.println("\n");
        }
        System.out.println("********** END **********\n");

    }
    //this method show all transactions for each depot in a company
    private void showAllTransactions(Company company) {

        System.out.println("Transactios for company: \n" + company.getName() + "\nLocation: " + company.getLocation());
        for (Depot depot : company.getDepots()) {

            System.out.println("Depot: " + depot.getDepotCode());
            Collections.sort(depot.getTransactions());
            depot.getTransactions().forEach(transaction -> {
                System.out.println(transaction);
            });
        }
        System.out.println("********** END **********\n");

    }
    //this method return a instance of view class usign the singleton desig patter
    public static synchronized View getInstance() {

        if (instance == null) {
            instance = new View();
        }
        return instance;
    }

}
