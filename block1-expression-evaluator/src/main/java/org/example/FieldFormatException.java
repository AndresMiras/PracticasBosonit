package org.example;

public class FieldFormatException extends Exception{
    public FieldFormatException(String message){
        super("Error in field ocurred... " + message);
    }
}
