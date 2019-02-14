package fr.esgi.infraStructure;

import fr.esgi.models.ConsultantRecruteur;
import fr.esgi.models.MoisCreneau;

import java.util.List;

public interface apiClient {
    List<ConsultantRecruteur> chercherConsultantRecruteurDisponibleParMois(MoisCreneau moisCreneau);
}
