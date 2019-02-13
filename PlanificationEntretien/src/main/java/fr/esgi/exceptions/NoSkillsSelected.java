package fr.esgi.exceptions;

public class NoSkillsSelected extends  RuntimeException {
    public NoSkillsSelected(){
        super("Aucune compétence sélectionnée");
    }
}
