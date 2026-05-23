package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void getTitre() {
        Solution s = new Solution("Redemarrage Excel", "Fermer et relancer.");
        assertEquals("Redemarrage Excel", s.getTitre());
    }

    @Test
    void getDescriptionSolution() {
        Solution s = new Solution("Redemarrage Excel", "Fermer et relancer.");
        assertEquals("Fermer et relancer.", s.getDescriptionSolution());
    }

    @Test
    void afficher() {
        Solution s = new Solution("Redemarrage Excel", "Fermer et relancer.");
        assertDoesNotThrow(s::afficher);
    }
}
