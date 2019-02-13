package fr.esgi;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Entretien {

    public Date date;
    public long heureDebut;
    public int duree;
}
