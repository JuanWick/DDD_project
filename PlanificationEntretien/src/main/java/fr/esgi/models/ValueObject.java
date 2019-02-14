package fr.esgi.models;

public interface ValueObject {
    boolean hashCode(Object o);

    boolean order(Object o);
}
