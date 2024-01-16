package br.com.fiap.techFlix.domain.entities;

import br.com.fiap.techFlix.domain.validation.Validator;

public class Category {

    private String name;

    public Category(String name) {
        Validator.notEmptyOrNull(name, "Category name");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
