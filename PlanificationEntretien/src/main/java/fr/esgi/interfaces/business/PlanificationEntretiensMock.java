package fr.esgi.interfaces.business;

import fr.esgi.models.Candidat;
import fr.esgi.models.CreneauHoraire;
import fr.esgi.interfaces.PlanificationEntretiens;

public class PlanificationEntretiensMock implements PlanificationEntretiens {

    @Override
    public boolean planifierEntretien(Candidat candidat, CreneauHoraire creneauSouhaite) {
        return true;
    }
}
