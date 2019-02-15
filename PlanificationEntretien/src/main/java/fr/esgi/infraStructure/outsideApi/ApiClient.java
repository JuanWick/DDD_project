package fr.esgi.infraStructure.outsideApi;

import fr.esgi.models.ConsultantRecruteur;
import fr.esgi.models.MoisCreneau;

import java.util.List;

public interface ApiClient {
    List<ConsultantRecruteur> chercherConsultantRecruteurDisponibleParMois(MoisCreneau moisCreneau);
}
