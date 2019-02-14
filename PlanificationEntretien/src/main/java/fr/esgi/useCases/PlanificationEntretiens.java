package fr.esgi.useCases;

import fr.esgi.models.Candidat;
import fr.esgi.models.CreneauHoraire;

public interface PlanificationEntretiens {
   public boolean planifierEntretien(Candidat candidat, CreneauHoraire creneauSouhaite);
}
