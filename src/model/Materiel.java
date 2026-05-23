package model;

// Equipement informatique de l'entreprise (PC, imprimante, serveur...)
public class Materiel implements Affichable {

    private String idMateriel;
    private String type;
    private String modele;

    public Materiel(String idMateriel, String type, String modele) {
        if (idMateriel == null || idMateriel.isEmpty()) throw new IllegalArgumentException("L'id du materiel ne peut pas etre vide");
        if (type == null || type.isEmpty())             throw new IllegalArgumentException("Le type du materiel ne peut pas etre vide");
        if (modele == null || modele.isEmpty())         throw new IllegalArgumentException("Le modele du materiel ne peut pas etre vide");
        this.idMateriel = idMateriel;
        this.type = type;
        this.modele = modele;
    }

    // retourne une description courte, ex : "Laptop Dell XPS (ID: PC-001)"
    public String getInfos() {
        return type + " " + modele + " (ID: " + idMateriel + ")";
    }

    @Override
    public void afficher() {
        System.out.println("  [MATERIEL] " + getInfos());
    }
}
