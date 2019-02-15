package fr.esgi.useCases.impl;

import fr.esgi.exceptions.IncompleteInputParameterException;
import fr.esgi.infraStructure.outsideApi.ApiClient;
import fr.esgi.models.Candidat;
import fr.esgi.models.ConsultantRecruteur;
import fr.esgi.models.CreneauHoraire;
import fr.esgi.models.MoisCreneau;
import fr.esgi.useCases.PlanificationEntretiens;

import java.util.ArrayList;
import java.util.List;

public class PlanificationEntretienBusiness implements PlanificationEntretiens {

    private ApiClient apiClient;

    public PlanificationEntretienBusiness(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public void planifierEntretien(Candidat candidat, CreneauHoraire creneauSouhaite) {
        if(null == candidat || null == creneauSouhaite) {
            throw new IncompleteInputParameterException();
        }
        try {
            List<ConsultantRecruteur> consultantRecruteurs = apiClient.chercherConsultantRecruteurDisponibleParMois(creneauHoraireToMoisCreneauAdapter(creneauSouhaite));
        } catch(Exception e){
            throw
        }

        List<ConsultantRecruteur> consultantRecruteursCompetent = new ArrayList<>();

        for (ConsultantRecruteur consultantRecruteur : consultantRecruteurs) {
            if(peutTester(consultantRecruteur, candidat)){
                consultantRecruteursCompetent.add(consultantRecruteur);
            }
        }
        //TODO find first

        //TODO creation entretien

        //TODO percistance
    }

    @Override
    public void annulerEntretien(Candidat candidat) {

    }

    @Override
    public void rePlanifierEntretien(Candidat candidat, CreneauHoraire creneauSouhaite) {

    }

    @Override
    public void notifierRh() {

    }

    private MoisCreneau creneauHoraireToMoisCreneauAdapter(CreneauHoraire creneauHoraire){//TODO
        return null;
    }

    private boolean peutTester(ConsultantRecruteur consultantRecruteur, Candidat candidat){
        return false;
    }
}
