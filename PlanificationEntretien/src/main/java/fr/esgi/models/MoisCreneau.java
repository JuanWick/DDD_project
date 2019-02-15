package fr.esgi.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MoisCreneau {
    private int annee;
    private int mois;
}
