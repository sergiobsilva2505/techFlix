package br.com.fiap.techFlix.domain.entities.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    @Test
    void shouldCreateUserWithValidParameters() {
        User user = new User("1", "Lucas", "lucas@gmail.com", "password", "token");

        assertEquals("1", user.getId());
        assertEquals("Lucas", user.getName());
        assertEquals("lucas@gmail.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals("token", user.getToken());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new User(null, "Lucas", "lucas@gmail.com", "password", "token"));
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new User("1", null, "lucas@gmail.com", "password", "token"));
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new User("1", "Lucas", null, "password", "token"));
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new User("1", "Lucas", "lucas@gmail.com", null, "token"));
    }

    @Test
    void shouldThrowExceptionWhenTokenIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new User("1", "Lucas", "lucas@gmail.com", "password", null));
    }

    @Test
    void shouldThrowExceptionWhenIdIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new User("", "Lucas", "lucas@gmail.com", "password", "token"));
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new User("1", "", "lucas@gmail.com", "password", "token"));
    }

    @Test
    void shouldThrowExceptionWhenEmailIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new User("1", "Lucas", "", "password", "token"));
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new User("1", "Lucas", "lucas@gmail.com", "", "token"));
    }

    @Test
    void shouldThrowExceptionWhenTokenIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new User("1", "Lucas", "lucas@gmail.com", "password", ""));
    }
}