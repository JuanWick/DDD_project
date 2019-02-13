package fr.esgi.exceptions;

public class UnmatchingCompetenceConsultantRecruteur extends RuntimeException {
    public UnmatchingCompetenceConsultantRecruteur() {
        super("Aucun consultant recruteur ne posséde la compétence requise.");
    }
}
