
package fr.esgi.exceptions;

public class IdNotValid extends RuntimeException{
    public IdNotValid(){
        super("L'Id n'est pas valide");
    }
}

