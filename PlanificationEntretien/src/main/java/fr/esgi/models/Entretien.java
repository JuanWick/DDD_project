package fr.esgi.models;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Entretien {

    public CreneauHoraire creneauHoraire;
    public int duree;
}
