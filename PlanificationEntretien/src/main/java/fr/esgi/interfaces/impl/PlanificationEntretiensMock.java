package fr.esgi.interfaces.impl;

import fr.esgi.Candidat;
import fr.esgi.CreneauHoraire;
import fr.esgi.Entretien;
import fr.esgi.interfaces.PlanificationEntretiens;

import java.util.Date;

public class PlanificationEntretiensMock implements PlanificationEntretiens {

    @Override
    public boolean planifierEntretien(Candidat candidat, CreneauHoraire creneauSouhaite) {
        return true;
    }
}
