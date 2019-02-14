package fr.esgi.exceptions;

public class DateIsNotValidException extends RuntimeException {
    public DateIsNotValidException(){
        super("La date est null");
    }
}
