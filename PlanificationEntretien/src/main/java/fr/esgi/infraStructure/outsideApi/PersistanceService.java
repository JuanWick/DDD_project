package fr.esgi.infraStructure.outsideApi;

import fr.esgi.models.Candidat;
import fr.esgi.models.ConsultantRecruteur;
import fr.esgi.models.Entretien;

import java.util.List;

public interface PersistanceService {

    void addEntretien(Entretien entretien);

    Entretien getEntretien(Candidat candidat);

    List<Entretien> getEntretiens(ConsultantRecruteur consultantRecruteur);

}
