package model;

import java.util.ArrayList;
import java.util.List;

public class QuestionsComplet {
    private Questions question;
    private Professeurs professeur;
    private Cours cours;
    private List<Solutions> solutions;

    public QuestionsComplet(Questions question, Professeurs professeur, Cours cours, List<Solutions> solutions) {
        this.question = question;
        this.professeur = professeur;
        this.cours = cours;
        this.solutions = new ArrayList<>(solutions);
    }

    public Questions getQuestion() {
        return question;
    }

    public void setQuestion(Questions question) {
        this.question = question;
    }

    public Professeurs getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Professeurs professeur) {
        this.professeur = professeur;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public List<Solutions> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<Solutions> solutions) {
        this.solutions = solutions;
    }

    @Override
    public String toString() {
        return "QuestionsComplet{" +
                "question=" + question +
                ", professeur=" + professeur +
                ", cours=" + cours +
                ", solutions=" + solutions +
                '}';
    }
}
