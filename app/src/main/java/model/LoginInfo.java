package model;

public class LoginInfo {
    private String mail;
    private String password;

    public LoginInfo(String email, String password) {
        this.mail = email;
        this.password = password;
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
}
