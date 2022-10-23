package org.example.helper;

import java.nio.charset.StandardCharsets;


public class Compare {
    public static boolean isNumber(String str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    public static int countChars(String str, String wantedChar){
        return (int) str.chars().filter(ch -> ch == wantedChar.getBytes(StandardCharsets.US_ASCII)[0]).count();
    }


}
