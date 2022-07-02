package com.api.link.handel;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import com.datastax.oss.driver.shaded.guava.common.hash.Hashing;


public class CreateURL{
    public static final String ALPHABET = "23456789bcdfghjkmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ-_";
   
    public String random(long number){
        try {
            StringBuilder str = new StringBuilder();
            int num = Integer.parseInt(String.valueOf(number)) + 100000;;

            while (num > 0) {
                str.insert(0, ALPHABET.charAt(num % 10));
                num = num / 10;
            }
            return str.toString();
        } catch (Exception e) {
            return null;
        }
    }

}
