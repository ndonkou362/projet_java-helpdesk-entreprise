package model;

import java.util.Date;

// Ticket de support, passe par 3 etats : Ouvert, En cours, Resolu
public class Appel implements Affichable {

    // les 3 etats possibles d'un appel
    public enum Statut {
        OUVERT("Ouvert"),
        EN_COURS("En cours"),
        RESOLU("Resolu");

        private String libelle;

        Statut(String libelle) { this.libelle = libelle; }

        public String getLibelle() { return libelle; }
    }

    // compteur partage entre tous les appels, sert a generer un numero unique
    private static int compteurTickets = 0;

    private String numeroTicket;
    private Date dateAppel;
    private Statut statut;
    private Appelant auteur;
    private Probleme souci;
    private Materiel machineConcernee;
    private Technicien technicienAssigne; // peut rester null si personne n'est assigne
    private Solution solutionApportee;   // null tant que l'appel n'est pas resolu
    private Suivi suivi;

    /**
     * Cree un nouvel appel. Le numero de ticket est genere automatiquement au format TKT-0001.
     *
     * @param auteur   la personne qui signale le probleme
     * @param souci   le probleme signale
     * @param machineConcernee le materiel concerne
     * @throws IllegalArgumentException si un des parametres est null
     */
    public Appel(Appelant auteur, Probleme souci, Materiel machineConcernee) {
        if (auteur == null || souci == null || machineConcernee == null) {
            throw new IllegalArgumentException("auteur, souci et machineConcernee sont obligatoires");
        }
        compteurTickets++;
        this.numeroTicket = "TKT-" + String.format("%04d", compteurTickets);
        this.dateAppel = new Date();
        this.statut = Statut.OUVERT;
        this.auteur = auteur;
        this.souci = souci;
        this.machineConcernee = machineConcernee;
        this.technicienAssigne = null;
        this.solutionApportee = null;
        this.suivi = new Suivi();
        suivi.ajouterEvenement("Appel cree par " + auteur.getNomComplet()
                + " (" + auteur.getDepartement() + ")"
                + " | Probleme : " + souci.getDescription()
                + " | Priorite : " + souci.getPriorite().getLibelle());
    }

    /**
     * Assigne un technicien a l'appel, le statut passe a EN_COURS.
     *
     * @param tech le technicien a assigner
     * @throws IllegalArgumentException si tech est null
     * @throws IllegalStateException    si l'appel est deja resolu
     */
    public void assignerTechnicien(Technicien tech) {
        if (tech == null) throw new IllegalArgumentException("Le technicien ne peut pas etre null");
        if (statut == Statut.RESOLU) throw new IllegalStateException("Impossible d'assigner : l'appel est deja resolu");
        this.technicienAssigne = tech;
        this.statut = Statut.EN_COURS;
        suivi.ajouterEvenement("Technicien assigne : " + tech.getNomComplet()
                + " (" + tech.getNiveauDescription() + ")");
    }

    /**
     * Transfere l'appel a un autre technicien. L'ancien technicien est conserve dans le suivi.
     *
     * @param nouveauTech le technicien qui prend la suite
     * @throws IllegalArgumentException si nouveauTech est null
     * @throws IllegalStateException    si l'appel est deja resolu
     */
    public void escalader(Technicien nouveauTech) {
        if (nouveauTech == null) throw new IllegalArgumentException("Le nouveau technicien ne peut pas etre null");
        if (statut == Statut.RESOLU) throw new IllegalStateException("Impossible d'escalader : l'appel est deja resolu");
        String ancienTech = (technicienAssigne != null) ? technicienAssigne.getNomComplet() : "inconnu";
        suivi.ajouterEvenement("Escalade : " + ancienTech
                + " vers " + nouveauTech.getNomComplet()
                + " (" + nouveauTech.getNiveauDescription() + ")");
        this.technicienAssigne = nouveauTech;
    }

    /**
     * Cloture l'appel avec la solution appliquee, le statut passe a RESOLU.
     * Un technicien doit etre assigne avant d'appeler cette methode.
     *
     * @param solution la solution qui a permis de resoudre le probleme
     * @throws IllegalArgumentException si solution est null
     * @throws IllegalStateException    si aucun technicien n'est assigne
     */
    public void resoudre(Solution solution) {
        if (solution == null) throw new IllegalArgumentException("La solution ne peut pas etre null");
        if (technicienAssigne == null) throw new IllegalStateException("Impossible de resoudre : aucun technicien assigne");
        this.solutionApportee = solution;
        this.statut = Statut.RESOLU;
        suivi.ajouterEvenement("Appel resolu. Solution appliquee : " + solution.getTitre());
    }

    /**
     * Ajoute une note libre dans le suivi de l'appel.
     *
     * @param note le commentaire a ajouter
     * @throws IllegalArgumentException si la note est vide ou null
     */
    public void ajouterNote(String note) {
        if (note == null || note.isEmpty()) throw new IllegalArgumentException("La note ne peut pas etre vide");
        suivi.ajouterEvenement("Note : " + note);
    }

    public void afficherHistorique() {
        System.out.println("  --- Historique du ticket " + numeroTicket + " ---");
        suivi.afficherHistorique();
    }

    public Statut getStatut() { return statut; }

    @Override
    public void afficher() {
        System.out.println("----- " + numeroTicket + " -----");
        System.out.println("  Date     : " + dateAppel);
        System.out.println("  Statut   : " + statut.getLibelle());
        System.out.println("  Appelant : " + auteur.getNomComplet() + " (" + auteur.getDepartement() + ")");
        System.out.println("  Probleme : " + souci.getDescription());
        System.out.println("  Type     : " + souci.getType().getLibelle()
                + " | Priorite : " + souci.getPriorite().getLibelle());
        System.out.println("  Materiel : " + machineConcernee.getInfos());

        if (technicienAssigne != null) {
            System.out.println("  Tech     : " + technicienAssigne.getNomComplet()
                    + " - " + technicienAssigne.getNiveauDescription());
        } else {
            System.out.println("  Tech     : (pas encore assigne)");
        }

        if (solutionApportee != null) {
            System.out.println("  Solution : " + solutionApportee.getTitre());
        }
    }
}
