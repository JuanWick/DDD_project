package fr.esgi.exceptions;

public class HorairesOutOfWorkingTimeException extends RuntimeException {
    public HorairesOutOfWorkingTimeException(){
        super("Les horaires de l'entretien sont en dehors du temps de travail");
    }
}
