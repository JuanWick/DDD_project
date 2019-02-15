package fr.esgi.infraStructure.outsideApi;

import fr.esgi.models.Candidat;
import fr.esgi.models.Entretien;

import java.util.ArrayList;
import java.util.List;

public class PersistanceServiceTestImpl implements PersistanceService {

    private List<Entretien> entretiens = new ArrayList<>();

    @Override
    public void addEntretien(Entretien entretien) {
        entretiens.add(entretien);
    }

    @Override
    public Entretien getEntretien(Candidat candidat) {
        return null;
    }
}
