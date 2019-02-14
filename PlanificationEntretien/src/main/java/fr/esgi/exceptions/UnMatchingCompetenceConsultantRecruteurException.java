package fr.esgi.exceptions;

public class UnMatchingCompetenceConsultantRecruteurException extends RuntimeException {
    public UnMatchingCompetenceConsultantRecruteurException() {
        super("Aucun consultant recruteur ne posséde la compétence requise.");
    }
}
