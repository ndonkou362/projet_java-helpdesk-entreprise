package model;

// Technicien du support, avec un niveau de competence
public class Technicien extends Personne implements Affichable {

    // l'enum garantit qu'on ne peut pas mettre un niveau invalide
    public enum Niveau {
        NIVEAU_1("Support initial – encodage et prise en charge"),
        NIVEAU_2("Support technique – rappel et solution directe"),
        NIVEAU_3("Expertise – problemes complexes et multi-appels");

        private final String description;

        Niveau(String description) { this.description = description; }

        public String getDescription() { return description; }
    }

    private Niveau niveau;

    public Technicien(String nom, String prenom, String email, Niveau niveau) {
        super(nom, prenom, email);
        if (niveau == null) throw new IllegalArgumentException("Le niveau ne peut pas etre null");
        this.niveau = niveau;
    }

    public String getNiveauDescription() { return niveau.getDescription(); }

    @Override
    public void afficher() {
        System.out.println("  [TECHNICIEN] " + getNomComplet() + " | " + getNiveauDescription() + " | " + getEmail());
    }
}
