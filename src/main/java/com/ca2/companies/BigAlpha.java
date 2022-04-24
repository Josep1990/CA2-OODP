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
    
      private static BigAlpha instance = null;
      
        public static synchronized Company getInstance() {

        if (instance == null) {
            instance = new BigAlpha();
        }
        return instance;
    }
    
    
    
    
}
