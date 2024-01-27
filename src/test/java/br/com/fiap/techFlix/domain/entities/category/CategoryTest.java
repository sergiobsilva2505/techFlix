package br.com.fiap.techFlix.domain.entities.category;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoryTest {

    @Test
    void shouldCreateCategoryWithValidIdAndName() {
        Category category = new Category("1", "Action");

        assertEquals("1", category.getId());
        assertEquals("Action", category.getName());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Category(null, "Action"));
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Category("1", null));
    }

    @Test
    void shouldThrowExceptionWhenIdIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Category("", "Action"));
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Category("1", ""));
    }
}