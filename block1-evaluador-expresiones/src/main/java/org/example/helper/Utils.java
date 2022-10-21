package org.example.helper;

public class Utils {

    public static String formatWithQuotation(String str){
        return "“" + str.trim() + "”";
    }
    public static String unformatWithQuotation(String str){
        str = str.replace("“" , "" );
        str = str.replace("”" , "" );
        return str;
    }

    public static String multipliString(String string, int multiplicator){
        // Se guarda el original para poder proceder a multiplicarlo.
        String auxString = string;
        for (int i = 1; i < multiplicator; i++) {
            string += auxString;
        }
        return string;
    }
}
