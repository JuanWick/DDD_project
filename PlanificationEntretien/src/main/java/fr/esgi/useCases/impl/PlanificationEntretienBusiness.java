package fr.esgi.useCases.impl;

import fr.esgi.exceptions.ErrorApiException;
import fr.esgi.exceptions.IncompleteInputParameterException;
import fr.esgi.infraStructure.outsideApi.ApiClient;
import fr.esgi.infraStructure.outsideApi.PersistanceService;
import fr.esgi.models.*;
import fr.esgi.useCases.PlanificationEntretiens;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PlanificationEntretienBusiness implements PlanificationEntretiens {

    private ApiClient apiClient;
    private PersistanceService persistanceService;

    private int DUREE_ENTRETIEN = 30;

    public PlanificationEntretienBusiness(ApiClient apiClient, PersistanceService persistanceService) {
        this.apiClient = apiClient;
        this.persistanceService = persistanceService;
    }

    @Override
    public void planifierEntretien(Candidat candidat, CreneauHoraire creneauSouhaite) {
        if(null == candidat || null == creneauSouhaite) {
            throw new IncompleteInputParameterException();
        }
        List<ConsultantRecruteur> consultantRecruteurs;
        try {
            consultantRecruteurs = apiClient.chercherConsultantRecruteurDisponibleParMois(creneauHoraireToMoisCreneauAdapter(creneauSouhaite));
        } catch(Exception e){
            throw new ErrorApiException();
        }

        List<ConsultantRecruteur> consultantRecruteursCompetent = new ArrayList<>();

        for (ConsultantRecruteur consultantRecruteur : consultantRecruteurs) {
            if(peutTester(consultantRecruteur, candidat)){
                consultantRecruteursCompetent.add(consultantRecruteur);
            }
        }

        List<ConsultantRecruteur> consultantRecruteursDisponible = new ArrayList<>();

        for (ConsultantRecruteur consultantRecruteur : consultantRecruteursCompetent){
            if(estDisponible(consultantRecruteur, creneauSouhaite)){
                consultantRecruteursDisponible.add(consultantRecruteur);
            }
        }

        if(consultantRecruteursDisponible.size() > 0 ){
            persistanceService.addEntretien(new Entretien(candidat, consultantRecruteursDisponible.get(0),creneauSouhaite,DUREE_ENTRETIEN));
        }

    }

    @Override
    public void annulerEntretien(Candidat candidat) {
        //Récupérer entretien par candidat

        //changer le statut

    }

    @Override
    public void rePlanifierEntretien(Candidat candidat, CreneauHoraire creneauSouhaite) {

    }

    @Override
    public void notifierRh() {

    }

    private MoisCreneau creneauHoraireToMoisCreneauAdapter(CreneauHoraire creneauHoraire){
        Calendar calendar = creneauHoraire.getDate();
        return MoisCreneau.builder()
                .annee(calendar.get(Calendar.YEAR))
                .mois(calendar.get(Calendar.MONTH)).build();
    }

    private boolean peutTester(ConsultantRecruteur consultantRecruteur, Candidat candidat){
        for (Compétence competenceRecruteur : consultantRecruteur.getCompétences()){
            for (Compétence competenceCandidat : candidat.getCompetences()){
                if (competenceRecruteur == competenceCandidat) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean estDisponible(ConsultantRecruteur consultantRecruteur, CreneauHoraire creneauSouhaité){
    for(Entretien EntretienConsultantExpected : persistanceService.getEntretiens(consultantRecruteur)){
        if(EntretienConsultantExpected.getCreneauHoraire().getHeureDebut() == creneauSouhaité.getHeureDebut()){
            return false;
        }
    }
       for(int DisponibilityConsultant : consultantRecruteur.getHoursFree()){
           if(DisponibilityConsultant == creneauSouhaité.getHeureDebut()){
               return true;
           }
       }
       return false;
    }
}
