package model;

import java.util.ArrayList;
import java.util.Date;

// Garde la trace de tout ce qui se passe sur un appel
public class Suivi implements Affichable {

    private ArrayList<String> historique;

    public Suivi() {
        this.historique = new ArrayList<>();
    }

    // chaque evenement est horodate automatiquement a l'ajout
    public void ajouterEvenement(String message) {
        historique.add("[" + new Date() + "] " + message);
    }

    public void afficherHistorique() {
        for (String ligne : historique) {
            System.out.println("  " + ligne);
        }
    }

    @Override
    public void afficher() {
        afficherHistorique();
    }
}
