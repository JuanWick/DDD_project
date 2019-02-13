package fr.esgi.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Candidat {
    private int Id;
    private String Nom;
    private List<Compétence> Competences;
}
