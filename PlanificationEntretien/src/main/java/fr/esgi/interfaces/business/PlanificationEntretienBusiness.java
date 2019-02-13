package fr.esgi.interfaces.business;

import fr.esgi.interfaces.PlanificationEntretiens;
import fr.esgi.models.Candidat;
import fr.esgi.models.CreneauHoraire;

public class PlanificationEntretienBusiness implements PlanificationEntretiens {
    @Override
    public boolean planifierEntretien(Candidat candidat, CreneauHoraire creneauSouhaite) {
        //TODO métier
        return false;
    }
}
