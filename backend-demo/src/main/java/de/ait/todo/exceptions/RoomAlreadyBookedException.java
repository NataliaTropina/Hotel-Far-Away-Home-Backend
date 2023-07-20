package de.ait.todo.exceptions;

public class RoomAlreadyBookedException extends RuntimeException {

    public RoomAlreadyBookedException (String message){
        super(message);
    }

}
