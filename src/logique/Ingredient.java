package logique;

/**
 * Author: Christophe Dussault
 * Ordre de conseption : 1e
 */

public class Ingredient {
    public static final String NOM_NULL = "nom ne peut pas être null";
    public static final String NOM_TROP_COURT = "nom trop court";
    public static final String PRIX_SUPERIEUR_ZERO = "Prix doit être supérieur à zéro";
    public static final int LONGEUR_NOM_MIN = 6;

    private String nom;
    private int prix;

    public Ingredient(String nom, int prix) {
        this.setNom(nom);
        this.setPrix(prix);
    }

    public String getNom() {
        return nom;
    }

    /**
     * Set le nom de l'ingrédient.
     * @param nom the nom voulue
     *
     * @throws IllegalArgumentException si le nom est null
     * @throws IllegalArgumentException si le nom est inferieur à 6 charactères
     */
    private void setNom(String nom) {
        if (nom == null) throw new IllegalArgumentException(NOM_NULL);
        else if (nom.length() < LONGEUR_NOM_MIN) throw new IllegalArgumentException(NOM_TROP_COURT);
        else this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    /**
     * Set le prix de l'ingrédient.
     * @param prix le prix voulu
     *
     * @throws IllegalArgumentException si le prix est inférieur à zéro
     */
    private void setPrix(int prix) {
        if (prix <= 0) throw new IllegalArgumentException(PRIX_SUPERIEUR_ZERO);
        else this.prix = prix;
    }

    @Override
    public String toString() {
        return "Ingredient{" + "nom=" + nom + ", prix=" + prix + '}';
    }
}