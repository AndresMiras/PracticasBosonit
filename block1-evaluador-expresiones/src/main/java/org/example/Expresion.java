package org.example;
import org.example.helper.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Expresion {
    private String string;
    private Date date;
    private int integer;
    private String type;
    private String suma;
    private String resta;
    private String multi;
    private String divi;
    private String mayorque;
    private String menorque;

    public Expresion(Expresion expression) {
        this.string = expression.string;
        this.date = expression.date;
        this.integer = expression.integer;
        this.type = expression.type;
        this.suma = expression.suma;
        this.resta = expression.resta;
        this.multi = expression.multi;
        this.divi = expression.divi;
        this.mayorque = expression.mayorque;
        this.menorque = expression.menorque;
    }

    public Expresion(){
        suma = "+";
        resta = "-";
        multi = "*";
        divi = "/";
        mayorque = ">";
        menorque = "<";
        type="";
        string = "";
        date = new Date();
        integer = 0;
    }

    public String getString() {
        return string;
    }

    public String getType() {
        return type;
    }

    public int getInteger() {
        return integer;
    }

    public String getSuma() {
        return suma;
    }

    public String getResta() {
        return resta;
    }

    public String getMulti() {
        return multi;
    }

    public String getDivi() {
        return divi;
    }

    public String getMayorque() {
        return mayorque;
    }

    public String getMenorque() {
        return menorque;
    }

    public Date getDate() {
        return date;
    }

    public void setInteger(int integer) {
        this.integer = integer;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTypeOfVariable(String expresion){

//      Verifico si es una fecha.
        if(MyDate.isDate(expresion,"yyyy/MM/dd")){
            date = MyDate.parseDate(expresion, "yyyy/MM/dd");
            type = "date";

//      Verifico si es un número.
        } else if(Compare.isNumber(expresion)) {
            integer = Integer.parseInt(expresion);
            type = "number";
        } else if(expresion.equals(suma)) {
            type = suma;
        } else if(expresion.equals(resta)) {
            type = resta;
        } else if(expresion.equals(multi)) {
            type = multi;
        } else if(expresion.equals(divi)) {
            type = divi;
        } else if(expresion.equals(mayorque)) {
            type = mayorque;
        } else if(expresion.equals(menorque)) {
            type = menorque;

//      Verifico si es una cadena printeable.
        } else if(Compare.isAsciiPrintable(expresion)) {
            string = expresion;
            type = "string";
        } else {
            try {
                throw new FieldFormatException("Error... typo de dato no válido en ( " + expresion);
            } catch (FieldFormatException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return type.equals("date") ? dateFormat.format(date):
               type.equals("number") ? String.valueOf(integer) :
               type.equals("string") ? string :
               type;
    }
}
