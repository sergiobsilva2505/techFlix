package br.com.fiap.techFlix.domain.entities.user;

import br.com.fiap.techFlix.domain.validation.Validator;
import org.springframework.util.Assert;

public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private String token;

    public User(String name, String email, String password) {
        Validator.notEmptyOrNull(name, "user name");
        Validator.notEmptyOrNull(email, "user email");
        Validator.notEmptyOrNull(password, "user password");
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String id, String name, String email, String password, String token) {
        this(name, email, password);
        Validator.notEmptyOrNull(id, "user id");
        Validator.notEmptyOrNull(token, "user token");
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
