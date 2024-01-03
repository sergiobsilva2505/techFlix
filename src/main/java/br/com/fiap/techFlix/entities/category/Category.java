package br.com.fiap.techFlix.entities.category;

import org.springframework.util.Assert;

public class Category {

    private String name;

    public Category(String name) {
        Assert.hasText(name, "Name cannot be empty");
        this.name = name;
    }
}
