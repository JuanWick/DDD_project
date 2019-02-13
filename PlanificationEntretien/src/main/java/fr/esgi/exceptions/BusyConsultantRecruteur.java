package fr.esgi.exceptions;

public class BusyConsultantRecruteur extends RuntimeException {
    public BusyConsultantRecruteur() {
        super("Aucun consultant recruteur n'est disponible.");
    }
}
