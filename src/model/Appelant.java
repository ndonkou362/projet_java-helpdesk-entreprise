package model;

// Employe qui contacte le support
public class Appelant extends Personne implements Affichable {

    private String departement;

    public Appelant(String nom, String prenom, String email, String departement) {
        super(nom, prenom, email);
        if (departement == null || departement.isEmpty()) throw new IllegalArgumentException("Le departement ne peut pas etre vide");
        this.departement = departement;
    }

    public String getDepartement() { return departement; }

    @Override
    public void afficher() {
        System.out.println("  [APPELANT] " + getNomComplet() + " | " + departement + " | " + getEmail());
    }
}
