package de.ait.todo.exceptions;

public class IncorrectDeleteException extends RuntimeException{

    public IncorrectDeleteException(String message) {
        super(message);
    }
}
