package fr.esgi.exceptions;

public class UnComparableException extends RuntimeException {
    public UnComparableException() {
        super("Les objets ne sont pas comparables.");
    }
}
