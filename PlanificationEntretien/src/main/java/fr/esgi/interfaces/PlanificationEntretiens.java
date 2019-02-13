package fr.esgi.interfaces;

import fr.esgi.Candidat;
import fr.esgi.CreneauHoraire;
import fr.esgi.Entretien;

public interface PlanificationEntretiens {
   public boolean planifierEntretien(Candidat candidat, CreneauHoraire creneauSouhaite);
}
