/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca2.companies;

/**
 *
 * @author josep
 */
public class BigAlpha extends Company {

   //this is the the Big Alpha company get instance method as it is static was not possible to create it in the super class 
    private static BigAlpha instance = null;    
   //thread safe instanciation 
    public static synchronized Company getInstance() {

        if (instance == null) {
            instance = new BigAlpha();
        }
        return instance;
    }

}
