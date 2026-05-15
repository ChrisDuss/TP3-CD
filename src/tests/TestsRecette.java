package tests;

import logique.Ingredient;
import logique.Recette;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * TP3 - CD.iml
 *
 * @author chris
 * @since 5/13/2026
 */
public class TestsRecette {
    Ingredient ing1 = new Ingredient("Carrot", 2);
    Ingredient ing2 = new Ingredient("Patate", 5);
    Ingredient ing3 = new Ingredient("Amethyst", 20);
    String nomInit = "Recette_soupe";
    int diffInit = 2;
    int expInint = 3;
    Recette rec = null;

    @BeforeEach
    public void Recette_Initialisation() {
        rec = new Recette(ing1, ing2, ing3, nomInit, diffInit, expInint);
    }

    @Test
    public void Recette_Constructeur_Aucun_Ingredient_null() {
        Exception ex = assertThrows(IllegalArgumentException.class, ()->
                rec = new Recette(ing1, null, ing3, nomInit, diffInit, expInint));

        assertEquals(Recette.AUCUN_INGREDIENT_NULL, ex.getMessage());
    }

    @Test
    public void Recette_Constructeur_Ingredient_Unique() {
        Exception ex = assertThrows(IllegalArgumentException.class, ()->
                rec = new Recette(ing1, ing1, ing3, nomInit, diffInit, expInint));

        assertEquals(Recette.INGREDIENTS_UNIQUE, ex.getMessage());
    }

    @Test
    public void Recette_Constructeur_setNom_Non_Null() {
        Exception ex = assertThrows(IllegalArgumentException.class, ()->
                rec = new Recette(ing1, ing2, ing3, null, diffInit, expInint));

        assertEquals(Recette.NOM_NULL, ex.getMessage());
    }

    @Test
    public void Recette_Constructeur_setNom_Nom_Trop_Court() {
        Exception ex = assertThrows(IllegalArgumentException.class, ()->
                rec = new Recette(ing1, ing2, ing3, "nomInit", diffInit, expInint));

        assertEquals(Recette.NOM_TROP_COURT, ex.getMessage());
    }

    @Test
    public void Recette_Constructeur_setNom() {
        assertEquals(rec.getNom(), nomInit);
    }

    @Test
    public void Recette_Constructeur_setDifficulte_Plus_bas_1() {
        Exception ex = assertThrows(IllegalArgumentException.class, ()->
                rec = new Recette(ing1, ing2, ing3, nomInit, 0, expInint));

        assertEquals(Recette.NOT_IN_RANGE, ex.getMessage());
    }

    @Test
    public void Recette_Constructeur_setDifficulte_Plus_Haut_5() {
        Exception ex = assertThrows(IllegalArgumentException.class, ()->
                rec = new Recette(ing1, ing2, ing3, nomInit, 6, expInint));

        assertEquals(Recette.NOT_IN_RANGE, ex.getMessage());
    }

    @Test
    public void Recette_Constructeur_setDifficulte() {
        assertEquals(rec.getDifficulte(), diffInit);
    }

    @Test
    public void Recette_Constructeur_setPointExperiance_Superieur_a_zero() {
        Exception ex = assertThrows(IllegalArgumentException.class, ()->
                rec = new Recette(ing1, ing2, ing3, nomInit, diffInit, 0));

        assertEquals(Recette.EXPERIENCE_SUPERIEUR_ZERO, ex.getMessage());
    }

    @Test
    public void Recette_Constructeur_setPointExperiance() {
        assertEquals(rec.getPointExperience(), expInint);
    }

    @Test
    public void Recette_obtenirPrix() {
        int total = ing1.getPrix() + ing2.getPrix() + ing3.getPrix();
        assertEquals(total, rec.obtenirPrix());
    }

    @Test
    public void Recette_contientIngredient_non_null() {
        Exception ex = assertThrows(IllegalArgumentException.class, ()->
                rec.contientIngredient(null));

        assertEquals(Recette.NOM_NULL, ex.getMessage());
    }

    @Test
    public void Recette_contientIngredient() {
        assertTrue(rec.contientIngredient(ing1.getNom()));
    }
}

