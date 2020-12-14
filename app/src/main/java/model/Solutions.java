package model;

public class Solutions {
    private int id;
    private String text;
    private int id_questions;
    private int answer;

    public Solutions(int id, String text, int id_questions, int answer) {
        this.id = id;
        this.text = text;
        this.id_questions = id_questions;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId_questions() {
        return id_questions;
    }

    public void setId_questions(int id_questions) {
        this.id_questions = id_questions;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Solutions{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", id_questions=" + id_questions +
                ", answer=" + answer +
                '}';
    }
}
