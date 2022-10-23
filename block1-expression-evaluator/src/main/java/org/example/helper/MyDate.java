package org.example.helper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyDate {
    LocalDate date;
    int year;
    int month;
    int day;

    public MyDate(String yearMonthDay, String separator) {
        String[] dates = yearMonthDay.split(separator);
        year = Integer.parseInt(dates[0]);
        month = Integer.parseInt(dates[1]);
        day = Integer.parseInt(dates[2]);
        this.date = LocalDate.of(year, month, day);
    }

    public static DateTimeFormatter formatter(String format){
        return DateTimeFormatter.ofPattern(format, Locale.ENGLISH);
    }

    public static boolean isDate(String expresion, String format){
        try {
            SimpleDateFormat date = new SimpleDateFormat(format, Locale.ENGLISH);
            date.parse(expresion);
            return true;
        } catch (ParseException e) {
//            e.printStackTrace();
            return false;
        }
    }

    public static boolean isDateFormat(String expresion, String format){
        Pattern pat = Pattern.compile(format);
        Matcher mat = pat.matcher(expresion);
//        "\\d{4}/\\d{1,2}/\\d{1,2}"
        return mat.matches();
    }

    public static Date parseDate(String expresion, String format){
        Date parsedDate = new Date();
        try {
            SimpleDateFormat date = new SimpleDateFormat(format, Locale.ENGLISH);
            parsedDate = date.parse(expresion);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
    }
}
