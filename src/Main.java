import model.*;
import java.util.ArrayList;

// Point d'entree : simule une journee de travail au helpdesk
public class Main {

    /**
     * Cherche une solution dans la base par mot-cle.
     * La recherche se fait dans le titre et la description, sans tenir compte des majuscules.
     *
     * @param base    la liste des solutions disponibles
     * @param motCle  le mot a rechercher
     * @return la premiere solution trouvee, ou null si aucune ne correspond
     * @throws IllegalArgumentException si la base ou le mot-cle est invalide
     */
    public static Solution chercherSolution(ArrayList<Solution> base, String motCle) {
        if (base == null || motCle == null || motCle.isEmpty()) {
            throw new IllegalArgumentException("Base ou mot-cle invalide");
        }
        for (Solution s : base) {
            String motCleMin = motCle.toLowerCase();
            if (s.getTitre().toLowerCase().contains(motCleMin)
                    || s.getDescriptionSolution().toLowerCase().contains(motCleMin)) {
                return s;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        // --- base de connaissances ---
        ArrayList<Solution> baseDeConnaissances = new ArrayList<>();
        baseDeConnaissances.add(new Solution("Redemarrage Excel",
                "Fermer le processus dans le gestionnaire des taches et relancer l'application."));
        baseDeConnaissances.add(new Solution("Bourrage papier imprimante",
                "Ouvrir le capot arriere, retirer le bac 2, degager delicatement la feuille coincee."));
        baseDeConnaissances.add(new Solution("Reinitialisation WiFi",
                "Oublier le reseau dans les parametres, redemarrer la carte reseau et se reconnecter."));
        baseDeConnaissances.add(new Solution("Disque dur plein",
                "Vider la corbeille, supprimer les fichiers temporaires dans C:/Temp et relancer."));

        // --- techniciens ---
        Technicien franck  = new Technicien("Nodo",   "Donald",  "d.nodo@support.com",   Technicien.Niveau.NIVEAU_1);
        Technicien marcel  = new Technicien("Talla",  "Marcel",  "m.talla@support.com",  Technicien.Niveau.NIVEAU_2);
        Technicien pascal  = new Technicien("Yakou",  "Pascal",  "p.yakou@expert.com",   Technicien.Niveau.NIVEAU_3);

        // --- appelants ---
        ArrayList<Appelant> listeAppelants = new ArrayList<>();
        Appelant edith    = new Appelant("Ndonkou",   "Edith",    "e.ndonkou@compta.com",   "Comptabilite");
        Appelant ange     = new Appelant("Meme",      "Ange",     "a.meme@labo.com",        "Recherche");
        Appelant laurent  = new Appelant("Christian", "Laurent",  "l.christian@dir.com",    "Direction");
        Appelant nathalie = new Appelant("Faure",  "Nathalie", "n.faure@rh.com",       "Ressources Humaines");
        listeAppelants.add(edith);
        listeAppelants.add(ange);
        listeAppelants.add(laurent);
        listeAppelants.add(nathalie);

        // --- simulation des appels ---
        ArrayList<Appel> historiqueAppels = new ArrayList<>();

        // Appel 1 : Edith, probleme Excel, resolu par Franck
        Appel appel1 = new Appel(edith,
                new Probleme("Impossible d'ouvrir Excel depuis ce matin", Probleme.TypeProbleme.LOGICIEL, Probleme.Priorite.HAUTE),
                new Materiel("PC-001", "Laptop", "Dell XPS"));
        appel1.assignerTechnicien(franck);
        Solution sol1 = chercherSolution(baseDeConnaissances, "Excel");
        if (sol1 != null) appel1.resoudre(sol1);
        historiqueAppels.add(appel1);

        // Appel 2 : Ange, WiFi perdu, resolu par Marcel
        Appel appel2 = new Appel(ange,
                new Probleme("Connexion WiFi perdue apres la mise a jour Windows", Probleme.TypeProbleme.CONFIGURATION, Probleme.Priorite.MOYENNE),
                new Materiel("PC-042", "Laptop", "Lenovo ThinkPad"));
        appel2.assignerTechnicien(marcel);
        appel2.ajouterNote("Ange rappellee a 14h30, probleme confirme cote reseau");
        Solution sol2 = chercherSolution(baseDeConnaissances, "WiFi");
        if (sol2 != null) appel2.resoudre(sol2);
        historiqueAppels.add(appel2);

        // Appel 3 : Laurent, serveur inaccessible, escalade Franck -> Pascal, en cours
        Appel appel3 = new Appel(laurent,
                new Probleme("Serveur de fichiers inaccessible depuis 3h du matin", Probleme.TypeProbleme.MATERIEL, Probleme.Priorite.HAUTE),
                new Materiel("SRV-02", "Serveur", "Dell PowerEdge R740"));
        appel3.assignerTechnicien(franck);
        appel3.ajouterNote("Probleme trop complexe pour le N1, escalade necessaire");
        appel3.escalader(pascal);
        appel3.ajouterNote("Pascal a acces au serveur, diagnostic en cours");
        historiqueAppels.add(appel3);

        // Appel 4 : Nathalie, bourrage imprimante, resolu par Marcel
        Appel appel4 = new Appel(nathalie,
                new Probleme("Bourrage papier, l'imprimante bloque tout", Probleme.TypeProbleme.MATERIEL, Probleme.Priorite.BASSE),
                new Materiel("IMP-05", "Imprimante", "HP LaserJet Pro"));
        appel4.assignerTechnicien(marcel);
        Solution sol4 = chercherSolution(baseDeConnaissances, "papier");
        if (sol4 != null) appel4.resoudre(sol4);
        historiqueAppels.add(appel4);

        // --- rapport final ---
        System.out.println("===========================================");
        System.out.println("   HELPDESK ENTREPRISE : RAPPORT FINAL    ");
        System.out.println("===========================================\n");

        System.out.println(">>> EQUIPE DE SUPPORT :");
        ArrayList<Affichable> equipe = new ArrayList<>();
        equipe.add(franck);
        equipe.add(marcel);
        equipe.add(pascal);
        for (Affichable membre : equipe) {
            membre.afficher();
        }

        System.out.println("\n-------------------------------------------\n");

        System.out.println(">>> APPELANTS ENREGISTRES (" + listeAppelants.size() + ") :");
        for (Affichable a : listeAppelants) {
            a.afficher();
        }

        System.out.println("\n-------------------------------------------\n");

        System.out.println(">>> BASE DE CONNAISSANCES (" + baseDeConnaissances.size() + " solutions) :");
        for (Affichable s : baseDeConnaissances) {
            s.afficher();
        }

        System.out.println("\n-------------------------------------------\n");

        System.out.println(">>> HISTORIQUE DES APPELS (" + historiqueAppels.size() + " tickets) :\n");
        for (Appel a : historiqueAppels) {
            a.afficher();
            a.afficherHistorique();
            System.out.println();
        }

        // stats de la journee
        int resolus = 0, enCours = 0, ouverts = 0;
        for (Appel a : historiqueAppels) {
            switch (a.getStatut()) {
                case RESOLU   : resolus++;  break;
                case EN_COURS : enCours++;  break;
                case OUVERT   : ouverts++;  break;
            }
        }

        System.out.println("===========================================");
        System.out.println("   STATS DE LA JOURNEE                    ");
        System.out.println("===========================================");
        System.out.println("  Tickets resolus  : " + resolus);
        System.out.println("  Tickets en cours : " + enCours);
        System.out.println("  Tickets ouverts  : " + ouverts);
        System.out.println("  Total            : " + historiqueAppels.size());
        System.out.println("\n[Fin du rapport]");
    }
}
