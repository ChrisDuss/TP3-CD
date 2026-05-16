package tests;

import logique.*;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Christophe Dussault
 * Ordre de conseption : 4e
 */
public class TestsLaboratoire{
    List<Ingredient> ings = null;
    List<Recette> recs = null;

    Alchimiste alchHaut = new Alchimiste("Merlin", 50);
    Alchimiste alch = new Alchimiste("Gandalf");
    Laboratoire labHaut = null;
    Laboratoire lab = null;
    Path pathOut = Paths.get("src/recettesBak.txt");
    Path pathIn = Paths.get("src/recettes.txt");


    @BeforeEach
    public void Laboratoire_Initialisation() {
        lab = new Laboratoire(alch);
        labHaut = new Laboratoire(alchHaut);
        ings = lab.getIngredients();
        recs = lab.getRecettes();

        StringBuilder backup = new StringBuilder();

        for (Recette rec : recs) {
            backup.append(rec.toString()).append("\n");
        }


        try {
            Files.writeString(pathOut, backup);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void reinitializer_fichier() {
        try {
            Files.copy(pathOut, pathIn, StandardCopyOption.REPLACE_EXISTING);
            Files.delete(pathOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void Laboratoire_setProprietaire_non_null() {
        Exception ex = assertThrows(IllegalArgumentException.class, ()->
                new Laboratoire(null));

        assertEquals(Laboratoire.ALCHIMISTE_NULL, ex.getMessage());
    }

    @Test
    public void Laboratoire_fairePotion_recette_null() {
        ResultatExperience experience = new ResultatExperience();

        ResultatExperience resutat = lab.fairePotion(ings.get(3).getNom(), ings.get(2).getNom(), ings.get(1).getNom());

        assertEquals(resutat.getSuccess(), experience.getSuccess());
        assertEquals(resutat.getExiste(), experience.getExiste());
    }

    @Test
    public void Laboratoire_fairePotion_recette_success() {
        ResultatExperience experience = new ResultatExperience();
        experience.setExiste(true);
        experience.setSuccess(true);

        String ing1 = ings.get(0).getNom();
        String ing2 = ings.get(1).getNom();
        String ing3 = ings.get(2).getNom();

        ResultatExperience resutat = labHaut.fairePotion(ing1, ing2, ing3);

        assertEquals(resutat.getSuccess(), experience.getSuccess());
        assertEquals(resutat.getExiste(), experience.getExiste());
    }

    @Test
    public void Laboratoire_creerNouvellePotion_correct() {
        ResultatExperience experience = new ResultatExperience();
        experience.setExiste(false);
        experience.setSuccess(true);

        String ing1 = ings.get(4).getNom();
        String ing2 = ings.get(3).getNom();
        String ing3 = ings.get(ings.size() - 1).getNom();
        String nom = "recette_aleatoire";

        ResultatExperience resutat = lab.creerNouvellePotion(ing1, ing2, ing3, nom, 1, 20);

        assertEquals(resutat.getSuccess(), experience.getSuccess());
        assertEquals(resutat.getExiste(), experience.getExiste());
    }

    @Test
    public void Laboratoire_creerNoubellePotion_recette_exist() {
        ResultatExperience experience = new ResultatExperience();
        experience.setExiste(true);
        experience.setSuccess(false);

        String ing1 = ings.get(0).getNom();
        String ing2 = ings.get(1).getNom();
        String ing3 = ings.get(2).getNom();
        String nom = "recette_aleatoire";

        ResultatExperience resutat = lab.creerNouvellePotion(ing1, ing2, ing3, nom, 1, 20);

        assertEquals(resutat.getSuccess(), experience.getSuccess());
        assertEquals(resutat.getExiste(), experience.getExiste());
    }

    @Test
    public void Laboratoire_creerNoubellePotion_ingredient_inconnu() {
        ResultatExperience experience = new ResultatExperience();
        experience.setExiste(true);
        experience.setSuccess(false);

        String ing1 = ings.get(0).getNom();
        String ing2 = ings.get(1).getNom();
        String ing3 = new Ingredient("Carrot", 2).getNom();
        String nom = "recette_aleatoire";

        Exception ex = assertThrows(IllegalArgumentException.class, ()->
                lab.creerNouvellePotion(ing1, ing2, ing3, nom, 1, 20));

        assertEquals(Laboratoire.PAS_DE_TOUT_INGREDIENTS, ex.getMessage());
    }

    @Test
    public void Laboratoire_ajouterRecette_correct() {
        String ing1 = ings.get(4).getNom();
        String ing2 = ings.get(3).getNom();
        String ing3 = ings.get(ings.size() - 1).getNom();
        String nom = "recette_aleatoire";

        lab.creerNouvellePotion(ing1, ing2, ing3, nom, 1, 20);
        Laboratoire labTemp = new Laboratoire(alch);

        System.out.println("labTemp:\t" + labTemp.getRecettes());
        System.out.println("lab:\t\t" + lab.getRecettes());

        assertEquals(labTemp.getRecettes().toString(), lab.getRecettes().toString());
    }

    @Test
    public void Laboratoire_trouverRecette_correct() {
        Recette rec = recs.get(0);

        String ing1 = ings.get(0).getNom();
        String ing2 = ings.get(1).getNom();
        String ing3 = ings.get(2).getNom();

        Recette recTrouver = lab.trouverRecette(ing1, ing2, ing3);

        assertEquals(rec, recTrouver);
    }

    @Test
    public void Laboratoire_trouverRecette_null() {
        String ing1 = ings.get(0).getNom();
        String ing2 = ings.get(1).getNom();
        String ing3 = ings.get(3).getNom();

        Recette recTrouver = lab.trouverRecette(ing1, ing2, ing3);

        assertNull(recTrouver);
    }

    @Test
    public void Laboratoire_trouverIngredient_correct() {
        Ingredient ing = ings.get(0);

        Ingredient ingTrouver = lab.trouverIngredient(ing.getNom());

        assertEquals(ing, ingTrouver);
    }

    @Test
    public void Laboratoire_trouverIngredient_null() {
        Ingredient ingTrouver = lab.trouverIngredient("ingredient");

        assertNull(ingTrouver);
    }


}
