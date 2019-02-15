package fr.esgi.useCases.impl;

import fr.esgi.exceptions.IncompleteInputParameterException;
import fr.esgi.infraStructure.outsideApi.ApiClient;
import fr.esgi.models.Candidat;
import fr.esgi.models.ConsultantRecruteur;
import fr.esgi.models.CreneauHoraire;
import fr.esgi.models.MoisCreneau;
import fr.esgi.useCases.PlanificationEntretiens;

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

        List<ConsultantRecruteur> consultantRecruteurs = apiClient.chercherConsultantRecruteurDisponibleParMois(creneauHoraireToMoisCreneauAdapter(creneauSouhaite));

        //TODO filter compétence consultant testeur

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

    private ConsultantRecruteur peutTester(Candidat candidat){
        return null;
    }
}
