package com.example.block7crudvalidation.exceptions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
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

    @JsonCreator
    public CustomError(HttpStatus httpStatus, String message) {
        this();
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
    }

    @JsonCreator
    public CustomError(HttpStatus httpStatus, String message, String stackTrace) {
        this(httpStatus, message);
        this.stackTrace = stackTrace;
    }

    public CustomError(HttpStatus httpStatus, String message, String stackTrace, Object data) {
        this(httpStatus, message, stackTrace);
        this.data = data;
    }

    //  -- Used

    @JsonCreator
    public CustomError( HttpStatus httpStatus, String message, Object data) {
        this();
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
        this.data = data;
    }

    // One builder to display messages.
    public static CustomError build(int statusCode, String message) {
        switch (statusCode){
            case(200):
                return new CustomError(HttpStatus.OK, "The request has been successful. : ( " + message + " )");
            case(201):
                return new CustomError(HttpStatus.CREATED, "The request was successful and a new resource was created as a result. : ( " + message + " )");
            case(204):
                return new CustomError(HttpStatus.NO_CONTENT, "The request has been completed successfully but your response has no content. : ( " + message + " )");
            case(205):
                return new CustomError(HttpStatus.RESET_CONTENT, "The request has been completed successfully. Please, refresh your browser: ( " + message + " )");
            case(400):
                return new CustomError(HttpStatus.BAD_REQUEST, "The server could not interpret the request due to invalid syntax. : ( " + message + " )");
            case(404):
                return new CustomError(HttpStatus.NOT_FOUND, "The request was well formed but could not be followed due to semantic errors. : ( " + message + " )");
            case(422):
                return new CustomError(HttpStatus.UNPROCESSABLE_ENTITY,"The request was well formed but could not be followed due to semantic errors. : ( " + message + " )");
            default:
                return new CustomError(HttpStatus.INTERNAL_SERVER_ERROR, "The server has encountered a situation that it does not know how to handle.");
        }
    }
    public static CustomError buildWithObject(int statusCode, String message, Object dataObject) {
        switch (statusCode){
            case(200):
                return new CustomError(HttpStatus.OK, "The request has been successful. : ( " + message + " )", dataObject);
            case(201):
                return new CustomError(HttpStatus.CREATED, "The request was successful and a new resource was created as a result. : ( " + message + " )", dataObject);
            case(204):
                return new CustomError(HttpStatus.NO_CONTENT, "The request has been completed successfully but your response has no content. : ( " + message + " )", dataObject);
            case(400):
                return new CustomError(HttpStatus.BAD_REQUEST, "The server could not interpret the request due to invalid syntax. : ( " + message + " )", dataObject);
            case(404):
                return new CustomError(HttpStatus.NOT_FOUND, "The request was well formed but could not be followed due to semantic errors. : ( " + message + " )", dataObject);
            case(422):
                return new CustomError(HttpStatus.UNPROCESSABLE_ENTITY,"The request was well formed but could not be followed due to semantic errors. : ( " + message + " )", dataObject);
            default:
                return new CustomError(HttpStatus.INTERNAL_SERVER_ERROR, "The server has encountered a situation that it does not know how to handle.");
        }
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
