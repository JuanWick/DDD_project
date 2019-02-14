package fr.esgi.exceptions;

public class BusyConsultantRecruteurException extends RuntimeException {
    public BusyConsultantRecruteurException() {
        super("Aucun consultant recruteur n'est disponible.");
    }
}
