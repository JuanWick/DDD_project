package fr.esgi.models;

import fr.esgi.exceptions.UnValidatedConstructorValueException;
import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.List;

@Data
@Builder
public class ConsultantRecruteur implements ValueObject{

    private String Nom;
    private List<Compétence> Compétences;
    private int[] hoursFree;

    @Override
    public boolean hashCode(Object o) {
        ConsultantRecruteur ConsultantRecruteur;
        try {
            ConsultantRecruteur = (ConsultantRecruteur) o;
        } catch (Exception e) {
            throw new UnValidatedConstructorValueException();
        }
        return ConsultantRecruteur.getNom().equals(Nom);
    }

    @Override
    public boolean order(Object o) {
        return false;
    }

}
