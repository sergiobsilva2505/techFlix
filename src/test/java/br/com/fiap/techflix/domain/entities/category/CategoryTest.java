package br.com.fiap.techflix.domain.entities.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoryTest {
    @Test
    @DisplayName("Cria categoria com ID e nome válidos")
    void shouldCreateCategoryWithValidIdAndName() {
        Category category = new Category("1", "Action");

        assertEquals("1", category.getId());
        assertEquals("Action", category.getName());
    }

    @Test
    @DisplayName("Lança exceção quando ID é nulo")
    void shouldThrowExceptionWhenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Category(null, "Action"));
    }

    @Test
    @DisplayName("Lança exceção quando nome é nulo")
    void shouldThrowExceptionWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Category("1", null));
    }

    @Test
    @DisplayName("Lança exceção quando ID é vazio")
    void shouldThrowExceptionWhenIdIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Category("", "Action"));
    }

    @Test
    @DisplayName("Lança exceção quando nome é vazio")
    void shouldThrowExceptionWhenNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Category("1", ""));
    }
}