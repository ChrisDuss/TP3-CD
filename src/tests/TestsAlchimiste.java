package tests;

import logique.Alchimiste;
import logique.Ingredient;
import logique.Recette;
import org.junit.After;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * TP3 - CD.iml
 *
 * @author chris
 * @since 5/13/2026
 */
public class TestsAlchimiste {
    Recette rec = new Recette(new Ingredient("Carrot", 2),
            new Ingredient("Patate", 5),
            new Ingredient("Amethyst", 20),
            "Recette_soupe", 5, 3);

    String nomInitHaut = "Merlin";
    String nomInit = "Gandalf";
    Alchimiste alchHaut = null;
    Alchimiste alch = null;

    @BeforeEach
    public void Alchimiste_initialisation() {
        alchHaut = new Alchimiste(nomInitHaut, 50);
        alch = new Alchimiste(nomInit);
    }

    @AfterEach
    public void gerer_invariant() {
        assertNotNull(alch.getNom());
        assertNotNull(alchHaut.getNom());
        assertTrue(alch.getNom().length() >= Alchimiste.LONGEUR_NOM_MINIMUM);
        assertTrue(alchHaut.getNom().length() >= Alchimiste.LONGEUR_NOM_MINIMUM);
    }

    @Test
    public void Alchimiste_setNom_non_null() {
        Exception ex = assertThrows(IllegalArgumentException.class, ()->
                new Alchimiste(null));

        assertEquals(Alchimiste.NOM_NULL, ex.getMessage());
    }

    @Test
    public void Alchimiste_setNom_trop_court() {
        Exception ex = assertThrows(IllegalArgumentException.class, ()->
                new Alchimiste("a"));

        assertEquals(Alchimiste.NOM_TROP_COURT, ex.getMessage());
    }

    @Test
    public void Alchimiste_setNom_correct() {
        assertEquals(nomInit, alch.getNom());
    }

    @Test
    public void Alchimiste_setNiveau_correct() {
        assertEquals(1, alch.getNiveau());
    }

    @Test
    public void Alchimiste_setExperiance_correct() {
        assertEquals(0, alch.getExperience());
    }

    @Test
    public void Alchimiste_FairePotion_non_null() {
        Exception ex = assertThrows(IllegalArgumentException.class, ()->
                alch.fairePotion(null));

        assertEquals(Alchimiste.RECETTE_NULL, ex.getMessage());
    }

    @Test
    public void Alchimiste_FairePotion_niveau_trop_bas() {
        boolean resultat = alch.fairePotion(rec);

        assertFalse(resultat);
    }

    @Test
    public void Alchimiste_FairePotion_niveau_correct() {
        boolean resultat = alchHaut.fairePotion(rec);

        assertTrue(resultat);
    }

    @Test
    public void Alchimiste_ajouterExperience_correct_exp() {
        int expInit = alchHaut.getExperience();

        alchHaut.fairePotion(rec);

        assertEquals(expInit + rec.getPointExperience(), alchHaut.getExperience());
    }

    @Test
    public void Alchimiste_ajouterExperience_correct_niveau() {
        int nivInit = alchHaut.getNiveau();

        while (alchHaut.getNiveau() == nivInit) {
            alchHaut.fairePotion(rec);
        }

        assertEquals(nivInit + 1, alchHaut.getNiveau());
        assertTrue(alchHaut.getExperience() < Alchimiste.EXPERIENCE_POUR_NIVEAU_SUIVANT);
    }

}
