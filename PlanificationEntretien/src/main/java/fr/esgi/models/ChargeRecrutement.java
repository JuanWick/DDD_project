package fr.esgi.models;

import fr.esgi.exceptions.UnValidatedConstructorValueException;
import lombok.Getter;

@Getter
public class ChargeRecrutement implements ValueObject {

    private String nom;

    public ChargeRecrutement(String nom) {
        if(null == nom || nom.isEmpty()){
            throw new UnValidatedConstructorValueException();
        }
        this.nom = nom;
    }

    @Override
    public boolean hashCode(Object o) {
        ChargeRecrutement chargeRecrutement;
        try {
            chargeRecrutement = (ChargeRecrutement) o;
        } catch (Exception e) {
            throw new UnValidatedConstructorValueException();
        }


        return chargeRecrutement.getNom().equals(nom);
    }

    @Override
    public boolean order(Object o) {
        ChargeRecrutement chargeRecrutement;
        try {
            chargeRecrutement = (ChargeRecrutement) o;
        } catch (Exception e) {
            throw new UnValidatedConstructorValueException();
        }

        return false;
    }
}
