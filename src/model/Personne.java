package model;

// Classe de base pour Appelant et Technicien
public abstract class Personne {

    private String nom;
    private String prenom;
    private String email;

    public Personne(String nom, String prenom, String email) {
        if (nom == null || nom.isEmpty())       throw new IllegalArgumentException("Le nom ne peut pas etre vide");
        if (prenom == null || prenom.isEmpty()) throw new IllegalArgumentException("Le prenom ne peut pas etre vide");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("L'email est invalide");
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public String getEmail()      { return email; }
    public String getNomComplet() { return prenom + " " + nom; }
}
