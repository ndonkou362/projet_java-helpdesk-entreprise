package model;

// Fiche de la base de connaissances pour resoudre un probleme connu
public class Solution implements Affichable {

    private String titre;
    private String descriptionSolution;

    public Solution(String titre, String descriptionSolution) {
        this.titre = titre;
        this.descriptionSolution = descriptionSolution;
    }

    public String getTitre()               { return titre; }
    public String getDescriptionSolution() { return descriptionSolution; }

    @Override
    public void afficher() {
        System.out.println("  [SOLUTION] " + titre);
        System.out.println("  Marche a suivre : " + descriptionSolution);
    }
}
