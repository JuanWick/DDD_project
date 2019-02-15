package fr.esgi.models;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Entretien {

    private Candidat candidat;
    private ConsultantRecruteur consultantRecruteur;
    private CreneauHoraire creneauHoraire;
    private String statut;
    private int duree;

    public void confirmer(){

    }

    public void annuler(){

    }
}
