package fr.esgi.models;

import lombok.Data;

import java.util.List;

@Data

public class ConsultantRecruteur {

    private int Id;
    private String Nom;
    private List<Compétence> Compétences;
}
