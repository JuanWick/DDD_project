package fr.esgi.infraStructure.outsideApi;

import fr.esgi.models.Candidat;
import fr.esgi.models.Entretien;

public interface PersistanceService {

    void addEntretien(Entretien entretien);

    Entretien getEntretien(Candidat candidat);

}
