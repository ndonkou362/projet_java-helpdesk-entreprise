package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProblemeTest {

    @Test
    void getDescription() {
        Probleme p = new Probleme("Ecran noir", Probleme.TypeProbleme.MATERIEL, Probleme.Priorite.HAUTE);
        assertEquals("Ecran noir", p.getDescription());
    }

    @Test
    void getType() {
        Probleme p = new Probleme("Ecran noir", Probleme.TypeProbleme.MATERIEL, Probleme.Priorite.HAUTE);
        assertEquals(Probleme.TypeProbleme.MATERIEL, p.getType());
        assertEquals("Materiel", p.getType().getLibelle());
    }

    @Test
    void getPriorite() {
        Probleme p = new Probleme("Ecran noir", Probleme.TypeProbleme.MATERIEL, Probleme.Priorite.HAUTE);
        assertEquals(Probleme.Priorite.HAUTE, p.getPriorite());
        assertEquals("Haute", p.getPriorite().getLibelle());
    }

    @Test
    void description_vide_leve_exception() {
        assertThrows(IllegalArgumentException.class, () ->
                new Probleme("", Probleme.TypeProbleme.LOGICIEL, Probleme.Priorite.BASSE));
    }

    @Test
    void type_null_leve_exception() {
        assertThrows(IllegalArgumentException.class, () ->
                new Probleme("Ecran noir", null, Probleme.Priorite.HAUTE));
    }

    @Test
    void priorite_null_leve_exception() {
        assertThrows(IllegalArgumentException.class, () ->
                new Probleme("Ecran noir", Probleme.TypeProbleme.MATERIEL, null));
    }
}
