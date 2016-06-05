package br.com.repositoriodeatividades.usecases.exercise.imports.models.exceptions;

public class InvalidFileFormatException extends IllegalArgumentException {

    public InvalidFileFormatException(String message) {
        super(message);
    }

}
