package fr.esgi.models;

import lombok.Data;

import java.util.UUID;

@Data
public class Entretien {

    private UUID uuid;
    private Candidat candidat;
    private ConsultantRecruteur consultantRecruteur;
    private CreneauHoraire creneauHoraire;
    private String statut;
    private int duree;

    public Entretien(Candidat candidat, ConsultantRecruteur consultantRecruteur, CreneauHoraire creneauHoraire, int duree){
         this.uuid = UUID.randomUUID();
         this.candidat = candidat;
         this.consultantRecruteur = consultantRecruteur;
         this.creneauHoraire = creneauHoraire;//TODO vérifier date dans le futur
         this.duree = duree;
    }

    public void confirmer(){

    }

    public void annuler(){

    }
}
