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
public class BigBeta extends Company {
    
          private static BigBeta instance = null;
      
        public static synchronized BigBeta getInstance() {

        if (instance == null) {
            instance = new BigBeta();
        }
        return instance;
    }
    
}
