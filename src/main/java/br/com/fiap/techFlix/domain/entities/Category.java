package br.com.fiap.techFlix.domain.entities;

import org.springframework.util.Assert;

public class Category {

    private String name;

    public Category(String name) {
        Assert.hasText(name, "Name cannot be empty");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
