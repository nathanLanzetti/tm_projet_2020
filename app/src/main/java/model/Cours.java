package model;

public class Cours {
    private int id;
    private String nom_cours;

    public Cours(int id, String nom_cours) {
        this.id = id;
        this.nom_cours = nom_cours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_cours() {
        return nom_cours;
    }

    public void setNom_cours(String nom_cours) {
        this.nom_cours = nom_cours;
    }

    @Override
    public String toString() {
        return "Cours{" +
                "id=" + id +
                ", nom_cours='" + nom_cours + '\'' +
                '}';
    }
}
