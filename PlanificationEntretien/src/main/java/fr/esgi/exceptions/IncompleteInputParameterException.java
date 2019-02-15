package fr.esgi.exceptions;

public class IncompleteInputParameterException extends RuntimeException {
    public IncompleteInputParameterException() {
        super("Valeurs incompletes.");
    }
}
