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
    public static final int DIFF_MINIMUM = 1;
    public static final int DIFF_MAXIMUM = 5;
    private ArrayList<Ingredient> ingredients;
    private String nom;
    private int difficulte;
    private int pointExperience;

    /**
     * Instancialise une recette.
     *
     * @param ing1 le premier ingrédient
     * @param ing2 le deuxième ingrédient
     * @param ing3 le troisième ingrédient
     * @param nom le nom de la recette
     * @param difficulte la difficulté de la recette
     * @param pointExperience les points d'experiences qu'elle donne
     *
     * @throws IllegalArgumentException si une des ingrédients est null
     * @throws IllegalArgumentException si tous les ingrédients ne sont pas unique
     */
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

    /**
     * Set le nom de la recette.
     * @param nom the nom voulue
     *
     * @throws IllegalArgumentException si le nom est null
     * @throws IllegalArgumentException si le nom est inferieur à 10 charactères
     */
    private void setNom(String nom) {
        if (nom == null) throw new IllegalArgumentException(NOM_NULL);
        else if (nom.length() < LONGEUR_NOM_MIN) throw new IllegalArgumentException(NOM_TROP_COURT);
        else this.nom = nom;
    }

    public int getDifficulte() {
        return difficulte;
    }

    /**
     * Set la difficulté de la recette.
     * @param difficulte la difficulté voulue
     *
     * @throws IllegalArgumentException si la difficulté n'est pas entre 1 et 5.
     */
    private void setDifficulte(int difficulte) {
        if (difficulte < DIFF_MINIMUM || difficulte > DIFF_MAXIMUM) throw new IllegalArgumentException(NOT_IN_RANGE);
        else this.difficulte = difficulte;
    }

    public int getPointExperience() {
        return pointExperience;
    }

    /**
     * Set l'experience que la recette donne
     * @param pointExperience point d'experience voulue
     *
     * @throws IllegalArgumentException si inférieur ou égale à zéro.
     */
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

    /**
     * Regarde si la recette contient un ingrédient.
     * @param nom le nom de l'ingrédient rechercher
     * @return vraie si l'ingrédient est dans la recette
     *
     * @throws IllegalArgumentException si le nom donner est null
     */
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
