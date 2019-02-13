package fr.esgi;

import lombok.Data;

import java.util.Date;

@Data
public class Entretien {

    public Date date;
    public long heureDebut;
    public int duree;
}
