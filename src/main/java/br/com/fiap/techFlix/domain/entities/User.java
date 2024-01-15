package br.com.fiap.techFlix.domain.entities;

import org.springframework.util.Assert;

public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private String token;

    public User(String name, String email, String password) {
        Assert.hasText(name, "Name must not be empty");
        Assert.hasText(email, "Email must not be empty");
        Assert.hasText(password, "Password must not be empty");
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String id, String name, String email, String password, String token) {
        this(name, email, password);
        Assert.hasText(id, "Id must not be empty");
        Assert.hasText(token, "Token must not be empty");
        this.id = id;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        Assert.hasText(token, "Token must not be empty");
        this.token = token;
    }
}
