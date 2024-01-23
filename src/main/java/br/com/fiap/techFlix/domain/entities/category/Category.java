package br.com.fiap.techFlix.domain.entities.category;

import br.com.fiap.techFlix.domain.validation.Validator;

public class Category {

    private String id;
    private String name;

    public Category(String name) {
        Validator.notEmptyOrNull(name, "Category name");
        this.name = name;
    }

    public Category(String id, String name) {
        this(name);
        Validator.notEmptyOrNull(id, "Category id");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
