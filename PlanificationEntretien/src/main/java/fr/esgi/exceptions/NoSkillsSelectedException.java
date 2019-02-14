package fr.esgi.exceptions;

public class NoSkillsSelectedException extends  RuntimeException {
    public NoSkillsSelectedException(){
        super("Aucune compétence sélectionnée");
    }
}
