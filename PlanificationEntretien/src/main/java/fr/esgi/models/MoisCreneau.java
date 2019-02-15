package fr.esgi.models;

import fr.esgi.exceptions.NotImplementedException;
import fr.esgi.exceptions.UnValidatedConstructorValueException;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MoisCreneau implements ValueObject{
    private int annee;
    private int mois;

    @Override
    public boolean equals(Object obj) {
        return hashCode(obj);
    }

    @Override
    public boolean hashCode(Object o) {
        MoisCreneau moisCreneau;
        try {
            moisCreneau = (MoisCreneau) o;
        } catch (Exception e) {
            throw new UnValidatedConstructorValueException();
        }
        return moisCreneau.annee == annee && moisCreneau.mois == mois;
    }

    @Override
    public boolean order(Object o) {
        throw new NotImplementedException();
    }
}
