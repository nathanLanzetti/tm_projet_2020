package model;

import java.util.ArrayList;
import java.util.List;

public class ProfesseursComplet {
    private Professeurs professeur;
    private List<Cours> cours;

    public ProfesseursComplet(Professeurs professeur, List<Cours> cours) {
        this.professeur = professeur;
        this.cours = new ArrayList<>(cours);
    }

    public Professeurs getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Professeurs professeur) {
        this.professeur = professeur;
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }
}
