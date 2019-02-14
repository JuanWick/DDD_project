
package fr.esgi.exceptions;

public class IdNotValidException extends RuntimeException{
    public IdNotValidException(){
        super("L'Id n'est pas valide");
    }
}

