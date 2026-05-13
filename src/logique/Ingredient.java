package logique;

/**
 * Author: Christophe Dussault
 * Ordre de conseption : 4e
 */

public class Ingredient {
    public static final String NOM_NULL = "nom ne peut pas être null";
    public static final String NOM_TROP_COURT = "nom trop court";
    public static final String PRIX_SUPÉRIEUR_ZÉRO = "Prix doit être supérieur à zéro";

    private String nom;
    private int prix;

    public Ingredient(String nom, int prix) {
        this.setNom(nom);
        this.setPrix(prix);
    }

    public String getNom() {
        return nom;
    }

    private void setNom(String nom) {
        if (nom == null) throw new IllegalArgumentException(NOM_NULL);
        else if (nom.length() <= 6) throw new IllegalArgumentException(NOM_TROP_COURT);
        else this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    private void setPrix(int prix) {
        if (prix < 0) throw new IllegalArgumentException(PRIX_SUPÉRIEUR_ZÉRO);
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Ingredient{" + "nom=" + nom + ", prix=" + prix + '}';
    }
}