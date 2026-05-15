package logique;

import java.util.ArrayList;

/**
 * Author : Christophe Dussault
 * Ordre de conception : 2e
 */
public class Recette {
    public static final String NOM_TROP_COURT = "nom trop court";
    public static final String NOM_NULL = "nom ne peut pas être null";
    public static final String AUCUN_INGREDIENT_NULL = "Aucun ingrédient ne peut être null";
    public static final String INGREDIENTS_UNIQUE = "Les ingrédients doivent tout être unique";
    public static final String NOT_IN_RANGE = "difficulté not in range";
    public static final String EXPERIENCE_SUPERIEUR_ZERO = "expérience est strictement supérieur a zéro";
    public static final int LONGEUR_NOM_MIN = 10;
    private ArrayList<Ingredient> ingredients;
    private String nom;
    private int difficulte;
    private int pointExperience;

    public Recette(Ingredient ing1, Ingredient ing2, Ingredient ing3, String nom, int difficulte, int pointExperience) {
        if (ing1 == null || ing2 == null || ing3 == null) throw new IllegalArgumentException(AUCUN_INGREDIENT_NULL);
        else if (ing1 == ing2 || ing2 == ing3 || ing3 == ing1) throw new IllegalArgumentException(INGREDIENTS_UNIQUE);
        else {
            this.ingredients = new ArrayList<Ingredient>();
            this.ingredients.add(ing1);
            this.ingredients.add(ing2);
            this.ingredients.add(ing3);

            this.setDifficulte(difficulte);
            this.setNom(nom);
            this.setPointExperience(pointExperience);
        }
    }


    public String getNom() {
        return nom;
    }

    private void setNom(String nom) {
        if (nom == null) throw new IllegalArgumentException(NOM_NULL);
        else if (nom.length() < LONGEUR_NOM_MIN) throw new IllegalArgumentException(NOM_TROP_COURT);
        else this.nom = nom;
    }

    public int getDifficulte() {
        return difficulte;
    }

    private void setDifficulte(int difficulte) {
        if (difficulte < 1 || difficulte > 5) throw new IllegalArgumentException(NOT_IN_RANGE);
        else this.difficulte = difficulte;
    }

    public int getPointExperience() {
        return pointExperience;
    }

    private void setPointExperience(int pointExperience) {
        if (pointExperience <= 0) throw new IllegalArgumentException(EXPERIENCE_SUPERIEUR_ZERO);
        else this.pointExperience = pointExperience;
    }

    public int obtenirPrix() {
        int prixTotal = 0;

        for (Ingredient ing : this.ingredients)
            prixTotal += ing.getPrix();

        return prixTotal;
    }

    public boolean contientIngredient(String nom) {
        if (nom == null) throw new IllegalArgumentException(NOM_NULL);
        else {
            boolean estContenu = false;


            for (Ingredient ing : this.ingredients) {
                if (ing.getNom().equals(nom)) {
                    estContenu = true;
                    break;
                }
            }

            return estContenu;
        }
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%s|%s", this.getNom(), this.ingredients.get(0).getNom(), this.ingredients.get(1).getNom(), this.ingredients.get(2).getNom(), this.getDifficulte(), this.getPointExperience());
    }


}
