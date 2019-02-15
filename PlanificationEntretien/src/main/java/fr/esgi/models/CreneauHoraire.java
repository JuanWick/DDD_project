package fr.esgi.models;

import lombok.Builder;
import lombok.Getter;

import java.util.Calendar;

@Getter
@Builder
public class CreneauHoraire {
    Calendar date;
    private int heureDebut;
}
