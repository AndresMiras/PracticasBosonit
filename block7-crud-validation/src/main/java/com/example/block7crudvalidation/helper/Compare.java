package com.example.block7crudvalidation.helper;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Compare {

    public static int countChars(String str, String wantedChar){
        return (int) str.chars().filter(ch -> ch == wantedChar.getBytes(StandardCharsets.US_ASCII)[0]).count();
    }

    public static boolean isNumber(String str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    public static boolean isAsciiPrintable(String str) {
        boolean flat = true;
        if (str == null) {
            flat = false;
        }
        int totalChars = str.length();
        for (int index = 0; index < totalChars && flat; index++) {
            int ASCIIValue = str.charAt(index);
            if (ASCIIValue > 126 && ASCIIValue < 32) {
                flat = false;
            }
        }
        return flat;
    }

    public static Boolean isString(String str) {
        Pattern pat = Pattern.compile("^[a-zA-ZÀ-ÿ0-9\u00C0-\u00FF]*$");
        Matcher mat = pat.matcher(str);

        return mat.matches();
    }

    public static Boolean isLettersOrNumbers(String str) {
        Pattern pat = Pattern.compile("^[a-zA-ZÀ-ÿ0-9\\u00f1\\u00d1]+(\\s*[a-zA-ZÀ-ÿ0-9\\u00f1\\u00d1]*)*[a-zA-ZÀ-ÿ0-9\\u00f1\\u00d1]+$");
        Matcher mat = pat.matcher(str);

        return mat.matches();
    }

    public static boolean isIncludeInString(String string, String[] stringList){
        boolean flat = false;

        for (int i = 0; i < stringList.length && !flat; i++) {
            if(string.equals(stringList[i])) flat = true;
        }

        return flat;
    }

    public static boolean isEmail(String email) {
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        // El email a validar
        email = "info@programacionextrema.com";

        Matcher mather = pattern.matcher(email);

        return mather.find() ? true : false;
    }
}
