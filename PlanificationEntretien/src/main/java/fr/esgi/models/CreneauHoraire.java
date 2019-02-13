package fr.esgi.models;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CreneauHoraire {
    Date date;
    public long heureDebut;
}
