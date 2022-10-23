package org.example;
import java.io.IOException;

public class OperationException extends IOException {
    public OperationException(String message) {
        super("Operation Error... " + message);
    }
}
