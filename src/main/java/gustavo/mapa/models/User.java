package main.java.gustavo.mapa.models;

public class User {
    private int id;
    private String name;
    private String login;
    private String password;
    private String email;

    public User() {}

    public User(String name, String login, String password, String email) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogine(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean isValid() {
        return name != null && !name.isEmpty() &&
               login != null && !login.isEmpty() &&
               password != null && !password.isEmpty() &&
               email != null && !email.isEmpty();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Name: ");
        sb.append(name);
        sb.append("\nLogin: ");
        sb.append(login);
        sb.append("\nEmail: ");
        sb.append(email);

        return sb.toString();
    }

}