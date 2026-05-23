package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TechnicienTest {

    @Test
    void getNiveauDescription() {
        Technicien t = new Technicien("Nodo", "Donald", "d@support.com", Technicien.Niveau.NIVEAU_1);
        assertEquals("Support initial – encodage et prise en charge", t.getNiveauDescription());
    }

    @Test
    void afficher() {
        Technicien t = new Technicien("Nodo", "Donald", "d@support.com", Technicien.Niveau.NIVEAU_2);
        assertDoesNotThrow(t::afficher);
    }

    @Test
    void niveau_null_leve_exception() {
        assertThrows(IllegalArgumentException.class, () ->
                new Technicien("Nodo", "Donald", "d@support.com", null));
    }

    @Test
    void email_invalide_leve_exception() {
        assertThrows(IllegalArgumentException.class, () ->
                new Technicien("Nodo", "Donald", "emailsansarobase", Technicien.Niveau.NIVEAU_1));
    }
}
