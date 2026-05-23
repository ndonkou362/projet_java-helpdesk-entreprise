package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MaterielTest {

    @Test
    void getInfos() {
        Materiel m = new Materiel("PC-001", "Laptop", "Dell XPS");
        assertEquals("Laptop Dell XPS (ID: PC-001)", m.getInfos());
    }

    @Test
    void afficher() {
        Materiel m = new Materiel("PC-001", "Laptop", "Dell XPS");
        assertDoesNotThrow(m::afficher);
    }

    @Test
    void id_vide_leve_exception() {
        assertThrows(IllegalArgumentException.class, () -> new Materiel("", "Laptop", "Dell XPS"));
    }

    @Test
    void type_vide_leve_exception() {
        assertThrows(IllegalArgumentException.class, () -> new Materiel("PC-001", "", "Dell XPS"));
    }

    @Test
    void modele_vide_leve_exception() {
        assertThrows(IllegalArgumentException.class, () -> new Materiel("PC-001", "Laptop", ""));
    }
}
