package br.com.fiap.techFlix.domain.validation;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void shouldNotThrowExceptionWhenTextIsNotEmptyOrNull() {
        Validator.notEmptyOrNull("valid text", "field");
    }

    @Test
    void shouldThrowExceptionWhenTextIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> Validator.notEmptyOrNull("", "field"));
    }

    @Test
    void shouldThrowExceptionWhenTextIsNull() {
        assertThrows(IllegalArgumentException.class, () -> Validator.notEmptyOrNull((String) null, "field"));
    }

    @Test
    void shouldNotThrowExceptionWhenCollectionIsNotEmptyOrNull() {
        Validator.notEmptyOrNull(Arrays.asList("item1", "item2"), "field");
    }

    @Test
    void shouldThrowExceptionWhenCollectionIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> Validator.notEmptyOrNull(Collections.emptyList(), "field"));
    }

    @Test
    void shouldThrowExceptionWhenCollectionIsNull() {
        assertThrows(IllegalArgumentException.class, () -> Validator.notEmptyOrNull((Collection<?>) null, "field"));
    }

    @Test
    void shouldNotThrowExceptionWhenObjectIsNotNull() {
        Validator.notNull(new Object(), "field");
    }

    @Test
    void shouldThrowExceptionWhenObjectIsNull() {
        assertThrows(IllegalArgumentException.class, () -> Validator.notNull(null, "field"));
    }

    @Test
    void shouldNotThrowExceptionWhenValueIsGreaterThanBaseValue() {
        Validator.greaterThan(10, 5, "field");
    }

    @Test
    void shouldThrowExceptionWhenValueIsLessThanOrEqualToBaseValue() {
        assertThrows(IllegalArgumentException.class, () -> Validator.greaterThan(5, 10, "field"));
    }

    @Test
    void shouldNotThrowExceptionWhenValueIsGreaterThanOrEqualToBaseValue() {
        Validator.greaterThanOrEqual(10, 5, "field");
    }

    @Test
    void shouldThrowExceptionWhenValueIsLessThanBaseValue() {
        assertThrows(IllegalArgumentException.class, () -> Validator.greaterThanOrEqual(5, 10, "field"));
    }
}