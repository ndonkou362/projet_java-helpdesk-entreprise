package model;

// Represente le probleme signale par l'appelant
public class Probleme {

    // les enums evitent les fautes de frappe sur le type et la priorite
    public enum TypeProbleme {
        LOGICIEL("Logiciel"),
        CONFIGURATION("Configuration"),
        MATERIEL("Materiel");

        private String libelle;

        TypeProbleme(String libelle) { this.libelle = libelle; }

        public String getLibelle() { return libelle; }
    }

    public enum Priorite {
        BASSE("Basse"),
        MOYENNE("Moyenne"),
        HAUTE("Haute");

        private String libelle;

        Priorite(String libelle) { this.libelle = libelle; }

        public String getLibelle() { return libelle; }
    }

    private String description;
    private TypeProbleme type;
    private Priorite priorite;

    public Probleme(String description, TypeProbleme type, Priorite priorite) {
        if (description == null || description.isEmpty()) throw new IllegalArgumentException("La description ne peut pas etre vide");
        if (type == null)     throw new IllegalArgumentException("Le type ne peut pas etre null");
        if (priorite == null) throw new IllegalArgumentException("La priorite ne peut pas etre nulle");
        this.description = description;
        this.type = type;
        this.priorite = priorite;
    }

    public String getDescription()  { return description; }
    public TypeProbleme getType()   { return type; }
    public Priorite getPriorite()   { return priorite; }
}
