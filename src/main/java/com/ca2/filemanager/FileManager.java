/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca2.filemanager;

import com.ca2.companies.Company;
import com.ca2.companies.depot.Depot;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josep
 */
public class FileManager {
    
    private static FileManager instance = null;
    
    //this method writes all trnsaction of a companuy to a file
    public void writeTransactionsToFile(Company company) {

      
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm");  
        String formatDateTime = date.format(format);  
        String transactiosFile = company.getName()+"-" +formatDateTime+ ".txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(transactiosFile, true));
            
            for (Depot depot : company.getDepots()) {
                depot.getTransactions().forEach(transaction -> {
                    try {
                        writer.write(transaction);
                        writer.newLine();
                    } catch (IOException ex) {
                        Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }

            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, e);
        }

    }
    //this method wirtes all the depot information to a file
    public void writeDepotsToFile(Company company) {
        
       LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm");  
        String formatDateTime = date.format(format);  
        String depotsFile = company.getName()+ "-" +formatDateTime+ "_depots_register.txt";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(depotsFile, true));

            for (Depot depot : company.getDepots()) {

                writer.write(depot.toString());
                writer.newLine();

            }

            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }
     //this method return a instance of FileManager class usign the singleton desig patter
    public static synchronized FileManager getInstance() {

        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

}
