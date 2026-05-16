package logique;

/**
 * Author: Christophe Dussault
 * Ordre de conseption : 3e
 */

import java.util.Random;

public class Alchimiste {
    public static final int EXPERIENCE_POUR_NIVEAU_SUIVANT = 500;
    public static final String NOM_NULL = "nom ne peut pas être null";
    public static final String NOM_TROP_COURT = "nom trop court";
    public static final String RECETTE_NULL = "recette ne peut pas être null";
    public static final int LONGEUR_NOM_MINIMUM = 6;
    private String nom;
    private int niveau;
    private int experience;

    public Alchimiste(String nom) {
        this.setNom(nom);
        this.setNiveau(1);
        this.setExperience(0);
    }

    public Alchimiste(String nom, int niveau) {
        this(nom);
        setNiveau(niveau);
    }

    public String getNom() {
        return this.nom;
    }

    public int getNiveau() {
        return this.niveau;
    }

    public int getExperience() {
        return this.experience;
    }

    /**
     * Change le nom de l'alchimiste.
     * @param nom the nom voulue
     *
     * @throws IllegalArgumentException si le nom est null
     * @throws IllegalArgumentException si le nom est inferieur à 6 charactères
     */
    private void setNom(String nom) {
        if (nom == null) throw new IllegalArgumentException(NOM_NULL);
        else if (nom.length() < LONGEUR_NOM_MINIMUM) throw new IllegalArgumentException(NOM_TROP_COURT);
        else this.nom = nom;
    }

    private void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    private void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * Crée une potion et l'ajoute avec une recette
     * et ajoute les points d'experiences si elle est un success.
     * <pre>
     * Les chances de success augmente avec le niveau de l'achimiste.
     * @param recette recette à faire
     * @return vraie si c'est un success
     *
     * @throws IllegalArgumentException si la recette est null
     */
    public boolean fairePotion(Recette recette) {
        if (recette == null) throw new IllegalArgumentException(RECETTE_NULL);
        boolean estReussi = false;
        double tauxExperience = this.niveau * 0.05;
        double tauxEchec = (recette.getDifficulte() * 0.25) - tauxExperience;

        if (tauxEchec < 0)
            tauxEchec = 0;

        Random random = new Random();
        double jetSuccess = ((double) random.nextInt(1, 101)) / 100;

        if (jetSuccess >= tauxEchec) {
            int nbExperience = recette.getPointExperience();
            this.ajouterExperience(nbExperience);
            estReussi = true;
        }

        return estReussi;
    }

    private void ajouterExperience(int experience) {
        this.setExperience(this.getExperience() + experience);

        if (this.getExperience() >= EXPERIENCE_POUR_NIVEAU_SUIVANT) {
            this.setNiveau(this.getNiveau() + 1);
            this.setExperience(this.getExperience() - EXPERIENCE_POUR_NIVEAU_SUIVANT);
            System.out.println("Vous êtes maintenant un alchimiste de niveau " + this.getNiveau());
        }
    }

}
