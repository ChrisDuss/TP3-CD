package tests;

import logique.Ingredient;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * TP3 - CD.iml
 *
 * @author chris
 * @since 5/13/2026
 */
public class TestsIngredient {
    Ingredient ing = null;
    String nomInit = "ingredient";
    int prixInint = 5;

    @BeforeEach
    public void Ingredient_initialisation() {
        ing = new Ingredient(nomInit, prixInint);
    }

    @Test
    public void Ingredient_Constructeur_setNom_Non_Null() {
        Exception ex = assertThrows(IllegalArgumentException.class, ()->
                ing = new Ingredient(null, prixInint));

        assertEquals(Ingredient.NOM_NULL, ex.getMessage());
    }

    @Test
    public void Ingredient_Constructeur_setNom() {
        assertEquals(ing.getNom(), nomInit);
    }

    @Test
    public void Ingredient_Constructeur_setNom_Nom_Trop_Court() {
        Exception ex = assertThrows(IllegalArgumentException.class, ()->
                ing = new Ingredient("i", prixInint));

        assertEquals(Ingredient.NOM_TROP_COURT, ex.getMessage());
    }

    @Test
    public void Ingredient_Constructeur_setPrix_Superieur_a_zero() {
        Exception ex = assertThrows(IllegalArgumentException.class, ()->
                ing = new Ingredient(nomInit, 0));

        assertEquals(Ingredient.PRIX_SUPERIEUR_ZERO, ex.getMessage());
    }

    @Test
    public void Ingredient_Constructeur_setPrix() {
        assertEquals(ing.getPrix(), prixInint);
    }
}
