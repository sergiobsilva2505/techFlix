package br.com.fiap.techFlix.domain.entities.user;

import br.com.fiap.techFlix.domain.validation.Validator;

public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private String token;

    public User(String id, String name, String email, String password, String token) {
        Validator.notEmptyOrNull(id, "user id");
        Validator.notEmptyOrNull(name, "user name");
        Validator.notEmptyOrNull(email, "user email");
        Validator.notEmptyOrNull(password, "user password");
        Validator.notEmptyOrNull(token, "user token");
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
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

}
