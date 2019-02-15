package fr.esgi.useCases;

import fr.esgi.models.Candidat;
import fr.esgi.models.CreneauHoraire;

public interface PlanificationEntretiens {
   void planifierEntretien(Candidat candidat, CreneauHoraire creneauSouhaite);

   void annulerEntretien(Candidat candidat);

   void rePlanifierEntretien(Candidat candidat, CreneauHoraire creneauSouhaite);

   void notifierRh();
}
