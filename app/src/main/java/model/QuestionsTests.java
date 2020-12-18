package model;

public class QuestionsTests {
    private int id_questions;
    private int id_tests;

    public QuestionsTests(int id_questions, int id_tests) {
        this.id_questions = id_questions;
        this.id_tests = id_tests;
    }

    public int getId_questions() {
        return id_questions;
    }

    public void setId_questions(int id_questions) {
        this.id_questions = id_questions;
    }

    public int getId_tests() {
        return id_tests;
    }

    public void setId_tests(int id_tests) {
        this.id_tests = id_tests;
    }

    @Override
    public String toString() {
        return "QuestionsTests{" +
                "id_questions=" + id_questions +
                ", id_tests=" + id_tests +
                '}';
    }
}
