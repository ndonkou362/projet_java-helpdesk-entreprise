package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppelTest {

    private Appelant appelant;
    private Probleme probleme;
    private Materiel materiel;
    private Technicien technicien;
    private Solution solution;

    @BeforeEach
    void setUp() {
        appelant   = new Appelant("Ndonkou", "Edith", "e@mail.com", "Comptabilite");
        probleme   = new Probleme("Ecran noir", Probleme.TypeProbleme.MATERIEL, Probleme.Priorite.HAUTE);
        materiel   = new Materiel("PC-001", "Laptop", "Dell XPS");
        technicien = new Technicien("Nodo", "Donald", "d@support.com", Technicien.Niveau.NIVEAU_1);
        solution   = new Solution("Redemarrage", "Eteindre et rallumer.");
    }

    @Test
    void assignerTechnicien() {
        Appel a = new Appel(appelant, probleme, materiel);
        a.assignerTechnicien(technicien);
        assertEquals(Appel.Statut.EN_COURS, a.getStatut());
    }

    @Test
    void assignerTechnicien_null_leve_exception() {
        Appel a = new Appel(appelant, probleme, materiel);
        assertThrows(IllegalArgumentException.class, () -> a.assignerTechnicien(null));
    }

    @Test
    void assignerTechnicien_appel_resolu_leve_exception() {
        Appel a = new Appel(appelant, probleme, materiel);
        a.assignerTechnicien(technicien);
        a.resoudre(solution);
        assertThrows(IllegalStateException.class, () -> a.assignerTechnicien(technicien));
    }

    @Test
    void escalader() {
        Technicien expert = new Technicien("Yakou", "Pascal", "p@expert.com", Technicien.Niveau.NIVEAU_3);
        Appel a = new Appel(appelant, probleme, materiel);
        a.assignerTechnicien(technicien);
        a.escalader(expert);
        assertEquals(Appel.Statut.EN_COURS, a.getStatut());
    }

    @Test
    void escalader_appel_resolu_leve_exception() {
        Technicien expert = new Technicien("Yakou", "Pascal", "p@expert.com", Technicien.Niveau.NIVEAU_3);
        Appel a = new Appel(appelant, probleme, materiel);
        a.assignerTechnicien(technicien);
        a.resoudre(solution);
        assertThrows(IllegalStateException.class, () -> a.escalader(expert));
    }

    @Test
    void resoudre() {
        Appel a = new Appel(appelant, probleme, materiel);
        a.assignerTechnicien(technicien);
        a.resoudre(solution);
        assertEquals(Appel.Statut.RESOLU, a.getStatut());
    }

    @Test
    void resoudre_sans_technicien_leve_exception() {
        Appel a = new Appel(appelant, probleme, materiel);
        assertThrows(IllegalStateException.class, () -> a.resoudre(solution));
    }

    @Test
    void resoudre_solution_null_leve_exception() {
        Appel a = new Appel(appelant, probleme, materiel);
        a.assignerTechnicien(technicien);
        assertThrows(IllegalArgumentException.class, () -> a.resoudre(null));
    }

    @Test
    void ajouterNote() {
        Appel a = new Appel(appelant, probleme, materiel);
        assertDoesNotThrow(() -> a.ajouterNote("Rappel prevu demain"));
    }

    @Test
    void ajouterNote_vide_leve_exception() {
        Appel a = new Appel(appelant, probleme, materiel);
        assertThrows(IllegalArgumentException.class, () -> a.ajouterNote(""));
        assertThrows(IllegalArgumentException.class, () -> a.ajouterNote(null));
    }

    @Test
    void afficherHistorique() {
        Appel a = new Appel(appelant, probleme, materiel);
        assertDoesNotThrow(a::afficherHistorique);
    }

    @Test
    void getStatut() {
        Appel a = new Appel(appelant, probleme, materiel);
        assertEquals(Appel.Statut.OUVERT, a.getStatut());
    }

    @Test
    void afficher() {
        Appel a = new Appel(appelant, probleme, materiel);
        assertDoesNotThrow(a::afficher);
    }
}
