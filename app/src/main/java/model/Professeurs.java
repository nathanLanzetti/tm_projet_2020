package model;

public class Professeurs {
    private int id;
    private String name;
    private String firstname;
    private String mail;
    private String password;
    private String token;


    public Professeurs(String name, String firstname, String mail, String password) {
        this.name = name;
        this.firstname = firstname;
        this.mail = mail;
        this.password = password;
    }

    public Professeurs(int id, String name, String firstname, String mail, String password) {
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.mail = mail;
        this.password = password;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Professeurs{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstname='" + firstname + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
