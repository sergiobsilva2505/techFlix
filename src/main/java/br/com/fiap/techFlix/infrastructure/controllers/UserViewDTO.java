package br.com.fiap.techFlix.infrastructure.controllers;

import br.com.fiap.techFlix.domain.entities.User;

public record UserViewDTO(String id, String name, String email) {

    public UserViewDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
