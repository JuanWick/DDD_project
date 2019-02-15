package fr.esgi.infraStructure.outsideApi;

import fr.esgi.models.Candidat;
import fr.esgi.models.ConsultantRecruteur;
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
        for (Entretien entretien: entretiens){
            if(entretien.getCandidat().hashCode(candidat)){

                return entretien;
            }
        }
        return null;
    }

    @Override
    public List<Entretien> getEntretiens(ConsultantRecruteur consultantRecruteur) {
        List<Entretien> Entretiens  = new ArrayList<>();
        for(Entretien Entretien : entretiens){
            if(Entretien.getConsultantRecruteur().hashCode(consultantRecruteur)){
                Entretiens.add(Entretien);
            }
        }
        return Entretiens;
    }
}
