package fr.esgi.exceptions;

public class DateIsNotValid extends RuntimeException {
    public DateIsNotValid(){
        super("La date est null");
    }
}
