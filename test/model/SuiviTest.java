package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SuiviTest {

    @Test
    void ajouterEvenement() {
        Suivi s = new Suivi();
        assertDoesNotThrow(() -> s.ajouterEvenement("Technicien assigne"));
    }

    @Test
    void ajouterEvenement_plusieurs_fois() {
        Suivi s = new Suivi();
        assertDoesNotThrow(() -> {
            s.ajouterEvenement("Appel cree");
            s.ajouterEvenement("Technicien assigne");
            s.ajouterEvenement("Appel resolu");
        });
    }

    @Test
    void afficherHistorique() {
        Suivi s = new Suivi();
        s.ajouterEvenement("Appel cree");
        assertDoesNotThrow(s::afficherHistorique);
    }

    @Test
    void afficher() {
        Suivi s = new Suivi();
        s.ajouterEvenement("Appel cree");
        assertDoesNotThrow(s::afficher);
    }
}
