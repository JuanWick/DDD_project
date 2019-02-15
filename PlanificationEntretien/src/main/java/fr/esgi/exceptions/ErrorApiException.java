package fr.esgi.exceptions;

public class ErrorApiException extends RuntimeException {
    public ErrorApiException(){
        super("Une erreur s'est produite à la récupération de l'API");
    }
}
