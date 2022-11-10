package com.example.block7crudvalidation.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.util.Date;

public class CustomError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private int code;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String stackTrace;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private Object data;

    public CustomError() {
        timestamp = new Date();
    }

    public CustomError(HttpStatus httpStatus, String message) {
        this();
        this.timestamp = new Date();
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
    }

    public CustomError(HttpStatus httpStatus, String message, String stackTrace) {
        this(httpStatus, message);
        this.stackTrace = stackTrace;
    }

    public CustomError(HttpStatus httpStatus, String message, String stackTrace, Object data) {
        this(httpStatus, message, stackTrace);
        this.data = data;
    }

    //  -- Used

    public CustomError( HttpStatus httpStatus, String message, Object data) {
        this.code = httpStatus.value();
        this.timestamp = new Date();
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "timestamp:" + timestamp +
                ", code:" + code +
                ", status:'" + status + '\'' +
                ", message:'" + message + '\'' +
                ", stackTrace:'" + stackTrace + '\'' +
                ", data:" + data +
                '}';
    }
}
