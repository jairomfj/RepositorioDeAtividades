package br.com.repositoriodeatividades.usecases.exercise.exceptions;

/**
 * Created by jairomendes on 4/2/16.
 */
public class InvalidFormatException extends Exception {

    private String message;

    public InvalidFormatException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
