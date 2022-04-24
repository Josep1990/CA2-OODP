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
public class BigCappa extends Company {

      private static BigCappa instance = null;
      
        public static synchronized BigCappa getInstance() {

        if (instance == null) {
            instance = new BigCappa();
        }
        return instance;
    }
}
