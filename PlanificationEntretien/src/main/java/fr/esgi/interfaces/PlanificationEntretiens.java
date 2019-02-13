package fr.esgi.interfaces;

import fr.esgi.models.Candidat;
import fr.esgi.models.CreneauHoraire;

public interface PlanificationEntretiens {
   public boolean planifierEntretien(Candidat candidat, CreneauHoraire creneauSouhaite);
}
