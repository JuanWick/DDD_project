package fr.esgi.models;

public interface ValueObject {
    boolean equals(Object o);

    boolean order(Object o);
}
