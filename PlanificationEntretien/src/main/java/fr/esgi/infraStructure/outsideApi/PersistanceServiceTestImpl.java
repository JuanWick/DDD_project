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
        System.out.printf("TEST : "+entretiens.size());
        for (Entretien entretien: entretiens){
            if(entretien.getCandidat().hashCode(candidat)){
                System.out.printf("TEST : A");

                return entretien;
            }
            System.out.printf("TEST : B");

        }
        return null;
    }
}
