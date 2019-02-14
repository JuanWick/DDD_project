package fr.esgi.models;

import fr.esgi.exceptions.UnValidatedConstructorValueException;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Candidat implements ValueObject{
    private int Id;
    private String Nom;
    private List<Compétence> Competences;

    @Override
    public boolean hashCode(Object o) {
        Candidat candidat;
        try {
            candidat = (Candidat) o;
        } catch (Exception e) {
            throw new UnValidatedConstructorValueException();
        }
        return candidat.getNom().equals(Nom);
    }

    @Override
    public boolean order(Object o) {
        Candidat candidat;
        try {
            candidat = (Candidat) o;
        } catch (Exception e) {
            throw new UnValidatedConstructorValueException();
        }

        return false;
    }
}
