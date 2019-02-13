package fr.esgi.exceptions;

public class HorairesOutOfWorkingTime extends RuntimeException {
    public HorairesOutOfWorkingTime(){
        super("Les horaires de l'entretien sont en dehors du temps de travail");
    }
}
