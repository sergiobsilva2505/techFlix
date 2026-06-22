package br.com.fiap.techflix.domain.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ValidatorTest {

    @Test @DisplayName("Não lança exceção quando texto não é vazio nem nulo")
    void shouldNotThrowExceptionWhenTextIsNotEmptyOrNull() {
        boolean completed = false;
        try {
            Validator.notEmptyOrNull("valid text", "field");
            completed = true;
        } catch (Exception e) {
            fail("Não deveria lançar exceção: " + e.getMessage());
        }
        assertTrue(completed);
    }

    @Test @DisplayName("Lança exceção quando texto é vazio")
    void shouldThrowExceptionWhenTextIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> Validator.notEmptyOrNull("", "field"));
    }

    @Test @DisplayName("Lança exceção quando texto é nulo")
    void shouldThrowExceptionWhenTextIsNull() {
        assertThrows(IllegalArgumentException.class, () -> Validator.notEmptyOrNull((String) null, "field"));
    }

    @Test @DisplayName("Não lança exceção quando coleção não é vazia nem nula")
    void shouldNotThrowExceptionWhenCollectionIsNotEmptyOrNull() {
        boolean completed = false;
        try {
            Validator.notEmptyOrNull(Arrays.asList("item1", "item2"), "field");
            completed = true;
        } catch (Exception e) {
            fail("Não deveria lançar exceção: " + e.getMessage());
        }
        assertTrue(completed);
    }

    @Test @DisplayName("Lança exceção quando coleção é vazia")
    void shouldThrowExceptionWhenCollectionIsEmpty() {
        List<Object> emptyList = Collections.emptyList();
        assertThrows(IllegalArgumentException.class, () -> Validator.notEmptyOrNull(emptyList, "field"));
    }

    @Test @DisplayName("Lança exceção quando coleção é nula")
    void shouldThrowExceptionWhenCollectionIsNull() {
        assertThrows(IllegalArgumentException.class, () -> Validator.notEmptyOrNull((Collection<?>) null, "field"));
    }

    @Test @DisplayName("Não lança exceção quando objeto não é nulo")
    void shouldNotThrowExceptionWhenObjectIsNotNull() {
        boolean completed = false;
        try {
            Validator.notNull(String.valueOf(42), "field");
            completed = true;
        } catch (Exception e) {
            fail("Não deveria lançar exceção: " + e.getMessage());
        }
        assertTrue(completed);
    }

    @Test @DisplayName("Lança exceção quando objeto é nulo")
    void shouldThrowExceptionWhenObjectIsNull() {
        assertThrows(IllegalArgumentException.class, () -> Validator.notNull(null, "field"));
    }

    @Test @DisplayName("Não lança exceção quando valor é maior que o valor base")
    void shouldNotThrowExceptionWhenValueIsGreaterThanBaseValue() {
        boolean completed = false;
        try {
            Validator.greaterThan(10, 5, "field");
            completed = true;
        } catch (Exception e) {
            fail("Não deveria lançar exceção: " + e.getMessage());
        }
        assertTrue(completed);
    }

    @Test @DisplayName("Lança exceção quando valor é menor ou igual ao valor base")
    void shouldThrowExceptionWhenValueIsLessThanOrEqualToBaseValue() {
        assertThrows(IllegalArgumentException.class, () -> Validator.greaterThan(5, 10, "field"));
    }

    @Test @DisplayName("Não lança exceção quando valor é maior ou igual ao valor base")
    void shouldNotThrowExceptionWhenValueIsGreaterThanOrEqualToBaseValue() {
        boolean completed = false;
        try {
            Validator.greaterThanOrEqual(10, 5, "field");
            completed = true;
        } catch (Exception e) {
            fail("Não deveria lançar exceção: " + e.getMessage());
        }
        assertTrue(completed);
    }

    @Test @DisplayName("Lança exceção quando valor é menor que o valor base")
    void shouldThrowExceptionWhenValueIsLessThanBaseValue() {
        assertThrows(IllegalArgumentException.class, () -> Validator.greaterThanOrEqual(5, 10, "field"));
    }
}