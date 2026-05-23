package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppelantTest {

    @Test
    void creation_normale() {
        Appelant a = new Appelant("Ndonkou", "Edith", "e@mail.com", "Comptabilite");
        assertEquals("Edith Ndonkou", a.getNomComplet());
        assertEquals("Comptabilite", a.getDepartement());
        assertEquals("e@mail.com", a.getEmail());
    }

    @Test
    void getDepartement() {
        Appelant a = new Appelant("Ndonkou", "Edith", "e@mail.com", "RH");
        assertEquals("RH", a.getDepartement());
    }

    @Test
    void afficher() {
        Appelant a = new Appelant("Ndonkou", "Edith", "e@mail.com", "Comptabilite");
        assertDoesNotThrow(a::afficher);
    }

    @Test
    void nom_vide_leve_exception() {
        assertThrows(IllegalArgumentException.class, () ->
                new Appelant("", "Edith", "e@mail.com", "RH"));
    }

    @Test
    void email_sans_arobase_leve_exception() {
        assertThrows(IllegalArgumentException.class, () ->
                new Appelant("Ndonkou", "Edith", "emailinvalide", "RH"));
    }

    @Test
    void departement_vide_leve_exception() {
        assertThrows(IllegalArgumentException.class, () ->
                new Appelant("Ndonkou", "Edith", "e@mail.com", ""));
    }
}
