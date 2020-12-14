package model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TestsComplet {
    private Tests test;
    @SerializedName("questionComplets")
    private List<QuestionsComplet> questionsComplets;

    public TestsComplet(Tests test, List<QuestionsComplet> questionsComplets) {
        this.test = test;
        this.questionsComplets = new ArrayList<>(questionsComplets);
    }

    public Tests getTest() {
        return test;
    }

    public void setTest(Tests test) {
        this.test = test;
    }

    public List<QuestionsComplet> getQuestionsComplets() {
        return questionsComplets;
    }

    public void setQuestionsComplets(List<QuestionsComplet> questionsComplets) {
        this.questionsComplets = questionsComplets;
    }

    @Override
    public String toString() {
        return "TestsComplet{" +
                "test=" + test +
                ", questionsComplets=" + questionsComplets +
                '}';
    }

}
