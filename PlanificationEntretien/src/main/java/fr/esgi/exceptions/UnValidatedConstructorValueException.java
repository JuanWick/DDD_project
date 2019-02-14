package fr.esgi.exceptions;

public class UnValidatedConstructorValueException extends RuntimeException {
    public UnValidatedConstructorValueException() {
        super("Valeur incorrecte.");
    }
}
