package fr.esgi;

@Data

public enum Compétence {
    JAVA("java"),
    DOTNET("dotnet"),
    JS("javascript"),
    AGILITE("agilité");

    private String url;

    Compétence(String url) {
        this.url = url;
    }

    public String url() {
        return url;
    }
}
