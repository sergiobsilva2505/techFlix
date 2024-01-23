package br.com.fiap.techFlix.domain.entities.category;

import br.com.fiap.techFlix.domain.validation.Validator;

public class Category {

    private String id;
    private String name;

    public Category(String id, String name) {
        Validator.notEmptyOrNull(id, "Category id");
        Validator.notEmptyOrNull(name, "Category name");
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
