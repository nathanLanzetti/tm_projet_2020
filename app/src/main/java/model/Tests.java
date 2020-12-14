package model;

public class Tests {
    private int id;
    private String name;
    private int author;
    private int cours;

    public Tests(int id, String name, int author, int cours) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.cours = cours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getCours() {
        return cours;
    }

    public void setCours(int cours) {
        this.cours = cours;
    }

    @Override
    public String toString() {
        return "Tests{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", cours=" + cours +
                '}';
    }
}
