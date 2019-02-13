package fr.esgi.models;

import java.util.List;

@Data

public class Candidat {
    private int Id;
    private String Nom;
    private List<Compétence> Competences;
}
