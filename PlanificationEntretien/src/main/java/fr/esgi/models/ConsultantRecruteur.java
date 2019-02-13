package fr.esgi.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ConsultantRecruteur {

    private int Id;
    private String Nom;
    private List<Compétence> Compétences;
}
